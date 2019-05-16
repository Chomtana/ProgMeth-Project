package gui;

import entity.Player;

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
	
	public String playerU = ClassLoader.getSystemResource("img/PlayerU.png").toString();
	public String playerR = ClassLoader.getSystemResource("img/PlayerR.png").toString();
	public String playerD = ClassLoader.getSystemResource("img/PlayerD.png").toString();
	public String playerL = ClassLoader.getSystemResource("img/PlayerL.png").toString();
	
	public String bomb = ClassLoader.getSystemResource("img/bomb.png").toString();
	public String fire = ClassLoader.getSystemResource("img/fire.png").toString();
	public String attack = ClassLoader.getSystemResource("img/attack.png").toString();
	
	public String iron = ClassLoader.getSystemResource("img/iron.png").toString();
	public String coal = ClassLoader.getSystemResource("img/coal.png").toString();
	public String diamond = ClassLoader.getSystemResource("img/diamond.png").toString();
	
	public String woodenSword = ClassLoader.getSystemResource("img/WoodenSword.png").toString();
	public String ironSword = ClassLoader.getSystemResource("img/IronSword.png").toString();
	public String diamondSword = ClassLoader.getSystemResource("img/DiamondSword.png").toString();
	
	public String ironPant = ClassLoader.getSystemResource("img/IronPant.png").toString();
	public String diamondPant = ClassLoader.getSystemResource("img/DiamondPant.png").toString();
	
	public String ironBoot = ClassLoader.getSystemResource("img/IronBoot.png").toString();
	public String diamondBoot = ClassLoader.getSystemResource("img/DiamondBoot.png").toString();
	
	public String ironHelmet = ClassLoader.getSystemResource("img/IronHelmet.png").toString();
	public String diamondHelmet = ClassLoader.getSystemResource("img/DiamondHelmet.png").toString();
	
	public String ironArmor = ClassLoader.getSystemResource("img/IronArmor.png").toString();
	public String diamondArmor = ClassLoader.getSystemResource("img/DiamondArmor.png").toString();
	
	public String startbg = ClassLoader.getSystemResource("img/startbg.png").toString();
	
	public ImageStore() {
		
	}
	
	public static ImageStore getInstance() {
		return store;
	}
}
