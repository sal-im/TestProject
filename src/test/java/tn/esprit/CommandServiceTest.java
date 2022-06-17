package tn.esprit;

import static org.junit.Assert.assertEquals;
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

import tn.esprit.entites.Command;
import tn.esprit.entites.Product;
import tn.esprit.entites.User;
import tn.esprit.service.CommandService;
import tn.esprit.service.ProductService;
import tn.esprit.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)
public class CommandServiceTest {

	@Autowired
	CommandService commandService;

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;

	private static Long commandId;
	private static Command myCommand;

	@Test
	@Order(1)
	void addCommandTest() {
		Command command = new Command();
		command.setProduct(productService.getById((long) 11));
		command.setUser(userService.getById((long) 2));
		Command AfterSave = commandService.add(command);
		commandId = AfterSave.getId();
		assertNotNull(AfterSave);
		assertNotNull(commandId);
		assertEquals(userService.getById((long) 2).getId(), command.getUser().getId());
		assertEquals(productService.getById((long) 11).getId(), command.getProduct().getId());
	}

	@Test
	@Order(2)
	void getCommandByIdTest() {
		myCommand = commandService.getById(commandId);
		assertNotNull(myCommand);
		assertEquals(myCommand.getId(), commandId);
	}

	@Test
	@Order(3)
	void getAllCommandsTest() {
		List<Command> commands = commandService.getAll();
		assertTrue(commands.size() > 0);
	}

	@Test
	@Order(4)
	void deleteCommandTest() {
		assertTrue(commandService.delete(commandId));
	}
	
	@Test
	@Order(5)
	void UserIdNULLCommandTest() {
		Command command = new Command();
		command.setProduct(productService.getById((long) 11));
		User user = userService.getById((long) 2);
		user.setId(null);
		command.setUser(user);
		Command AfterSave = commandService.add(command);
		assertNull(AfterSave);
	}
	
	@Test
	@Order(6)
	void UserNotExistsCommandTest() {
		Command command = new Command();
		command.setProduct(productService.getById((long) 11));
		User user = userService.getById((long) 2);
		user.setId((long) 253632);
		command.setUser(user);
		Command AfterSave = commandService.add(command);
		assertNull(AfterSave);
	}
	
	@Test
	@Order(6)
	void ProductNotExistsCommandTest() {
		Command command = new Command();
		command.setUser(userService.getById((long) 2));
		Product product = productService.getById((long) 11);
		product.setId((long) 124531);
		command.setProduct(product);
		Command AfterSave = commandService.add(command);
		assertNull(AfterSave);
	}
}
