package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCartService {

    private static final int MIN_QUANTITY_FOR_DISCOUNT = 3;
    private static final double DISCOUNT_PERCENTAGE = 10;


    public BigDecimal calculateTotalPriceWithDiscount(ShoppingCart shoppingCart) {
        Map<String, List<Product>> productsByCategory = shoppingCart.getProducts().stream().collect(Collectors.groupingBy(Product::getCategory));
        return productsByCategory.keySet().stream()
                .map(category -> calculatePrice(productsByCategory.get(category)))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal calculatePrice(List<Product> products) {
        BigDecimal priceBeforeDiscount = products.stream().map(product -> product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        if (getTotalAmountOfProducts(products) >= MIN_QUANTITY_FOR_DISCOUNT) {
            return applyDiscount(priceBeforeDiscount);
        }
        return priceBeforeDiscount;
    }

    private int getTotalAmountOfProducts(List<Product> products) {
        return products.stream().map(Product::getQuantity).reduce(0, Integer::sum);
    }

    private BigDecimal applyDiscount(BigDecimal price) {
        return price.multiply(BigDecimal.valueOf((100 - DISCOUNT_PERCENTAGE) / 100));
    }
}
