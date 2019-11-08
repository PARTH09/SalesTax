package Model;

import java.util.List;

public class TaxCalculator {

    private final double SALES_TAX_RATE = 0.10;

    private final double IMPORT_TAX_RATE = 0.05;

    private final double NEAREST_APPROX = 1.0d/0.05;

    private final double ROUNDING_APPROX = 100;

    private final List<Item> items;

    private double total;

    private double salesTaxes;

    public TaxCalculator(List<Item> items){
        this.items = items;
    }

    public double getTotalAll() {
        return Math.round((total + salesTaxes) * ROUNDING_APPROX) / ROUNDING_APPROX;
    }

    public double getSalesTaxes() {
        return salesTaxes;
    }

    public void computeTax(){
        for (Item item : items) {
            if (!item.isExcluded())
                item.setSalesTax(roundTax(getSalesTax(item.getPrice())));
            if (item.isImported())
                item.setImportTax(roundTax(getImportTax(item.getPrice())));
            total = total + item.getPrice();
            salesTaxes = salesTaxes + item.getTotalTax();
        }
    }

    private double roundTax(double amount){
        return (double)Math.ceil(amount * NEAREST_APPROX)/NEAREST_APPROX;
    }

    private double getSalesTax(double price){
        return price * SALES_TAX_RATE;
    }

    private double getImportTax(double price){
        return price * IMPORT_TAX_RATE;
    }
}
