public class RFQRunnable implements Runnable {
        private PriceProcess pProcess;

        RFQRunnable(PriceProcess priceProcess){
            pProcess = priceProcess;
        }

        @Override
        public void run(){
            System.out.println("RFQThread started");
        }
    }

