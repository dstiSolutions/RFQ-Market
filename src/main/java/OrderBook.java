import java.util.HashMap;

/* OrderBook Class to provide a series of prices and quantities offered to clients.
 * The class is synchronized for concurrency reasons.
 */
public class OrderBook {
    private HashMap<Double, Double> buyPrices; //from quantity to price
    private HashMap<Double, Double> sellPrices;

    OrderBook(){
        buyPrices = new HashMap<>();
        sellPrices = new HashMap<>();
    }

    // Given a midPrice, this function generates an OrderBook of prices and quantities to offer
    public void generateOrderBook(Double midPrice){
        HashMap<Double,Double> newBuyPrices = new HashMap<>();
        HashMap<Double,Double> newSellPrices = new HashMap<>();

        int i = 0;
        Double startSell = Math.ceil(midPrice);
        while (i < 10){
            newSellPrices.put(100+i*100.0, i+startSell);
            Logger.logPricing("sell", i+startSell, 100+i*100.0); //logs different prices and quantities being offered
            i++;
        }

        int j = 0;
        Double startBuy = Math.floor(midPrice);
        while (j < 10 && startBuy-j > 0){
            newBuyPrices.put(100+j*100.0, startBuy-j);
            Logger.logPricing("buy", startBuy-j,100+j*100.0);
            j++;
        }

        synchronized (this){
            buyPrices = newBuyPrices;
            sellPrices = newSellPrices;
        }
    }

    public synchronized HashMap<Double, Double> getBuyPrices(){
        return new HashMap(buyPrices); //return copy of hashmap for safety
    }

    public synchronized HashMap<Double, Double> getSellPrices(){
        return new HashMap(sellPrices); //same as above
    }
}
