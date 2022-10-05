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
                new Product("Tea", BigDecimal.valueOf(5.3), 2, "drinks"),
                new Product("Coffee", BigDecimal.valueOf(3.5), 2, "drinks"),
                new Product("Cheese", BigDecimal.valueOf(8), 2, "food")
        ));
        BigDecimal expectedPrice = BigDecimal.valueOf(31.84);

        BigDecimal actualPrice = service.calculateTotalPriceWithDiscount(cart);

        Assertions.assertEquals(expectedPrice, actualPrice);
    }

}