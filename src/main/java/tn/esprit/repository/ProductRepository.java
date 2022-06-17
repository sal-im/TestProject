package tn.esprit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entites.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	public boolean existsByBarcode(String barcode);

}
