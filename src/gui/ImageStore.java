package gui;

public class ImageStore {
	private static ImageStore store = new ImageStore();
	
	public String blockBG = ClassLoader.getSystemResource("img/blockbg.png").toString();
	public String coalBlock = ClassLoader.getSystemResource("img/coalblock.png").toString();
	public String ironBlock = ClassLoader.getSystemResource("img/ironblock.png").toString();
	public String diamondBlock = ClassLoader.getSystemResource("img/diamondblock.png").toString();
	
	public ImageStore() {
		
	}
	
	public static ImageStore getInstance() {
		return store;
	}
}
