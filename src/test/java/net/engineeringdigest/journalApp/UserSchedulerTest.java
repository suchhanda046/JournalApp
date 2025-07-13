package net.engineeringdigest.journalApp;

import net.engineeringdigest.journalApp.scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulerTest {
    @Autowired
    UserScheduler userScheduler;

    @Test
    public void testUserScheduler(){
        userScheduler.featchUserAndSendSAMail();
    }
}
