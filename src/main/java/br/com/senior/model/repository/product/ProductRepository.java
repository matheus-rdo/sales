package br.com.senior.model.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senior.model.domain.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
