import java.util.HashMap;

/* OrderBook Class to provide a series of prices and quantities offered to clients.
 * The class is synchronized for concurrency reasons.
 */
public class OrderBook {
    private HashMap<Integer, Integer> buyPrices= new HashMap<>(); //from quantity to price
    private HashMap<Integer, Integer> sellPrices= new HashMap<>();

    OrderBook(){ }

    // Given a midPrice, this function generates an OrderBook of prices and quantities to offer
    public synchronized void generateOrderBook(Double midPrice){
        buyPrices.clear();
        sellPrices.clear();

        int i = 0;
        int startSell = (int) Math.ceil(midPrice);
        while (i < 10){
            sellPrices.put(100+i*100, i+startSell);
            Logger.logPricing("sell", i+startSell, 100+i*100); //logs different prices and quantities being offered
            i++;
        }

        int j = 0;
        int startBuy = (int) Math.floor(midPrice);
        while (j < 10 && startBuy-j > 0){
            buyPrices.put(100+j*100, startBuy-j);
            Logger.logPricing("buy", startBuy-j,100+j*100);
            j++;
        }
    }

    public synchronized HashMap<Integer, Integer> getBuyPrices(){
        return new HashMap(buyPrices); //return copy of hashmap for safety
    }

    public synchronized HashMap<Integer, Integer> getSellPrices(){
        return new HashMap(sellPrices); //same as above
    }
}
