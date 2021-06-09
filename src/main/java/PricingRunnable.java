public class PricingRunnable implements Runnable {
        private PriceProcess pProcess;
        private OrderBook oBook;

        PricingRunnable(PriceProcess priceProcess, OrderBook orderBook){
            pProcess = priceProcess;
            oBook = orderBook;
        }

        @Override
        public void run(){
            System.out.println("PricingThread started");
            while (true){
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
}
