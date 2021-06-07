
public class PricingRunnable implements Runnable {
        private PriceProcess pProcess;

        PricingRunnable(PriceProcess priceProcess){
            pProcess = priceProcess;
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
                double currentPrice = pProcess.queryPrice();
                OrderBook currentBook = new OrderBook(currentPrice);
            }
        }
}
