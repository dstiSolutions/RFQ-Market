import java.util.HashMap;

public class OrderBook {
    private HashMap<Integer, Integer> buyPrices= new HashMap(); //from quantity to price
    private HashMap<Integer, Integer> sellPrices= new HashMap();

    OrderBook(){ }

    public synchronized void generateOrderBook(Double midPrice){
        buyPrices.clear();
        sellPrices.clear();

        int i = 0;
        int startSell = (int) Math.ceil(midPrice);
        while (i < 10){
            sellPrices.put(100+i*100, i+startSell);
            i++;
        }

        int j = 0;
        int startBuy = (int) Math.floor(midPrice);
        while (j < 10 && startBuy-j > 0){
            buyPrices.put(100-j*100, startBuy-j);
            j++;
        }
    }

    public synchronized HashMap<Integer, Integer> getBuyPrices(){
        return new HashMap(buyPrices);
    }

    public synchronized HashMap<Integer, Integer> getSellPrices(){
        return new HashMap(sellPrices);
    }
}
