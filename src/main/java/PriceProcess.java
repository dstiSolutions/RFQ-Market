import java.util.Random;

public class PriceProcess {
    private double price;

    PriceProcess() throws InterruptedException {
        System.out.println("Starting Price Process...");
        this.price = 1000;
        this.update();

    }

    public void update() throws InterruptedException {
        while (true){
            wait(500);
            updatePrice();
        }
    }

    public double updatePrice(){
        Random rand = new Random();
        int nextStep = rand.nextInt(3) - 1;
        if ((price += (double)nextStep) >= 0) {
            price += (double)nextStep;
        } else {
            price = 0.0;
        }
        return price;
    }

    public double queryPrice(){
        return price;
    }

}