package br.com.senior.model.domain.product;

public class ProductNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public ProductNotFoundException() {
        super("Produto não cadastrado");
    }

}
