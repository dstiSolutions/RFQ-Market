import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting Program...");

        // Shared PriceProcess and OrderBook
        PriceProcess priceProcess = new PriceProcess();
        OrderBook orderBook = new OrderBook();

        PricingRunnable pricingRunnable = new PricingRunnable(priceProcess, orderBook);
        Thread pricingThread = new Thread(pricingRunnable);
        pricingThread.start();

        RFQRunnable rfqRunnable = new RFQRunnable(priceProcess, orderBook);
        Thread rfqThread = new Thread(rfqRunnable);
        rfqThread.start();

        Thread.sleep(200000);
        pricingRunnable.stop();
        rfqRunnable.stop();
        pricingThread.join();
        rfqThread.join();
    }
}

