package net.engineeringdigest.journalApp;

import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserRepositoryImplTest {
    @Autowired
    UserRepositoryImpl userRepository;

    @Test
    public void testGetUserForSA(){
        assertNotNull(userRepository.getUserForSA());
    }
}
