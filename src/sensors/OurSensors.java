package sensors;

import java.awt.Color;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class OurSensors {
	private TouchSensor touch; //Port 3
	private UltraSonicSensor ultra; //Port 1
	private ColorSensor color; //Port 2
	
	public OurSensors() {
		touch= new TouchSensor(SensorPort.S3);
		ultra = new UltraSonicSensor (SensorPort.S1);
		color = new ColorSensor (SensorPort.S2);
		
	}

}