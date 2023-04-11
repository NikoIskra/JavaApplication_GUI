package hr.java.production.model;

import java.math.BigDecimal;

/**
 * interface edible
 */
public interface Edible {

    BigDecimal calculateKilocalories();
    BigDecimal calculatePrice();
}
