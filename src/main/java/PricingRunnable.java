
public class PricingRunnable implements Runnable {

        @Override
        public void run(){
            System.out.println("PricingThread started");
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                double currentPrice = Outer.this.priceProcess.queryPrice();
                OrderBook currentBook = new OrderBook(currentPrice);
            }
        }
}
