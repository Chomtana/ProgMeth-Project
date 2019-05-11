package gui;

public class ImageStore {
	private static ImageStore store = new ImageStore();
	
	public String blockBG = ClassLoader.getSystemResource("img/blockbg.png").toString();
	public String coalBlock = ClassLoader.getSystemResource("img/coalblock.png").toString();
	public String ironBlock = ClassLoader.getSystemResource("img/ironblock.png").toString();
	public String diamondBlock = ClassLoader.getSystemResource("img/diamondblock.png").toString();
	
	public String boomerU = ClassLoader.getSystemResource("img/boomerU.png").toString();
	public String boomerR = ClassLoader.getSystemResource("img/boomerR.png").toString();
	public String boomerD = ClassLoader.getSystemResource("img/boomerD.png").toString();
	public String boomerL = ClassLoader.getSystemResource("img/boomerL.png").toString();
	
	public String zombieU = ClassLoader.getSystemResource("img/zombieU.png").toString();
	public String zombieR = ClassLoader.getSystemResource("img/zombieR.png").toString();
	public String zombieD = ClassLoader.getSystemResource("img/zombieD.png").toString();
	public String zombieL = ClassLoader.getSystemResource("img/zombieL.png").toString();
	
	public ImageStore() {
		
	}
	
	public static ImageStore getInstance() {
		return store;
	}
}
