import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import JSON.JSONObject;

public class TESTUPDATE {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JSONObject jsonObject = null;
		try {
			String output = null;

			URL url = new URL(
					"http://192.168.42.69:8090/NGPortalWS/portal/dbtPortal/updateApprovedSchemesStatusStageCode");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			//String stageflag[] = { "institute", "department" };
			String input = "{ \"appid\" : \"POSLO010170000000908\",\"decesion\":\"Reject\",\"stageflag\":\"department\" }";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
