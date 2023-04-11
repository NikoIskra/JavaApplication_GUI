package hr.java.production.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * class wok
 */
public class Wok extends Item implements Edible, Serializable {

    private BigDecimal brojKalorija, weight;

    /**
     * konstruktor wok
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
    public Wok(String name, Long id, Discount discountAmount, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal brojKalorija, BigDecimal weight) {
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

    @Override
    public BigDecimal calculateKilocalories() {
        return brojKalorija.multiply(weight);
    }

    @Override
    public BigDecimal calculatePrice() {
        return getSellingPrice().multiply(weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Wok wok = (Wok) o;
        return Objects.equals(brojKalorija, wok.brojKalorija) && Objects.equals(weight, wok.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), brojKalorija, weight);
    }
}
