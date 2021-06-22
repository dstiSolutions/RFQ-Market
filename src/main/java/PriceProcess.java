import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* PriceProcess class generates a random times series to mimic prices for the product.
 */
public class PriceProcess {
    private double price;
    private ScheduledThreadPoolExecutor stpe;

    PriceProcess() throws InterruptedException {
        System.out.println("Starting Price Process...");
        this.price = 1000;
        stpe = new ScheduledThreadPoolExecutor(2);
        stpe.scheduleAtFixedRate(new UpdateTimeJob(this), 0, 250, TimeUnit.MILLISECONDS);
    }

    public void shutDown(){
        stpe.shutdownNow();
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double newPrice){
        price = newPrice;
    }
}

