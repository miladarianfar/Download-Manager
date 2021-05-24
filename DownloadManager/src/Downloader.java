import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Downloader {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("Please enter Link address : ");
		String url = s.next();
		
		System.out.println("Please enter file location");
		String fileLocation = s.next() + "\\";
		
		String tempFileName = url.substring(url.lastIndexOf('/') + 1, url.length());
		String fileName = tempFileName.replaceAll("%20", " ");
		
		CloseableHttpClient client = HttpClients.createDefault();
		
		HttpGet request = new HttpGet(url.trim());
		
		try {
			
			HttpResponse response = client.execute(request);
			
			int responseCode = response.getStatusLine().getStatusCode();
			System.out.println("Response code : " + responseCode);
			System.out.println("Request URL : " + request.getURI());
			System.out.println("File name : " + fileName);
			
			InputStream in = response.getEntity().getContent();
			
			FileOutputStream out = new FileOutputStream(new File(fileLocation, fileName));
			
			System.out.println("Please wait...");
			System.out.println("Downloading started...");
			int b;
			while((b = in.read()) != -1) {
				out.write(b);
			}
			
			System.out.println("Downloaded Succesfully");
			System.out.println(fileName + " downloaded at " + fileLocation + " " + fileName);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}















