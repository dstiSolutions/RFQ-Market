import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RFQRunnable implements Runnable {
        private PriceProcess pProcess;
        private OrderBook oBook;

        RFQRunnable(PriceProcess priceProcess, OrderBook orderBook){
            oBook = orderBook;
            pProcess = priceProcess;
        }

        @Override
        public void run(){
            System.out.println("RFQThread started");

            Random rand = new Random();

            double curPrice = pProcess.queryPrice();
            int priceMaxHigh = 1000;
            int priceMinLow = -1000;

            int quantityMinimum = 0;
            int quantityMaximum = 1000;

            int numberOfOrders = 30;

            int i = 0;
            while (i < numberOfOrders){
                int randLimit = rand.nextInt(2001) - 1000 + (int)curPrice;
                int randQuantity = rand.nextInt(quantityMaximum+1);
                int randSign = rand.nextInt(3) - 1; // randSign range between [-1,1]
                int randTime = rand.nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(randTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // enforce randSign to be +1 or -1 in order to correspond with buy/sell
                while (randSign == 0){
                    randSign = rand.nextInt(3) - 1; // check this is wrong
                }

                sendRequest(randSign, randQuantity);

                i++;
            }
        }

        public double sendRequest(int buyOrSell, double quantity) {
            if (buyOrSell == 1) {
                return oBook.getBuyPrices().get(Math.floor(quantity / 100) * 100);
            } else if (buyOrSell == -1) {
                return oBook.getSellPrices().get(Math.floor(quantity / 100) * 100);
            } else {
                return -1;
            }
        }
}

