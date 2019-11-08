package service;

import Model.Item;
import Model.TaxCalculator;
import util.Classifier;
import util.InputParser;
import util.OuputParser;

import java.io.FileNotFoundException;
import java.util.List;

public class TaxCalculatorServiceImpl implements TaxCalculatorService {

    private static class TaxCalculatorServiceImplLoader {
        private static final TaxCalculatorServiceImpl INSTANCE = new TaxCalculatorServiceImpl();
    }

    private TaxCalculatorServiceImpl() {
        if (TaxCalculatorServiceImplLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static TaxCalculatorServiceImpl getInstance() {
        return TaxCalculatorServiceImplLoader.INSTANCE;
    }

    @Override
    public void calculateTax(String filePath) throws FileNotFoundException {
        InputParser reader = read(filePath);
        List<Item> items = classifyItems(reader.readInput(), reader.readExclusions());
        TaxCalculator taxCalculator = calculate(items);
        taxCalculator.computeTax();
        print(items, taxCalculator.getSalesTaxes(), taxCalculator.getTotalAll());
    }

    private InputParser read(String filePath){
        InputParser reader = new InputParser(filePath);
        return reader;
    }

    private List<Item> classifyItems(List<String> inputText, List<String> exclusions){
        return new Classifier(inputText, exclusions).classifyText();
    }

    private TaxCalculator calculate(List<Item> items){
        return new TaxCalculator(items);
    }

    private void print(List<Item> items, double salesTaxes, double total){
        OuputParser consolePrinter = new OuputParser(items, salesTaxes, total);
        consolePrinter.print();
    }
}
