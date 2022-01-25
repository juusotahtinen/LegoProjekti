/**
 * @author Leevi Palo, Juuso Tähtinen, Riku Maijala
 * @version 0.1
 */

package util;

/**
 * Robotin toiminnassa tarvittavia muuttujia
 */
public class DATA {
	/**
	 * shouldRun-muuttujalla maaritetaan ovatko moottorit kaynnissa vai ei
	 */
	public static boolean shouldRun=true;
	/**
	 * distance-muuttujalla tutkitaan etaisyytta esteeseen
	 */
	public static float distance=100;
	/**
	 * ambient-muuttujalla tutkitaan sensoriin tulevan valon maaraa
	 */
	public static float ambient=0.100f;
	/**
	 * direction-muuttujalla maaritetaan robotin suunta, eteen- vai taaksepain
	 */
	public static int direction=1;
	/**
	 * obstacleCount-muuttujalla pidetaan kirjaa havaituista esteista
	 */
	public static int obstacleCount=0;

}
