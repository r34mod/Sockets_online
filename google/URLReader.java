package google;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class URLReader {
	 public static void main(String[] args) throws Exception {
	        URL verisign = new URL("https://www.google.com/");
	        BufferedReader in = new BufferedReader(
	                                new InputStreamReader(
	                                verisign.openStream()));

	        String inputLine;

	        while ((inputLine = in.readLine()) != null)
	            System.out.println(inputLine);

	        in.close();
	    }
}
