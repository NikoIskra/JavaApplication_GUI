package hr.java.production.genericsi;

import hr.java.production.model.Item;
import hr.java.production.model.Store;
import hr.java.production.model.Technical;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TechnicalStore <T extends Technical> extends Store {

    private List<T> technicals;

    public TechnicalStore(String name, Long id, String webAddress, LinkedHashSet<Item> items, List<T> technicals) {
        super(name, id, webAddress, items);
        this.technicals = technicals;
    }

    public List<T> getTechnicals() {
        return technicals;
    }

    public void setTechnicals(List<T> technicals) {
        this.technicals = technicals;
    }
}
