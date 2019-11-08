import service.TaxCalculatorService;
import service.TaxCalculatorServiceImpl;

public class TaxMain {

    private static TaxCalculatorService taxCalculatorService = TaxCalculatorServiceImpl.getInstance();

    public static void main(String [] args){
        if (args.length > 0){
            try {
                taxCalculatorService.calculateTax(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Specify file name after the command: java TaxMain.java <filePath>");
        }
    }
}
