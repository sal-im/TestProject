package tn.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entites.Product;
import tn.esprit.service.ProductService;

@RequestMapping("/product")
@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("add")
	public ResponseEntity<Product> add(@RequestBody Product entity) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.add(entity));
	}

	@GetMapping("get-all")
	public ResponseEntity<List<Product>> getAll() {
		List<Product> products = productService.getAll();

		return ResponseEntity.status(HttpStatus.OK).body(products);
	}

	@GetMapping("get-by-id/{id}")
	public ResponseEntity<Product> getById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getById(id));
	}

	@PutMapping("edit")
	public ResponseEntity<Product> edit(@RequestBody Product entity) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.edit(entity));
	}

	@DeleteMapping("delete-by-id/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.delete(id));
	}
}
