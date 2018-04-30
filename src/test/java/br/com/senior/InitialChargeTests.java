package br.com.senior;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.senior.model.domain.user.User;
import br.com.senior.model.repository.user.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitialChargeTests {

	@Autowired
	UserRepository userRepository;
	
	@BeforeClass
	public static void setHeadless() {
		 System.setProperty("java.awt.headless", "false");
	}
	
	@Test
	public void defaultUsersCreated() {
		User admin = userRepository.findUserByUsernameAndPassword("admin", "admin");
		assertNotNull(admin);
		assertTrue(admin.isAdmin());
		
		User vendas = userRepository.findUserByUsernameAndPassword("vendas", "vendas");
		assertNotNull(vendas);
		assertFalse(vendas.isAdmin());
		assertEquals(2, userRepository.count());
	}


}
