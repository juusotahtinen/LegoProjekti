/**
 * Alkuperainen koodi on Pentti Ojaniemen BackAndForth ohjelmapaketista
 * @author Leevi Palo, Juuso Tähtinen, Riku Maijala
 * @version 0.1
 */

package app;

//Tuodaan tarvittavat kirjastot
import lejos.utility.Delay;
import lejos.hardware.Button;
import runnables.*;

/**
 * Paaohjelma robotin ajamiseen
 */
public class BackAndForth {

	public static void main(String[] args) {
		/**
		 * Luodaan oliot moottorille, etaisyyssensorille ja valopaalle
		 */
		RunMotor runMotor=new RunMotor();
		MeasureDistance measureDistance=new MeasureDistance();
		MeasureColor measureColor=new MeasureColor();

		/**
		 * Seuraavaksi luodaan saikeet
		 */
		Thread motor=new Thread(runMotor);
		Thread distance=new Thread(measureDistance);
		Thread ambient=new Thread(measureColor);


		
		/**
		 * Tulostetaan tekstia EV3-palikan naytolle
		 */
        System.out.println("Drive forward and avoid obstacles");
        System.out.println("Press any key to start");
        System.out.println("                                      ");

        /**
         * Vilkutetaan valoa nelja kertaa
         */
        Button.LEDPattern(4);
        
        /**
         * Odotetaan, etta jotakin nappia painetaan
         */
        Button.waitForAnyPress();
        
        /**
         * Kaynnistetaan ensiksi varisensorin saie
         * Sen jalkeen etaisyys ja moottorit 5 sekunnin kuluttua
         */
        ambient.start();
        Delay.msDelay(5000);
        distance.start();
        motor.start();
        runMotor.startMotor();

        
        
        
        /**
         * Odotetaan napin painallusta ja sen jalkeen sammutetaan moottorit
         */
        Button.waitForAnyPress();
        runMotor.stopMotor();
        /**
         * Lopuksi soitimme Nokiatunnarin ajon päätyttyä, tämä kuitenkin aiheutti ohjelman kaatumisen, siksi jätetty kommentiksi
         */
        //Soitetaan nokiatunnari ajon paattymisen merkiksi
        //Sound.playSample(new File("nokia.wav"), 100);
        
        
	}

}
