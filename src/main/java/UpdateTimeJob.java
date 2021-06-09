import java.util.Random;

/* UpdateTimeJob provides implementation for the thread updating
 * the random price process.
 */
class UpdateTimeJob implements Runnable {
    private PriceProcess curProcess;

    UpdateTimeJob(PriceProcess priceProcess) {
        curProcess = priceProcess;
    }

    public void run() {
        Random rand = new Random();
        int nextStep = rand.nextInt(3) - 1;
        if ((curProcess.getPrice() + (double) nextStep) > 0) {
            curProcess.setPrice(curProcess.getPrice() + (double) nextStep);
        } else {
            curProcess.setPrice(0.0); // to stop price going below 0
        }
    }
}
