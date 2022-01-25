/**
 * @author Leevi Palo, Juuso Tähtinen, Riku Maijala
 * @version 0.1
 */

package runnables;

//Tuodaan tarvittavat kirjastot
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;
import util.DATA;

/**
 * Luodaan RunMotor-luokka robotin ajamiseen
 */
public class RunMotor implements Runnable{
	/**
	 * Luodaan molemmille moottoreille oliot, joiden avulla voidaan säädellä pyörimisnopeuksia
	 */
    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    /**
	 * Luodaan molemmille moottoreille oliot, joiden avulla voidaan säädellä pyörimisnopeuksia
	 */
    UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
    /**
     * Luodaan sekuntikello-olio ajanottoa varten
     */
    Stopwatch watch = new Stopwatch();
	@Override
	/**
	 * run-metodilla voidaan kayttaa paaohjelmassa saikeita
	 */
	public void run() {
		while (DATA.shouldRun) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
	        /**
	         * Loiva kaarros vasempaan, kun ajaudutaan hieman pois viivalta
	         */
			if (DATA.ambient>0.230f && DATA.ambient<0.280f) {
				motorA.setPower(+50);
				motorB.setPower(+30);
			}
			/**
			 * Hieman tiukempi kaarros vasempaan, kun ollaan jo kauempana viivasta
			 */
			else if (DATA.ambient>0.281f && DATA.ambient<0.300f) {
				motorA.setPower(+50);
				motorB.setPower(+10);
			}
			/**
			 * Tiukka 90-asteen kaannos vasempaan jos ollaan jo puhtaasti valkoisella pohjalla
			 */
			else if (DATA.ambient>0.301f) {
				motorA.setPower(+40);
				motorB.setPower(-40);
			}
			/**
			 * Tavoitearvo mustan ja valkoisen rajalle, robotti saa kulkea suoraan
			 */
			else if (DATA.ambient<0.200f && DATA.ambient>0.100f) {
				motorA.setPower(+50);
				motorB.setPower(+50);
			}
			/**
			 * Kaannetaan hieman oikealle, ollaan ajauduttu enemmän mustan puolelle
			 */
			else if (DATA.ambient<0.100f && DATA.ambient >0.70f) {
				motorA.setPower(+10);
				motorB.setPower(+50);
			}
			/**
			 * Tiukka 90-asteen kaannos oikeaan, ollaan jo puhtaasti mustalla pohjalla
			 */
			else if (DATA.ambient<0.070f) {
				motorA.setPower(-40);
				motorB.setPower(+40);
			}
			/**
			 * Tarkkaillaan etaisyysanturilla esteitä, jos tullaan liian lahelle estetta, lisataan kohtaamismuuttujaan 1
			 */
			else if (DATA.distance<0.06) {
				DATA.obstacleCount++;
				/**
				 * Jos kohtaamismuuttuja on isompi kuin 1 (eli toisella kohtaamisella), pysaytetaan robotti
				 */
				if (DATA.obstacleCount > 1) {
					
					motorA.stop();
					motorB.stop();
					
					motorA.close();
					motorB.close();
					
					/**
					 * time-muuttuja ja saa arvon stopwatchilta millisekunteina, muutetaan sekunneiksi
					 */
					int time = watch.elapsed()/1000;
					/**
					 * Muutetaan sekunnit minuuteiksi
					 */
					int minutes = time / 60; 
					/**
					 * Otetaan jakojaannoksesta ylimaaraiset sekunnit
					 */
					int seconds =time % 60;
					/**
					 * Jos minuutteja alle kymmenen, ensimmainen luku naytetaan nollana
					 */
					String disMinu = (minutes < 10 ? "0" : "") + minutes;
					/**
					 * Jos sekunteja alle kymmenen, ensimmäinen luku naytetaan nollana
					 */
					String disSec = (seconds < 10 ? "0" : "") + seconds;
					/**
					 * Tulostetaan naytolle kulunut aika minuutteina ja sekunteina
					 */
					System.out.println("Time spent " + disMinu + " min " + disSec + " sec");
					System.out.println("");
					/**
					 * Tulostetaan lopettamisviesti
					 */
					System.out.println("Press button to stop program");
					
					
	
				}
				/**
				 * Kun este havaitaan, kaannos noin 70 astetta oikeaan 
				 */
				else {
					motorA.setPower(-50);
					motorB.setPower(+50);
					Delay.msDelay(450);
					
					/**
					 * Kaarretaan esteen ohi kunnes musta viiva jalleen nakyvissa
					 */
					while (DATA.ambient>0.300f) {
						motorA.setPower(+45);
						motorB.setPower(+28);
					}
				}	
			}
			/**
			 * Jos variarvo on oikea ja estetta ei ole, ajetaan suoraan
			 */
			else {
				DATA.direction=1;
				motorA.setPower(+45);
				motorB.setPower(+45);
			}
		}
	}
	
	/**
	 * Kaynnistetaan moottorien pyoriminen, samalla nollataan ajanotto
	 */
	public void startMotor() {
    	motorA.setPower(30);
    	motorB.setPower(30);
    	watch.reset();
	}
	/**
	 * Pysaytetaan moottorit
	 */
	public void stopMotor() {
		DATA.shouldRun = false;
		
	}
	
	
	
	
	

}
