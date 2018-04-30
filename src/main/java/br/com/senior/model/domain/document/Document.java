package br.com.senior.model.domain.document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import br.com.senior.model.domain.product.Product;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long number;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private boolean confirmed;

    @ManyToMany
    private List<Product> items;

    public Document() {
        this.items = new ArrayList<>();
        total = new BigDecimal(0);
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public List<Product> getItems() {
        return items;
    }

    public void addItem(Product item) {
        this.items.add(item);
        this.total = this.total.add(item.getPrice());
    }

}
