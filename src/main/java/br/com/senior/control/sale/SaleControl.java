package br.com.senior.control.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.senior.model.domain.document.Document;
import br.com.senior.model.domain.document.NoActiveDocumentException;
import br.com.senior.model.domain.product.Product;
import br.com.senior.model.domain.product.ProductNotFoundException;
import br.com.senior.model.repository.document.DocumentRepository;
import br.com.senior.model.repository.product.ProductRepository;

@Controller
public class SaleControl {

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private ProductRepository productRepository;

	private Document document;

	public Document getDocument() {
		return document;
	}

	/**
	 * @param productCode the code of the product
	 * @return the product with the code
	 * @throws ProductNotFoundException
	 *             if no product was found with the code
	 */
	public Product addProduct(long productCode) throws ProductNotFoundException {
		Product productFound = productRepository.findById(productCode).orElse(null);
		if (productFound == null) {
			throw new ProductNotFoundException();
		}

		if (document == null) {
			document = new Document();
		}
		document.addItem(productFound);
		documentRepository.save(document);
		return productFound;
	}

	/**
	 * Confirm's the sale
	 * @throws NoActiveDocumentException  if there is no active sale
	 */
	public void confirm() throws NoActiveDocumentException {
		if(document == null) {
			throw new NoActiveDocumentException();
		}
		
		document.setConfirmed(true);
		documentRepository.save(document);
		document = null;
	}

	/**
	 * Cancel's the sale
	 * @throws NoActiveDocumentException  if there is no active sale
	 */
	public void cancel() throws NoActiveDocumentException {
		if(document == null) {
			throw new NoActiveDocumentException();
		}
		
		document.setConfirmed(false);
		documentRepository.save(document);
		document = null;
	}
	
	/**
	 * @return <b> true</b> if has active sale
	 */
	public boolean hasActiveSale() {
		return document != null;
	}

}
