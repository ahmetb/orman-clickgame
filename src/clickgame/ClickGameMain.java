package clickgame;


import clickgame.service.ClickGameService;
import clickgame.ui.ClickGameWindow;

public class ClickGameMain {
	
	public static ClickGameService service;
	public static ClickGameWindow gui;
	public static void main(String[] args) throws InterruptedException {
		gui = new ClickGameWindow();
		service = new ClickGameService(gui.getLogWindow().getLogArea());
		
		gui.setVisible(true);
	}
	
}
