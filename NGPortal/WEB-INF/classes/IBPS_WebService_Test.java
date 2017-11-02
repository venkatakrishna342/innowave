import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 */

/**
 * @author Admin
 *
 */
public class IBPS_WebService_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
            URL url = new URL("http://10.10.46.166:8080/SJ_Pre_Matric_RemarksHistoryWS/webresources/genericPreMatric/post_VerificationHistory");
            // URL url = new URL("http://10.10.46.165:8080/FeeCalculationEngine/FeeEngine");
            //URL url = new URL("http://10.10.46.165:8080/NGPortalWS/portal/dbtPortal/setNotification");
           
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"regNo\":\"SJ-0000008576-process\", \"schemeId\":\"3\", \"roleId\":\"1\"}";
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            System.out.println(conn.getResponseCode() + " " +HttpURLConnection.HTTP_CREATED);
            if (conn.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
                  throw new RuntimeException("Failed : HTTP error code : "
                         + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                         (conn.getInputStream())));

            String output, jsonOutput=null;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                  jsonOutput = output;
            }
            
            System.out.println("Output of iii : "+jsonOutput);
            conn.disconnect();

		} catch (MalformedURLException e) {
		     e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
	}

}
