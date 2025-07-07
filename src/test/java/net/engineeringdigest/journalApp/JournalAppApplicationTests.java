package net.engineeringdigest.journalApp;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JournalAppApplicationTests {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@BeforeAll
	public static void setUpEnv(){

	}
	@BeforeEach
	public void browserOpen(){

	}

	@Disabled
	@ParameterizedTest
	@CsvSource({
			"Ram",
			"Shyam",
			"Vipul",
			"Hari"
	})
	void testFindByUsername(String user) {
		assertNotNull(userRepository.findByUserName(user),"failed for"+user);
	}
	@Disabled
	@ParameterizedTest
	@ArgumentsSource(UserArgumentsProvider.class)
	void testFindByUsername1(User user) {
		assertTrue(userService.createNewUser(user));
	}

	@AfterEach
	public void setUpEnvClose(){

	}
	@AfterEach
	public void browserClose(){

	}

}
