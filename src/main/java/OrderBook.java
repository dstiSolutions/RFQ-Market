import java.util.HashMap;

public class OrderBook {
    private HashMap<Integer, Integer> buyPrices= new HashMap(); //from quantity to price
    private HashMap<Integer, Integer> sellPrices= new HashMap();

    OrderBook(){
        reset();
    }

    OrderBook(Double midPrice){
        reset();
        generateOrderBook(midPrice);
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

    public HashMap<Integer, Integer> getBuyPrices(){
        return buyPrices;
    }

    public HashMap<Integer, Integer> getSellPrices(){
        return sellPrices;
    }
}
