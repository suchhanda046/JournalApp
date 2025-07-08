package net.engineeringdigest.journalApp;

import net.engineeringdigest.journalApp.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    EmailService emailService;

    @Test
    public void testSendEmail(){
        emailService.sendEmail("suchhanda.senapati@gmail.com","Java EmailService Test","Hi, This emailService is sent while springboot test");
    }
}
