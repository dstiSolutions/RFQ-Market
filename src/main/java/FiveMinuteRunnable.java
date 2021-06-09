
/* FiveMinuteRunnable provides implementation for the thread allowing
 * the program to terminate after five minutes of running.
 */
public class FiveMinuteRunnable implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

}