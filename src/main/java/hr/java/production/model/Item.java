package hr.java.production.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.jar.Attributes;

/**
 * class item
 */
public class Item extends NamedEntity implements Serializable {

    private Discount discountAmount;
    private Category category;
    private BigDecimal width, height, length, productionCost, sellingPrice;

    /**
     * konstruktor Item
     * @param name
     * @param id
     * @param discountAmount
     * @param category
     * @param width
     * @param height
     * @param length
     * @param productionCost
     * @param sellingPrice
     */
    public Item(String name, Long id, Discount discountAmount, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice) {
        super(name, id);
        this.discountAmount = discountAmount;
        this.category = category;
        this.width = width;
        this.height = height;
        this.length = length;
        this.productionCost = productionCost;
        this.sellingPrice = sellingPrice;
    }



    public Discount getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Discount discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(BigDecimal productionCost) {
        this.productionCost = productionCost;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return Objects.equals(discountAmount, item.discountAmount) && Objects.equals(category, item.category) && Objects.equals(width, item.width) && Objects.equals(height, item.height) && Objects.equals(length, item.length) && Objects.equals(productionCost, item.productionCost) && Objects.equals(sellingPrice, item.sellingPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discountAmount, category, width, height, length, productionCost, sellingPrice);
    }

    @Override
    public String toString() {
        return this.getName().toString();
    }
}
