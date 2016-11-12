/**
 * 
 */
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.*;
/**
 * @author huanjiayang
 *
 */
public class StwHarParser {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		StwNetworkLog stwNetworkLog = new StwNetworkLog();
		
		System.out.println("STW HAR Parser running...");
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Please enter the path to the folder containing all your HAR records: ");
		String path = reader.nextLine(); 
		
		File dir = new File(path);  //"/Users/huanjiayang/Documents/stw_har/");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File file : directoryListing) {
				if(file.getName().endsWith(".har")){
					//System.out.println(file.getName().split(".har")[0]);
					String filePath = file.getPath();
					//System.out.println("file path is: " + filePath);
					String filename = file.getName().split(".har")[0];
					System.out.println("reading network log file for page : " + filename);
					String jsonString = new String();
					try {
						jsonString = StwHarParser.readFile(filePath, Charset.defaultCharset());
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					JSONObject har = new JSONObject(jsonString);
					JSONObject log = (JSONObject) har.get("log");
					JSONArray entries = (JSONArray) log.get("entries");
					for(int i=0; i<entries.length();i++){
						JSONObject entry = (JSONObject) entries.get(i);
						JSONObject request = (JSONObject) entry.get("request");
						String url = request.getString("url");
						String domain = url.split("/")[2];		
						String method = request.getString("method");
						stwNetworkLog.addNwkLogEntry(domain, method, filename);
					}
				
				}
			}
		}
		
		System.out.println("STW HAR Parser finshed parsing all log files...printing out all results...");
		//System.out.println(stwNetworkLog.getDomainList());
		System.out.println(stwNetworkLog);
		stwNetworkLog.makeCSV("STWNwkLog.csv");
	}
	
	
	static String readFile(String path, Charset encoding) 
			  throws IOException 
	{
	  byte[] encoded = Files.readAllBytes(Paths.get(path));
	  return new String(encoded, encoding);
	}

}
