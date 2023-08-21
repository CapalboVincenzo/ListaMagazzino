package com.example.demo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Annotation
@Service

public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	// Save operation
	public Product saveProduct(Product product)
	{
		return productRepository.save(product);
	}

	// Read operation/ findAll
	public List<Product> fetchProductList()
	{
		return (List<Product>)
			productRepository.findAll();
	}

	 
	// Delete operation/ DeleteByID
	public void deleteProductById(Integer productId)
	{
		productRepository.deleteById(productId);
	}
}