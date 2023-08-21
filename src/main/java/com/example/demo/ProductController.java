package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController {

	@Autowired 
	private ProductRepository productRepository;
	
	
	@RequestMapping(value = "/products/", method = RequestMethod.POST)
	public Product createProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable Integer id) {
		return productRepository.findById(id).orElse(null);
	}
	
	
	@PutMapping("/products/{id}")
	public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
		product.setId(id);
		return productRepository.save(product);
	}
	
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable Integer id) {
		productRepository.deleteById(id);
	}
	
	@RequestMapping(value = "/products/", method = RequestMethod.GET)
	public List <Product> getAllProduct(){
		return productRepository.findAll();
	}

	 @PatchMapping("/products/{id}")
	  public ResponseEntity<Product> patchProduct(@PathVariable("id") int id, @RequestBody Product update){
		 Optional<Product> optionalProduct = productRepository.findById(id);
		 //TO SEARCH ELEMENTS WITH FINDBYID - ID
		    if (!optionalProduct.isPresent()) {
		      return ResponseEntity.notFound().build();
	 }
	    Product product = optionalProduct.get();
	    //SET NAME AND UPDATE 
	    if (update.getName() != null) {
	      product.setName(update.getName());
	    }
	    //SET PRICE ONLY IF IT'S NOT NULL
	    if (update.getPrice() != null) {
	        product.setPrice(update.getPrice());
	      }
	      if (update.getQuantity() != 0) {
	        product.setQuantity(update.getQuantity());
	      }
	      Product savedProduct = productRepository.save(product);
	      return ResponseEntity.ok(savedProduct);
	    }
	}

	