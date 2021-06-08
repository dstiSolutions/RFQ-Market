import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PriceProcess {
    private double price;

    PriceProcess() throws InterruptedException {
        System.out.println("Starting Price Process...");
        this.price = 1000;
        ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(2);
        stpe.scheduleAtFixedRate(new UpdateTimeJob(this), 0, 250, TimeUnit.MILLISECONDS);

    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double newPrice){
        price = newPrice;
    }
}

