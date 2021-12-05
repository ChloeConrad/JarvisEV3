package vision;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import sensors.UltraSonicSensor;

/**
 * @deprecated
 * Ancienne version de OurMotor, suivant movePilot, mais étant plus limité dans certains mouvements.
 * @author JarvisTeam
 */
public class Robot {
    private float speed;
    private int offset;
    private Wheel leftW;
    private Wheel rightW;
    private Chassis chassis;
    public MovePilot pilot;

    private static EV3UltrasonicSensor USs;
    private SampleProvider USdist;
    private EV3TouchSensor Ts;
    public SampleProvider Touch;
    
    RegulatedMotor claw = new EV3LargeRegulatedMotor(MotorPort.D); //Positive opens. Range varies from 0 as to 1800 as open
    
    
    float temp;
    
    //Constructor
    public Robot() {
        /* Motor and chassis init */
        offset=58;    // ecart entre les roues et le centre
        rightW = WheeledChassis.modelWheel(Motor.A, 52.0).offset(-offset); // roue de droite, 52.0 est son diametre.
        leftW = WheeledChassis.modelWheel(Motor.B, 52.0).offset(offset); // roue de gauche 
        chassis = new WheeledChassis(new Wheel[]{rightW, leftW}, WheeledChassis.TYPE_DIFFERENTIAL); //chassis. Differential car le robot ne se deplace pas dans toutes les directions
        pilot = new MovePilot(chassis);
        
        /* Ultrasonic Sensor init */
        USs = new EV3UltrasonicSensor(SensorPort.S1);
        USdist = USs.getDistanceMode();
        
        /* Button sensor init */
        Ts = new EV3TouchSensor(SensorPort.S3);
        Touch = Ts.getTouchMode();
        
        /* Claw Init */
        claw = Motor.D;
        claw.setSpeed(1000);
        claw.setAcceleration(200);
        
        pilot.setLinearSpeed(500);
		pilot.setLinearAcceleration(100);
		
        /* Speed init */
        this.speedInit();
    }
    /* ** Debug Methods ** */
    //output message and waits for user input
    public void stopMessage(String str) {
        LCD.refresh(); //LCD pas utilisé actuellement
        System.out.println(str);
        Button.waitForAnyPress();
    }
        
    //exits the execution (not currently in use)
    public void stopAll() {
        System.out.println("Exiting ...");
        System.exit(1);
    }

    /* ** Init Methods ** */
    //initialize the speed of the vehicle, by travelling 100 units and comparing the distance
    private void speedInit() {
    	//this.pilot.travel(500);
        System.out.println("Initializing the speed\n .. will move forward.");
        float dist1 = getDist();
        this.pilot.travel(100);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        float dist2 = getDist();
        float travel = Math.abs(dist2-dist1);
        this.speed = 100/travel;
    }

    /* ** Sensor Methods ** */
    public float getDist() {
        float [] sample = new float[USdist.sampleSize()];
        USdist.fetchSample(sample, 0);
        return sample[0];
    }
    
    public float getTouch() {
    	float [] sample = new float[Touch.sampleSize()];
    	Touch.fetchSample(sample, 0);
    	return sample[0];
    }
    
    
    /* ** Movement methods ** */
    //moves the robot to the nearest obstacle, with a margin of 10cm or margin
    public void moveToObstacle() {
        moveToObstacle(0.2);
    }

    public void moveToObstacle(double margin) {
        float distToWall = this.getDist();
        this.pilot.travel((int)((distToWall-margin)*speed));
    }
    public void moveToObstacle(double margin, boolean b) {
        float distToWall = this.getDist();
        this.pilot.travel((int)((distToWall-margin)*speed),b);
    }

    //make the bot do a complete turn
    public void doA360() {
        this.pilot.rotate(360);
    }
    
    public void square() {
    	for(int i=0; i<4; i++) {
    		this.moveToObstacle();
    		this.pilot.rotate(90);
    	}
    }
    
    public void closeClaw() {
    	//claw.rotate(currentClaw);
    	//currentClaw=0;
    	claw.rotate(-1800);
    }
    public void openClaw() {
    	//claw.rotate(-1800-currentClaw,true);
    	//currentClaw+=(1800-currentClaw);
    	claw.rotate(1800,true); //lorsqu'on ouvre la pince, pas besoin de rester immobile
    }
    public void grabPallet() {
    	 // Pas sur d'avoir besoin de grab juste un pallet, en soit le close et open sert à ça, et pour
    	// serrer la pince, 1800 c'est parf.
    }

    /* ** Getters Glitters ** */
    public float getSpeed(){
        return speed;
    }
    
    public float[] scan360() {
    	float[] t = new float[18];
    	int i = 0;
    	UltraSonicSensor s = new UltraSonicSensor(SensorPort.S1);
    	do {
    		t[i] = s.getDist();
    		this.pilot.rotate(20);
    		i++;
    	}while(i<18);
    	return t;
    }

}