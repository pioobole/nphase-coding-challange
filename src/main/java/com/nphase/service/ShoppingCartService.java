package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;

public class ShoppingCartService {

    private static final int MIN_QUANTITY_FOR_DISCOUNT = 3;
    private static final double DISCOUNT_PERCENTAGE = 10;


    public BigDecimal calculateTotalPriceWithDiscount(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts()
                .stream()
                .map(product -> applyDiscountIfApplicable(product)
                        .multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal applyDiscountIfApplicable(Product product) {
        if (product.getQuantity() >= MIN_QUANTITY_FOR_DISCOUNT) {
            return product.getPricePerUnit().multiply(BigDecimal.valueOf((100 - DISCOUNT_PERCENTAGE) / 100));
        }
        return product.getPricePerUnit();
    }
}
