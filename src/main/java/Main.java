
public class Main {
    private static Object RFQRunnable;
    private static Object PricingRunnable;

    public static void main(String[] args){
        System.out.println("Starting Program...");
        PriceProcess priceProcess = new PriceProcess();

        Thread rfqThread = new Thread(RFQRunnable);
        rfqThread.start();

        Thread pricingThread = new Thread(PricingRunnable);
        pricingThread.start();

    }
}
