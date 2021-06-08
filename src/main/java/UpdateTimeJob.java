import java.util.Random;

class UpdateTimeJob implements Runnable {
    private PriceProcess curProcess;

    UpdateTimeJob(PriceProcess priceProcess) {
        curProcess = priceProcess;
    }

    public void run() {
        // your task
        Random rand = new Random();
        int nextStep = rand.nextInt(3) - 1;
        if ((curProcess.getPrice() + (double) nextStep) >= 0) {
            curProcess.setPrice(curProcess.getPrice() + (double) nextStep);
        } else {
            curProcess.setPrice(0.0);
        }
    }
}
