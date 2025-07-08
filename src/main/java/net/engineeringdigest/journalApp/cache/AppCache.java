package net.engineeringdigest.journalApp.cache;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.ConfigJournalEntity;
import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class AppCache {
    public enum keys{
        WEATHER_API;
    }
    @Autowired
    ConfigJournalAppRepository configJournalAppRepository;

    public Map<String,String> appCache;

    @PostConstruct
    public void init(){
        try{
            appCache = new HashMap<>();
            List<ConfigJournalEntity> configs = configJournalAppRepository.findAll();
            for(ConfigJournalEntity config:configs){
                appCache.put(config.getKey(),config.getValue());
            }
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }
}
