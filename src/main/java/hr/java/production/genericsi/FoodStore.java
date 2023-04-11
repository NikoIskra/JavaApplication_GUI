package hr.java.production.genericsi;

import hr.java.production.model.Edible;
import hr.java.production.model.Item;
import hr.java.production.model.Store;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FoodStore <T extends Edible> extends Store {

    private List<T> edibles;

    public FoodStore(String name, Long id, String webAddress, LinkedHashSet<Item> items, List<T> edibles) {
        super(name, id, webAddress, items);
        this.edibles = edibles;
    }

    public List<T> getEdibles() {
        return edibles;
    }

    public void setEdibles(List<T> edibles) {
        this.edibles = edibles;
    }
}
