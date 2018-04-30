package br.com.senior.sales;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.senior.control.sale.SaleControl;
import br.com.senior.model.domain.document.Document;
import br.com.senior.model.domain.document.NoActiveDocumentException;
import br.com.senior.model.domain.product.Product;
import br.com.senior.model.domain.product.ProductNotFoundException;
import br.com.senior.model.repository.document.DocumentRepository;
import br.com.senior.model.repository.product.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class SalesTests {

    @Autowired
    private SaleControl saleControl;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @BeforeClass
    public static void setHeadless() {
        System.setProperty("java.awt.headless", "false");
    }

    /**
     * Criando os produtos que serão utilizados pelas vendas nos testes.
     */
    @Before
    public void prepareProducts() {
        Product bola = new Product(1l, "Bola Nike v2", new BigDecimal("89.90"));
        Product chuteira = new Product(2l, "Chuteira Adidas", new BigDecimal("140"));
        Product meiao = new Product(10l, "Meião Nike", new BigDecimal("14.80"));
        Product camisa = new Product(20l, "Camiseta Seleção Brasileira", new BigDecimal("189.99"));
        this.productRepository.saveAll(Arrays.asList(bola, chuteira, meiao, camisa));
    }

    @Test
    public void testScenario02() throws ProductNotFoundException {
        Product productFound = this.saleControl.addProduct(1l);
        assertEquals(new Long(1), productFound.getId());
        assertEquals("Bola Nike v2", productFound.getDescription());
        assertEquals(new BigDecimal("89.90"), productFound.getPrice());
    }

    @Test(expected = ProductNotFoundException.class)
    public void testScenario03() throws ProductNotFoundException {
        this.saleControl.addProduct(3l);
    }

    @Test
    public void testScenario04() throws ProductNotFoundException {
        assertFalse(saleControl.hasActiveSale());

        saleControl.addProduct(10l);

        // Após o primeiro item, o documento é gerado.
        assertEquals(1l, documentRepository.count());

        saleControl.addProduct(20l);

        // Garantindo que o documento foi persistido na base, e respeitando a SEQUENCE.
        Document databaseFirstDocument = documentRepository.findById(1l).get();
        assertFalse(databaseFirstDocument.isConfirmed());

        List<Product> items = saleControl.getDocument().getItems();
        assertEquals(2, items.size());
        assertEquals("Meião Nike", items.get(0).getDescription());
        assertEquals("Camiseta Seleção Brasileira", items.get(1).getDescription());
    }

    @Test
    public void testScenario05() throws ProductNotFoundException {
        saleControl.addProduct(20l);
        saleControl.addProduct(2l);
        saleControl.addProduct(10l);

        Document databaseFirstDocument = documentRepository.findById(1l).get();
        assertEquals(new BigDecimal("344.79"), databaseFirstDocument.getTotal());

        assertEquals(1l, documentRepository.count());
    }

    @Test
    public void testScenario06() throws ProductNotFoundException {
        // Ainda não foi confirmada nenhuma venda, logo, não há documento/itens
        assertFalse(saleControl.hasActiveSale());

        saleControl.addProduct(2l);

        assertEquals(new Long(1), saleControl.getDocument().getNumber());

        // Garantindo que está persistindo e respeitando a SEQUENCE
        Document persistedDocument = this.documentRepository.findById(1l).get();
        assertEquals(new Long(1), persistedDocument.getNumber());

    }

    @Test
    public void testScenario07() throws ProductNotFoundException, NoActiveDocumentException {
        saleControl.addProduct(1l);
        saleControl.addProduct(2l);
        saleControl.addProduct(10l);

        assertTrue(saleControl.hasActiveSale());
        assertEquals(3, saleControl.getDocument().getItems().size());

        saleControl.confirm();
        assertFalse(saleControl.hasActiveSale());

        // Assegurando que o documento foi confirmado/gerado
        Document persistedDocument = this.documentRepository.findById(1l).get();
        assertEquals(new Long(1), persistedDocument.getNumber());
        assertTrue(persistedDocument.isConfirmed());
        assertEquals(new BigDecimal("244.70"), persistedDocument.getTotal());
    }

    @Test
    public void testScenario08() throws ProductNotFoundException, NoActiveDocumentException {
        saleControl.addProduct(2l);
        saleControl.addProduct(10l);

        saleControl.cancel();
        assertFalse(saleControl.hasActiveSale());

        // Assegurando que o documento foi confirmado/gerado
        Document persistedDocument = this.documentRepository.findById(1l).get();
        assertEquals(new Long(1), persistedDocument.getNumber());
        assertFalse(persistedDocument.isConfirmed());
        assertEquals(new BigDecimal("154.80"), persistedDocument.getTotal());

    }

}
