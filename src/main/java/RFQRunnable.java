import java.util.Random;
import java.util.concurrent.TimeUnit;

/* RFQRunnable provides implementation for the thread querying
 * the company's OrderBook for the price, given a required quantity.
 */
public class RFQRunnable implements Runnable {
        private PriceProcess pProcess;
        private OrderBook oBook;
        private boolean exit;
        //private Object IllegalArgumentException;

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
                double randQuantity = rand.nextInt(quantityMaximum+1);
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

                String buyOrSell = "";
                if (randSign == 1){
                    buyOrSell = "BUY";
                } else if (randSign == -1) {
                    buyOrSell = "SELL";
                }
                Double response = sendRequestForQuote(buyOrSell, randQuantity);
                Logger.logRFQEvent(buyOrSell, randQuantity, response);
            }
        }

        // Function that sends a request to company's OrderBook
        public Double sendRequestForQuote(String buyOrSell, double quantity) {
            if (buyOrSell == "BUY") {
                // Grabs price for the quantity, rounded up to nearest 100 to correspond with logic in OrderBook
                Double priceForBuyQuantity = oBook.getBuyPrices().get(Math.ceil(quantity / 100) * 100);
                if (priceForBuyQuantity != null) {
                    return priceForBuyQuantity;
                } else {
                    return null; // no price found for that quantity
                }
            } else if (buyOrSell == "SELL"){
                Double priceForSellQuantity = oBook.getSellPrices().get(Math.ceil(quantity / 100) * 100);
                if (priceForSellQuantity != null) { // similar to above
                    return priceForSellQuantity;
                } else {
                    return null;
                }
            } else {
                throw new IllegalArgumentException("Neither BUY nor SELL given");
            }
        }

        public void stop(){
            exit = true;
        }
}

