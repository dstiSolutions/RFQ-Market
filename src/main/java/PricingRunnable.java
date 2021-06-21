
/* PricingRunnable provides implementation for the thread changing
 * the current mid price.
 */
public class PricingRunnable implements Runnable {
        private PriceProcess pProcess;
        private OrderBook oBook;
        private boolean exit;

        // Inject in the Main's price process and shared OrderBook
        PricingRunnable(PriceProcess priceProcess, OrderBook orderBook){
            pProcess = priceProcess;
            oBook = orderBook;
            exit = false;
        }

        @Override
        public void run(){
            System.out.println("PricingThread started");
            while (!exit){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                double currentPrice = pProcess.getPrice();
                Logger.logPrices(currentPrice);
                oBook.generateOrderBook(currentPrice);
            }
        }

        public void stop(){
            exit = true;
        }


}
