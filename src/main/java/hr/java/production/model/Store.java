package hr.java.production.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * class store
 */
public class Store extends NamedEntity implements Serializable {

    private String  webAddress;
    private Set<Item> items;

    /**
     * konstruktor store
     * @param name
     * @param id
     * @param webAddress
     * @param items
     */
    public Store(String name, Long id, String webAddress, Set<Item> items) {
        super(name, id);
        this.webAddress = webAddress;
        this.items = items;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Store store = (Store) o;
        return Objects.equals(webAddress, store.webAddress) && Objects.equals(items, store.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), webAddress, items);
    }

    @Override
    public String toString() {
        return getName().toUpperCase();
    }
}
