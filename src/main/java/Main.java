
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting Program...");
        PriceProcess priceProcess = new PriceProcess();
        OrderBook orderBook = new OrderBook();

        Runnable pricingRunnable = new PricingRunnable(priceProcess, orderBook);
        Thread pricingThread = new Thread(pricingRunnable);
        pricingThread.start();

        Runnable rfqRunnable = new RFQRunnable(priceProcess, orderBook);
        Thread rfqThread = new Thread(rfqRunnable);
        rfqThread.start();

    }
}
