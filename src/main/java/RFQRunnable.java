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
            int quantityMaximum = 1000;
            int numberOfOrders = 50;

            int i = 0;
            while (i < numberOfOrders){
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


                double response = sendRequest(randSign, randQuantity);
                Logger.logRFQEvent(randSign, randQuantity, response);

                i++;
            }
        }

        public double sendRequest(int buyOrSell, double quantity) {
            if (buyOrSell == 1) {
                if (oBook.getBuyPrices().get((int)Math.floor(quantity / 100) * 100) != null) {
                    return oBook.getBuyPrices().get((int)Math.floor(quantity / 100) * 100);
                } else {
                    return -1;
                }
            } else if (buyOrSell == -1) {
                if (oBook.getSellPrices().get((int)Math.ceil(quantity / 100) * 100) != null) {
                    return oBook.getSellPrices().get((int)Math.ceil(quantity / 100) * 100);
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
}

