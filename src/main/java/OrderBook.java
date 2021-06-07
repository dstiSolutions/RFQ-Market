import java.util.HashMap;

public class OrderBook {
    private HashMap<Integer, Integer> buyPrices= new HashMap(); //from price level to quantity
    private HashMap<Integer, Integer> sellPrices= new HashMap();

    OrderBook(){
        reset();
    }

    public void reset(){
        buyPrices.clear();
        sellPrices.clear();
    }

    public void generateOrderBook(Double midPrice){
        int i = 0;
        int startSell = (int)Math.ceil(midPrice);
        while (i < 10){
            sellPrices.put(i+startSell,100+i*100);
            i++;
        }

        int j = 0;
        int startBuy = (int)Math.floor(midPrice);
        while (j < 10){
            sellPrices.put(startBuy-j,100+j*100);
            j++;
        }

    }
}
