package tn.esprit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entites.Product;
import tn.esprit.repository.ProductRepository;
import tn.esprit.repository.UserRepository;
import tn.esprit.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Product add(Product product) {
		if (product != null & product.getBarcode() != null & !product.getBarcode().isEmpty() & product.getQuantity() > 0
				& product.getPrice() > 0 & !productRepository.existsByBarcode(product.getBarcode())
				& userRepository.existsById(product.getUser().getId())) {
			return productRepository.save(product);
		}
		return null;
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public Product getById(Long id) {
		if (id != null) {
			Optional<Product> optional = productRepository.findById(id);
			if (optional.isPresent()) {
				return optional.get();
			}
		}
		return null;
	}

	@Override
	public Product edit(Product product) {
		if (product != null & product.getId() != null && productRepository.existsById(product.getId())) {
			return productRepository.save(product);
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		productRepository.deleteById(id);
		return !productRepository.existsById(id);
	}

}
