package hr.java.production.model;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * class burger
 */
public class Burger extends Item implements Edible, Serializable {

    private BigDecimal brojKalorija, weight;

    /**
     * konstruktor burger
     * @param name
     * @param id
     * @param discountAmount
     * @param category
     * @param width
     * @param height
     * @param length
     * @param productionCost
     * @param sellingPrice
     * @param brojKalorija
     * @param weight
     */
    public Burger(String name, Long id, Discount discountAmount, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal brojKalorija, BigDecimal weight) {
        super(name, id, discountAmount, category, width, height, length, productionCost, sellingPrice);
        this.brojKalorija = brojKalorija;
        this.weight = weight;
    }

    public BigDecimal getBrojKalorija() {
        return brojKalorija;
    }

    public void setBrojKalorija(BigDecimal brojKalorija) {
        this.brojKalorija = brojKalorija;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * metoda za izracun kilokalorija
     * @return
     */
    @Override
    public BigDecimal calculateKilocalories() {
        return brojKalorija.multiply(weight);
    }

    /**
     * metoda za izracun cijene
     * @return
     */
    @Override
    public BigDecimal calculatePrice() {
         return getSellingPrice().multiply(weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Burger burger = (Burger) o;
        return Objects.equals(brojKalorija, burger.brojKalorija) && Objects.equals(weight, burger.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), brojKalorija, weight);
    }
}
