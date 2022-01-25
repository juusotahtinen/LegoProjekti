/**
 * @author Leevi Palo, Juuso Tähtinen, Riku Maijala
 * @version 0.1
 */

package runnables;

//Tuodaan tarvittavat kirjastot
import lejos.hardware.port.SensorPort;
import sensors.UltraSonicSensor;
import util.DATA;

/**
 *Luokka etaisyyden mittaamista varten
 */
public class MeasureDistance implements Runnable{
	
	/**
	 * Luodaan aanisensoriolio
	 */
    UltraSonicSensor uss = new UltraSonicSensor(SensorPort.S1);

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
			 * Data luokkaan lahetetaan distance arvo
			 */
	        DATA.distance = uss.getRange();
		}
	}

}
