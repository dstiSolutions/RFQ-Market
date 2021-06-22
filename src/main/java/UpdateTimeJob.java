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
        double nextPotentialPrice = curProcess.getPrice() + (double) nextStep;
        curProcess.setPrice(nextPotentialPrice);
    }
}
