package com.nphase.service;


import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class ShoppingCartServiceTest {
    private final ShoppingCartService service = new ShoppingCartService();


    @Test
    public void calculatesDiscountedPrice() {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.0), 4)
        ));
        BigDecimal expectedPrice = BigDecimal.valueOf(18.0);

        BigDecimal actualPrice = service.calculateTotalPriceWithDiscount(cart);

        Assertions.assertEquals(0, expectedPrice.compareTo(actualPrice));
    }

}