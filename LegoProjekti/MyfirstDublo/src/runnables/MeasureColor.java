/**
 * @author Leevi Palo, Juuso Tähtinen, Riku Maijala
 * @version 0.1
 */

package runnables;

//Tuodaan tarvittavat kirjastot
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import sensors.ColorSensor;
import util.DATA;


/**
 *Luokka variarvojen mittaamista varten
 */
public class MeasureColor implements Runnable{
	
	/**
	 * Luodaan varisensoriolio 
	 */
	ColorSensor cs = new ColorSensor(SensorPort.S4);
	
	/**
	 * run-metodilla voidaan kayttaa paaohjelmassa saikeita
	 */
	@Override
	public void run() {
		while(DATA.shouldRun) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			/**
			 * Koska omatekemamme rata oli hieman sinertava,vaihdoimme sensorin variksi punaisen
			 */
			cs.setRedMode();
	        cs.setFloodLight(Color.RED);
	        cs.setFloodLight(true);
	        
	        /**
	         * / Data luokkaan lahetetaan ambientin arvo
	         */
	        DATA.ambient = cs.getAmbient();
		}
	}

}
