
public class Main {
    public static void main(String[] args){
        System.out.println("Starting Program...");
        PriceProcess priceProcess = new PriceProcess();
        OrderBook orderBook = new OrderBook();

        Runnable rfqRunnable = new RFQRunnable(priceProcess, orderBook);
        Thread rfqThread = new Thread(rfqRunnable);
        rfqThread.start();

        Runnable pricingRunnable = new PricingRunnable(priceProcess, orderBook);
        Thread pricingThread = new Thread(pricingRunnable);
        pricingThread.start();

    }
}
