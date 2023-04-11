package hr.java.production.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * class laptop
 */
public class Laptop extends Item implements Technical, Serializable {
    int garancija;

    /**
     * konstruktor laptop
     * @param name
     * @param id
     * @param discountAmount
     * @param category
     * @param width
     * @param height
     * @param length
     * @param productionCost
     * @param sellingPrice
     * @param garancija
     */
    public Laptop(String name, Long id, Discount discountAmount, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, int garancija) {
        super(name, id, discountAmount, category, width, height, length, productionCost, sellingPrice);
        this.garancija = garancija;
    }

    public int getGarancija() {
        return garancija;
    }

    public void setGarancija(int garancija) {
        this.garancija = garancija;
    }

    /**
     * metoda za vracanje garancije
     * @return
     */
    @Override
    public int trajanjeGarancija() {
        return garancija;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Laptop laptop = (Laptop) o;
        return garancija == laptop.garancija;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), garancija);
    }
}
