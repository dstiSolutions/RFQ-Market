
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

/* Logger Class to log pricing, RFQ, mid price events.
 * Writes currently to a txt file: logfile.txt.
 */
public class Logger {

    public Logger() throws IOException { }

    public static void logPricing(String buyOrSell, Double price, Double quantity) {
        try{
            Instant time = Instant.now();
            String priceStr = Double.toString(price);
            String quantityStr = Double.toString(quantity);
            String now = time.toString();

            FileWriter myWriter = new FileWriter("logfile.txt", true); // true appends to original file

            myWriter.write("Time: " + now + " , " + "Type: Pricing"+ " , " + "BuyOrSell: " + buyOrSell + " , " + "Price: " + priceStr +  " , " + "Quantity: "+ quantityStr + System.getProperty( "line.separator" ));
            myWriter.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void logRFQEvent(TradeDirection tradeDirection, int quantity, Double response) {
        try{
            Instant now = Instant.now();
            String quantityStr = Double.toString(quantity);
            String priceResponse = Double.toString(response);
            String buyOrSell = tradeDirection.toString();

            FileWriter myWriter = new FileWriter("logfile.txt", true); // appends to original file

            myWriter.write("Time: " + now + " , " + "Type: RFQ"+ " , " + "BuyOrSell: " + buyOrSell + " , " + "Quantity: " + quantityStr +  " , " + "Price Response: "+ priceResponse + System.getProperty( "line.separator" ));
            myWriter.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void logPrices(double midPrice) {
        try{
            Instant now = Instant.now();
            String midPriceStr = Double.toString(midPrice);

            FileWriter myWriter = new FileWriter("logfile.txt", true); //appends to original file

            myWriter.write("Time: " + now + " , " + "Type: MidPrice"+ " , " + "Price: " + midPriceStr + System.getProperty( "line.separator" ));
            myWriter.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}