package hr.java.production.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

/**
 * class category
 */
public class Category extends NamedEntity implements Serializable {

    private String description;

    /**
     * konstruktor category
     * @param name
     * @param id
     * @param description
     */
    public Category(String name, Long id, String description) {
        super(name, id);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return Objects.equals(description, category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description);
    }
}
