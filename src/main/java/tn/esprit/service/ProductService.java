package tn.esprit.service;

import java.util.List;

import tn.esprit.entites.Product;

public interface ProductService {

	public Product add(Product product);

	public List<Product> getAll();

	public Product getById(Long id);

	public Product edit(Product product);

	public boolean delete(Long id);

}
