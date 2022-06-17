package tn.esprit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.entites.Product;
import tn.esprit.service.ProductService;
import tn.esprit.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)
public class ProductServiceTest {

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;

	private static Long productId;
	private static Product myProduct;

	@Test
	@Order(1)
	void addProductTest() {
		Product product = new Product();
		product.setBarcode("FD25SX");
		product.setTitle("Pull POLO");
		product.setPrice((@NotNull float) 75.00);
		product.setQuantity(250);
		product.setUser(userService.getById((long) 2));
		Product productAfterSave = productService.add(product);
		productId = productAfterSave.getId();
		assertNotNull(productAfterSave);
		assertNotNull(productId);
		assertEquals("Pull POLO", productAfterSave.getTitle());
		assertEquals("FD25SX", productAfterSave.getBarcode());
		assertEquals(250, productAfterSave.getQuantity());
	}

	@Test
	@Order(2)
	void addProductWithUsedBarcodeSameTest() {
		Product product = new Product();
		product.setBarcode("XS25SX");
		product.setTitle("Nike");
		product.setPrice((@NotNull float) 53.00);
		product.setQuantity(112);
		product.setUser(userService.getById((long) 2));
		Product productAfterSave = productService.add(product);
		assertNull(productAfterSave);
	}

	@Test
	@Order(3)
	void getProductByIdTest() {
		myProduct = productService.getById(productId);
		assertNotNull(myProduct);
		assertEquals(myProduct.getId(), productId);
	}

	@Test
	@Order(4)
	void editProductTest() {
		Product product = productService.getById(productId);
		product.setQuantity(220);
		Product afterEdit = productService.edit(product);
		assertNotNull(afterEdit);
		assertNotEquals(afterEdit, myProduct);
		assertEquals(afterEdit.getQuantity(), 220);
	}

	@Test
	@Order(5)
	void editWithoutIdTest() {
		Product product = productService.getById(productId);
		product.setTitle("Pull H&M");
		product.setId(null);
		Product afterEdit = productService.edit(product);
		assertNull(afterEdit);
	}

	@Test
	@Order(6)
	void getAllProductsTest() {
		List<Product> products = productService.getAll();
		assertTrue(products.size() > 0);
	}

	@Test
	@Order(7)
	void deleteProductTest() {
		assertTrue(productService.delete(productId));
	}
	
	@Test
	@Order(8)
	void ProductQuantityTest() {
		Product product = new Product();
		product.setBarcode("CDCD362S");
		product.setTitle("Nike");
		product.setPrice((@NotNull float) 53.00);
		product.setQuantity(0);
		product.setUser(userService.getById((long) 2));
		Product productAfterSave = productService.add(product);
		assertNull(productAfterSave);
	}
	
	@Test
	@Order(9)
	void ProductPriceTest() {
		Product product = new Product();
		product.setBarcode("CDCD362S");
		product.setTitle("Nike");
		product.setPrice((@NotNull float) 0);
		product.setQuantity(114);
		product.setUser(userService.getById((long) 2));
		Product productAfterSave = productService.add(product);
		assertNull(productAfterSave);
	}
}
