
public class Main {
    public static void main(String[] args){
        System.out.println("Starting Program...");
        PriceProcess priceProcess = new PriceProcess();

        Runnable rfqRunnable = new RFQRunnable(priceProcess);
        Thread rfqThread = new Thread(rfqRunnable);
        rfqThread.start();

        Runnable pricingRunnable = new PricingRunnable(priceProcess);
        Thread pricingThread = new Thread(pricingRunnable);
        pricingThread.start();

    }
}
