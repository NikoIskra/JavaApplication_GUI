package hr.java.production.sort;

import hr.java.production.model.Item;

import java.math.BigDecimal;
import java.util.Comparator;

public class ProductionSorter implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        BigDecimal cijenaPrviItem=o1.getSellingPrice();
        BigDecimal cijenaDrugiItem=o2.getSellingPrice();
        if (cijenaPrviItem.compareTo(cijenaDrugiItem)>0)
            return 1;
        else if (cijenaPrviItem.compareTo(cijenaDrugiItem)<0)
            return -1;
        else
            return 0;
    }
}
