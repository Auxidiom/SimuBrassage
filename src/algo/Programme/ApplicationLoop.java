package algo.Programme;

import algo.Etapes.Brassage;
import javafx.application.Platform;
import ui.ihm.EcranSimulation;

public class ApplicationLoop implements Runnable {
	
	
	private Boolean running;
	
	private Application app;
	
	Brassage brassage;

	public ApplicationLoop(Brassage b, Application app) {
		this.brassage = b;
		this.app = app;
	}
	
	@Override
	public void run() {
		running = true;
		while(running) {
				Platform.runLater(() -> {
					app.actualiserTemperature();
					app.actualiserTimer();
					app.obtenirPuissance();
	            });
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
}

