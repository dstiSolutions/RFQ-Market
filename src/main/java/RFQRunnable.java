import java.util.Random;
import java.util.concurrent.TimeUnit;

/* RFQRunnable provides implementation for the thread querying
 * the company's OrderBook for the price, given a required quantity.
 */
public class RFQRunnable implements Runnable {
        private PriceProcess pProcess;
        private OrderBook oBook;
        private boolean exit;
        private enum TradeDirection {BUY, SELL};

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
                TradeDirection tradeDirection;
                if (randSign == 1){
                    tradeDirection = TradeDirection.BUY;
                } else {
                    tradeDirection = TradeDirection.SELL;
                }
                double response = sendRequestForQuote(tradeDirection, randQuantity);
                Logger.logRFQEvent(tradeDirection, randQuantity, response);
            }
        }

        // Function that sends a request to company's OrderBook
        public Double sendRequestForQuote(TradeDirection tradeDirection, double quantity) {
            if (tradeDirection == TradeDirection.BUY) {
                // Grabs price for the quantity, rounded up to nearest 100 to correspond with logic in OrderBook
                Double priceForBuyQuantity = oBook.getBuyPrices().get(Math.ceil(quantity / 100) * 100);
                if (priceForBuyQuantity != null) {
                    return priceForBuyQuantity;
                } else {
                    return null; // no price found for that quantity
                }
            } else {
                Double priceForSellQuantity = oBook.getSellPrices().get(Math.ceil(quantity / 100) * 100);
                if (priceForSellQuantity != null) { // similar to above
                    return priceForSellQuantity;
                } else {
                    return null;
                }
            }
        }

        public void stop(){
            exit = true;
        }
}

