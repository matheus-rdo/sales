package br.com.senior.admin;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.senior.control.admin.AdminControl;
import br.com.senior.model.domain.document.Document;
import br.com.senior.model.domain.product.InvalidProductException;
import br.com.senior.model.domain.product.Product;
import br.com.senior.model.repository.document.DocumentRepository;
import br.com.senior.model.repository.product.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdminTests {

	@Autowired
	private AdminControl adminControl;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private DocumentRepository documentRepository;

	@BeforeClass
	public static void setHeadless() {
		System.setProperty("java.awt.headless", "false");
	}

	@Test
	public void testScenario001() throws InvalidProductException {
		Product product = new Product(4l, "Meião Nike", new BigDecimal("14.80"));
		adminControl.saveProduct(product);
		
		Product databaseProduct = productRepository.findById(4l).get();
		assertEquals(new Long(4), databaseProduct.getId());
		assertEquals("Meião Nike", databaseProduct.getDescription());
		assertEquals(new BigDecimal("14.80"), databaseProduct.getPrice());
	}
	
	@Test
	public void testScenario009() {
		Product bola = new Product(1l, "Bola Nike v2", new BigDecimal("89.90"));
		Product chuteira = new Product(2l, "Chuteira Adidas", new BigDecimal("140"));
		Product meiao = new Product(10l, "Meião Nike", new BigDecimal("14.80"));
		Product camisa = new Product(20l, "Camiseta Seleção Brasileira", new BigDecimal("189.99"));
		this.productRepository.saveAll(Arrays.asList(bola, chuteira, meiao, camisa));
		
		//3 Documentos de vendas confirmadas.
		Document document1 = new Document();
		document1.addItem(bola);
		document1.addItem(camisa);
		document1.addItem(chuteira);
		document1.setConfirmed(true);
		
		Document document2 = new Document();
		document2.addItem(bola);
		document2.addItem(camisa);
		document2.setConfirmed(true);
		
		Document document3 = new Document();
		document3.addItem(bola);
		document3.addItem(camisa);
		document3.addItem(chuteira);
		document3.addItem(meiao);
		document3.setConfirmed(true);
		
		//2 Documentos de vendas não confirmadas.
		Document document4 = new Document();
		document4.addItem(bola);
		document4.addItem(camisa);
		
		Document document5 = new Document();
		document5.addItem(meiao);
		document5.addItem(chuteira);
		documentRepository.saveAll(Arrays.asList(document1,document2,document3,document4,document5));
		
		BigDecimal expectedTotal = document1.getTotal().add(document2.getTotal()).add(document3.getTotal());
		assertEquals(expectedTotal, adminControl.getTotalSold());
	}
	

}
