import java.util.Random;
import java.util.concurrent.TimeUnit;

/* RFQRunnable provides implementation for the thread querying
 * the company's OrderBook for the price, given a required quantity.
 */
public class RFQRunnable implements Runnable {
        private PriceProcess pProcess;
        private OrderBook oBook;
        private boolean exit;

        // Inject in the Main's price process and shared OrderBook
        RFQRunnable(PriceProcess priceProcess, OrderBook orderBook){
            oBook = orderBook;
            pProcess = priceProcess;
        }

        @Override
        public void run(){
            System.out.println("RFQThread started");

            Random rand = new Random();
            int quantityMaximum = 1000; // Maximum quantity that can be requested by the client

            while(!exit){
                int randQuantity = rand.nextInt(quantityMaximum+1);
                int randSign = rand.nextInt(3) - 1; // randSign range between [-1,1]
                int randTime = rand.nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(randTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Enforce randSign to be +1 or -1 in order to correspond to buy/sell
                while (randSign == 0){
                    randSign = rand.nextInt(3) - 1;
                }
                double response = sendRequest(randSign, randQuantity);
                Logger.logRFQEvent(randSign, randQuantity, response);
            }
        }

        // Function that sends a request to company's OrderBook
        public double sendRequest(int buyOrSell, double quantity) {
            if (buyOrSell == 1) {
                // Grabs price for the quantity, rounded up to nearest 100 to correspond with logic in OrderBook
                if (oBook.getBuyPrices().get((int)Math.ceil(quantity / 100) * 100) != null) {
                    return oBook.getBuyPrices().get((int)Math.ceil(quantity / 100) * 100);
                } else {
                    return -1; // error
                }
            } else if (buyOrSell == -1) {
                if (oBook.getSellPrices().get((int)Math.ceil(quantity / 100) * 100) != null) { // similar to above
                    return oBook.getSellPrices().get((int)Math.ceil(quantity / 100) * 100);
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }

        public void stop(){
            exit = true;
        }
}

