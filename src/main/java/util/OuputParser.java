package util;

import Model.Item;

import java.util.List;

public class OuputParser {

    private final List<Item> items;

    private final double total;

    private final double salesTaxes;

    private final String SALEX_TAXES_PREFIX = "Sales Taxes: ";

    private final String TOTAL_PREFIX = "Total: ";

    public OuputParser(List<Item> items, double salesTaxes, double total){
        this.items = items;
        this.salesTaxes = salesTaxes;
        this.total = total;
    }

    public void print(){
        for (Item item : items) {
            System.out.println(item.toString());
        }
        System.out.println(SALEX_TAXES_PREFIX + String.valueOf(salesTaxes));
        System.out.println(TOTAL_PREFIX + String.valueOf(total));
    }
}
