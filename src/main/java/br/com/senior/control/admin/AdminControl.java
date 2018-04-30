package br.com.senior.control.admin;

import br.com.senior.model.domain.product.InvalidProductException;
import br.com.senior.model.domain.product.Product;
import br.com.senior.model.repository.document.DocumentRepository;
import br.com.senior.model.repository.product.ProductRepository;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminControl {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private DocumentRepository documentRepository;
 

    public void saveProduct(Product product) throws InvalidProductException{
        StringBuilder exceptionBuilder = new StringBuilder();
        if(product.getId() != null){
            Product productFound = productRepository.findById(product.getId()).orElse(null);
            if(productFound != null){
                exceptionBuilder.append("Já existe um produto com este mesmo código \n");
            }
        } else {
            exceptionBuilder.append("Especifique o código do produto \n");
        }
        if(product.getDescription() == null || product.getDescription().trim().isEmpty()){
            exceptionBuilder.append("É obrigatório informar uma descrição \n");
        }
        
        if(product.getPrice() == null){
            exceptionBuilder.append("É obrigatório informar um preço válido \n");
        }
        
        String exceptionsMessage = exceptionBuilder.toString();
        if(!exceptionsMessage.isEmpty()){
            throw new InvalidProductException(exceptionsMessage);
        }
        
        this.productRepository.save(product);
    }


	public BigDecimal getTotalSold() {
		return documentRepository.totalSoldSum();
	}
    
}
