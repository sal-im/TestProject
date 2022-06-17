package tn.esprit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.entites.User;
import tn.esprit.enums.UserRole;
import tn.esprit.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)
public class UserServiceTest {

	@Autowired
	UserService userService;

	private static Long userId;
	private static User myUser;

	@Test
	@Order(1)
	void addUserTest() {
		User user = new User();
		user.setFirstName("Ala");
		user.setLastName("Hammdi");
		user.setPassword("123456");
		user.setEmail("ala@gmail.com");
		user.setRole(UserRole.ADMIN);
		user.setUsername("ala_hm");
		User userAfterSave = userService.add(user);
		userId = userAfterSave.getId();
		assertNotNull(userAfterSave);
		assertNotNull(userId);
		assertEquals("ala@gmail.com", userAfterSave.getEmail());
	}

	@Test
	@Order(2)
	void getUserByIdTest() {
		myUser = userService.getById(userId);
		assertNotNull(myUser);
		assertEquals(myUser.getId(), userId);
	}

	@Test
	@Order(3)
	void editUserTest() {
		User user = userService.getById(userId);
		user.setFirstName("amine");
		user.setEmail("amine@gmail.com");
		User afterEdit = userService.edit(user);
		assertNotNull(afterEdit);
		assertNotEquals(afterEdit, myUser);
		assertEquals(afterEdit.getFirstName(), "amine");
	}

	@Test
	@Order(4)
	void editUserWithoutIdTest() throws CloneNotSupportedException {
		User user = userService.getById(userId);
		user.setFirstName("saif");
		user.setId(null);
		User afterEdit = userService.edit(user);
		assertNull(afterEdit);
	}

	@Test
	@Order(5)
	void getAllUsersTest() {
		List<User> users = userService.getAll();
		assertTrue(users.size() > 0);
	}

	@Test
	@Order(6)
	void deleteUserTest() {
		assertTrue(userService.delete(userId));
	}
	
	@Test
	@Order(7)
	void EmailUsedTest() {
		User user = new User();
		user.setFirstName("Ala");
		user.setLastName("Hammdi");
		user.setPassword("123456");
		user.setEmail("used@gmail.com");
		user.setRole(UserRole.ADMIN);
		user.setUsername("ala_hsm");
		User userAfterSave = userService.add(user);
		assertNull(userAfterSave);
	}

	@Test
	@Order(8)
	void EmptyEmailTest() {
		User user = new User();
		user.setFirstName("Ala");
		user.setLastName("Hammdi");
		user.setPassword("123456");
		user.setEmail("");
		user.setRole(UserRole.ADMIN);
		user.setUsername("ala_hsm");
		User userAfterSave = userService.add(user);
		assertNull(userAfterSave);
	}
	
	@Test
	@Order(9)
	void ValidEmailTest() {
		User user = new User();
		user.setFirstName("Ala");
		user.setLastName("Hammdi");
		user.setPassword("123456");
		user.setEmail("simple string");
		user.setRole(UserRole.ADMIN);
		user.setUsername("ala_hsm");
		User userAfterSave = userService.add(user);
		assertNull(userAfterSave);
	}
	
	@Test
	@Order(10)
	void EmptyPasswordTest() {
		User user = new User();
		user.setFirstName("Ala");
		user.setLastName("Hammdi");
		user.setPassword("");
		user.setEmail("test@gmail.com");
		user.setRole(UserRole.ADMIN);
		user.setUsername("ala_hsm");
		User userAfterSave = userService.add(user);
		assertNull(userAfterSave);
	}
	
	@Test
	@Order(10)
	void invalidPasswordTest() {
		User user = new User();
		user.setFirstName("Ala");
		user.setLastName("Hammdi");
		user.setPassword("<6");
		user.setEmail("test@gmail.com");
		user.setRole(UserRole.ADMIN);
		user.setUsername("ala_hsm");
		User userAfterSave = userService.add(user);
		assertNull(userAfterSave);
	}
}
