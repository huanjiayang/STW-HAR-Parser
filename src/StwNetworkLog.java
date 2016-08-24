import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class StwNetworkLog {
	private ArrayList<StwNetworkLogEntry> stwNetworkLogEntries = new ArrayList<StwNetworkLogEntry>();
	
	public StwNetworkLog() {
		super();
	}

	public StwNetworkLog(ArrayList<StwNetworkLogEntry> stwNetworkLogEntry) {
		super();
		this.stwNetworkLogEntries = stwNetworkLogEntry;
	}

	public void addNwkLogEntry(String domain, String method, String page){
		
		int domainIndex = this.findDomainIndex(domain);
		if(domainIndex == -1){
			StwNetworkLogEntry entry = new StwNetworkLogEntry(domain, method, new ArrayList<String>());
			entry.addPage(page);
			this.stwNetworkLogEntries.add(entry);
		}
		else{
			this.stwNetworkLogEntries.get(domainIndex).addPage(page);
		}
	}
	
	public void makeCSV(String filename){
        PrintWriter pw;
		try {
			pw = new PrintWriter(new File(filename));
	        StringBuilder sb = new StringBuilder();
			sb.append("Domain");
			sb.append(',');
			sb.append("Method");
			sb.append(',');
			sb.append("Used on Pages");
			sb.append('\n');
			for(int i=0;i<stwNetworkLogEntries.size();i++){
				sb.append(stwNetworkLogEntries.get(i).getDomain());
				sb.append(',');
				sb.append(stwNetworkLogEntries.get(i).getMethod());
				sb.append(',');
				sb.append(stwNetworkLogEntries.get(i).getPagesInString());
				sb.append('\n');
			}
	        pw.write(sb.toString());
	        pw.close();
	        System.out.println("CSV file " + filename + " created");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int findDomainIndex(String domain){
		int entryIndex = -1;
		for(int i=0;i<stwNetworkLogEntries.size();i++){
			if(stwNetworkLogEntries.get(i).getDomain().equals(domain))entryIndex=i;
		}
		return entryIndex;
	}
	
	public ArrayList<StwNetworkLogEntry> getStwNetworkLogEntry() {
		return stwNetworkLogEntries;
	}

	public void setStwNetworkLogEntry(ArrayList<StwNetworkLogEntry> stwNetworkLogEntry) {
		this.stwNetworkLogEntries = stwNetworkLogEntry;
	}

	@Override
	public String toString() {
		return "StwNetworkLog [stwNetworkLogEntry=" + stwNetworkLogEntries + "]";
	}
	
	public ArrayList<String> getDomainList(){
		ArrayList<String> domainList = new ArrayList<String>();
		for(int i=0;i<stwNetworkLogEntries.size();i++){
			domainList.add(stwNetworkLogEntries.get(i).getDomain());
		}
		return domainList;
	}
	

}
