import java.util.ArrayList;

public class StwNetworkLogEntry {
	private String domain;
	private String method;
	private ArrayList<String> pages;
	
	public StwNetworkLogEntry(String domain, String method, ArrayList<String> pages) {
		super();
		this.domain = domain;
		this.method = method;
		this.pages = pages;
	}
	
	public void addPage(String page){
		if(!pages.contains(page))
			this.pages.add(page);
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String url) {
		this.domain = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public ArrayList<String> getPages() {
		return pages;
	}
	public void setPages(ArrayList<String> pages) {
		this.pages = pages;
	}
	
	@Override
	public String toString() {
		return "StwNetworkLogEntry [domain=" + domain + ", method=" + method + ", pages=" + pages + "]";
	}
	
	public String getPagesInString(){
		String pageString = new String();
		for(String page : this.pages){
			pageString = pageString + " : " + page;
		}
		return pageString;
	}
	
}
