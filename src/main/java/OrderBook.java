import java.util.HashMap;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;

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
        int startSell = (int)ceil(midPrice);
        while (i < 10){
            sellPrices.put(i+startSell,100+i*100);
            i++;
        }

        int j = 0;
        int startBuy = (int)floor(midPrice);
        while (j < 10){
            sellPrices.put(startBuy-j,100+j*100);
            j++;
        }

    }
}
