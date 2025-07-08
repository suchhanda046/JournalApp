package net.engineeringdigest.journalApp.scheduler;

import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.enums.Sentiments;
import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import net.engineeringdigest.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Component
public class UserScheduler {
    @Autowired
    UserRepositoryImpl userRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    AppCache appCache;

//    @Scheduled(cron = "0 0 9 * * SUN")
    public void featchUserAndSendSAMail(){

        List<User> users = userRepository.getUserForSA();
        for(User user:users){
            List<JournalEntry> entries = user.getJournalEntries();
            List<Sentiments> mapEntries = entries.stream().filter(x->x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getSentiment()).collect(Collectors.toList());
            Map<Sentiments,Integer> allSentiments=new HashMap<>();
            for(Sentiments sentiments:mapEntries){
                allSentiments.put(sentiments,allSentiments.getOrDefault(sentiments,0)+1);
            }
            int maxCount=0;
            Sentiments mostFrequentSentiment=null;
            for(Map.Entry<Sentiments,Integer> sentiment:allSentiments.entrySet()){
                if(sentiment.getValue() > maxCount){
                    maxCount=sentiment.getValue();
                    mostFrequentSentiment=sentiment.getKey();
                }
            }
            if(mostFrequentSentiment!=null){
                emailService.sendEmail(user.getEmail(),"SentimentAnalysisService for last 7 days", mostFrequentSentiment.toString());
            }
        }
    }

    @Scheduled(cron = "0 0 9 * * SUN")
    public void clearCache(){
        appCache.init();
    }
}
