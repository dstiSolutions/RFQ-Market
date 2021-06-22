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
                TradeDirection tradeDirection = randSign == 1 ? TradeDirection.BUY : TradeDirection.SELL;

                System.out.printf("tradeDirection: %s, randQuantity: %s\n", tradeDirection, randQuantity);
                double response = sendRequestForQuote(tradeDirection, randQuantity);
                System.out.printf("response: %s", response);
                Logger.logRFQEvent(tradeDirection, randQuantity, response);
            }
        }

        // Function that sends a request to company's OrderBook
        public Double sendRequestForQuote(TradeDirection tradeDirection, double quantity) {
          System.out.println("Inside method\n");
            if (tradeDirection == TradeDirection.BUY) {
              System.out.println("BUY");
                // Grabs price for the quantity, rounded up to nearest 100 to correspond with logic in OrderBook
                Double priceForBuyQuantity = oBook.getBuyPrices().get(Math.ceil(quantity / 100) * 100);
                if (priceForBuyQuantity == null) {
                  priceForBuyQuantity = 0.0;
                }
                System.out.printf("returning priceForBuyQuantity: %s\n", priceForBuyQuantity);
                return priceForBuyQuantity;
            } else {
                Double priceForSellQuantity = oBook.getSellPrices().get(Math.ceil(quantity / 100) * 100);
                if (priceForSellQuantity == null) {
                  priceForSellQuantity = 0.0;
                }
                System.out.printf("returning priceForSellQuantity: %s\n", priceForSellQuantity);
                return priceForSellQuantity;
            }
        }

        public void stop(){
            exit = true;
        }
}

