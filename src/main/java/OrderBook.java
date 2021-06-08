import java.util.HashMap;

public class OrderBook {
    private HashMap<Integer, Integer> buyPrices= new HashMap(); //from quantity to price
    private HashMap<Integer, Integer> sellPrices= new HashMap();

    OrderBook(){
        synchronized (this){
            reset();
        }
    }

    OrderBook(Double midPrice){
        synchronized (this) {
            //reset();
            generateOrderBook2(midPrice);
        }
    }

    public void reset(){
        buyPrices.clear();
        sellPrices.clear();
    }

    public void generateOrderBook(Double midPrice){
        int i = 0;
        int startSell = (int) Math.ceil(midPrice);
        while (i < 10){
            sellPrices.put(100+i*100, i+startSell);
            i++;
        }

        int j = 0;
        int startBuy = (int) Math.floor(midPrice);
        while (j < 10 && startBuy-j >0){
            buyPrices.put(100+j*100, startBuy-j);
            j++;
        }
    }

    public synchronized void generateOrderBook2(Double midPrice){
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
        while (j < 10 && startBuy-j >0){
            buyPrices.put(100+j*100, startBuy-j);
            j++;
        }
    }

    public synchronized HashMap<Integer, Integer> getBuyPrices(){
        return buyPrices;
    }

    public synchronized HashMap<Integer, Integer> getSellPrices(){
        return sellPrices;
    }
}
