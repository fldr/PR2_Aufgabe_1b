package porsche911gt2rs;


import jgame.*;


public class Porsche911GT2RS extends JGObject{

    //---------------constans---------------
    public static final double KM_IN_M = 1000.0;
    public static final double M_IN_KM = 1 / KM_IN_M;

    public static final double EPSILON = 0.1;

    public static final double KM_PER_HOUR_IN_M_PER_SEC = 3.6;
    public static final double M_PER_SEC_IN_KM_PER_HOUR = 1 / KM_PER_HOUR_IN_M_PER_SEC;

    public static final double SPEEDMIN = 0.01; // [m/s]
    //public static final double SPEEDMAX = 330.0 / KM_PER_HOUR_IN_M_PER_SEC; //  [m/s]
    public static final double ACCEARTH = 9.81; // [m/s^2]

    public static final double DRY = 1.0; // Bodenbehaftung
    public static final double WET = 0.7; // Bodenbehaftung
    public static final double ICE = 0.1; // Bodenbehaftung
    //---------------attributes---------------
    public double time; // [s]
    public double pos; //  [m]
    public double speed; // [m/s]

    public double mass; // [kg]
    public double powerPropMax; // [W]
    public double speedMax; // [m/s]
    
    public double proplevel; // Druck Gaspedal (0..1)
    public double brakeProplevel; // Bremspedal
    public double powerProp; // Anteil maximale Leistung [W]
    public double forcePropMax; // Maximale Vortriebskraft[kg*m/s^-2]
    public double forcePropAbs; // [kg*m/s^-2]
    public double forceProp; // Vortriebskraft [kg*m/s^-2]
    public double dragConst;//abs() == || [kg*m/s^-2]
    public double forceDrag = (dragConst * (speed * speed) * Math.signum(-speed)); // Resultierender Widerstand [kg*m/s^-2]
    public double force; // Resultierende Kraft [kg*m/s^-2]
    public double acc; // Beschleunigung [m/s^-2]
    public double traction = DRY;        //Bodenhaftung (um welchen Faktor wird Kraft umgesetzt) (DEFAULT)

    
    public boolean absState;
    public boolean asrState;
    
    public Porsche911GT2RS(double mass, double powerPropMax, double speedMax) {
        
        super("Porsche911GT2RS", true, 0, 255, 1, "porsche");
        
        this.mass = mass; // kg
        this.powerPropMax = powerPropMax; // W
        this.speedMax = speedMax; // m/s
        
        forcePropMax = mass * ACCEARTH * traction; // Maximale Vortriebskraft[kg*m/s^-2]
        dragConst = Math.abs(powerPropMax / (speedMax * speedMax * speedMax));//abs() == || [kg*m/s^-2]

    }

//    public static void main(String[] args) {
//        Porsche911GT2RS porsche = new Porsche911GT2RS();
//        porsche.set(1.0, 1.0, 0.1, 1.0);
//
//        while ((SPEEDMAX - porsche.speed) >= EPSILON) {
//
//            System.out.println(porsche);
//
//            porsche.step(1.0, porsche.proplevel);
//
//        }
//    }


    
        public void setGround(double ground){
        traction = ground; // Bodenbehaftung ändern
        forcePropMax = mass * ACCEARTH * traction; // Maximale Vortriebskraft[kg*m/s^-2]
    }
    
    public void setABS(boolean value) { absState = value; }
    public void setASR(boolean value) { asrState = value; }
    
    //OPERATIONS
    public void set(double time, double pos, double speed, double proplevel) {
        this.time = time;
        this.pos = pos;
        this.speed = speed;
        this.proplevel = proplevel;
    }

    public void reset() {
        set(0.0, 0.0, 0.0, 0.0);
    }

    public void step(double deltaTime, double proplevel, double brakeProplevel) {
      
        if (speed < SPEEDMIN) {
            this.speed = SPEEDMIN;
        }
        

        powerProp = proplevel * powerPropMax;
        forcePropAbs = Math.min(forcePropMax, powerProp / speed);
        forceProp = forcePropAbs * Math.signum(proplevel);
        forceDrag = (dragConst * (speed * speed) * Math.signum(-speed));
        force = forceProp + forceDrag;
        
        
        acc = force / mass;
        speed = speed + (acc * deltaTime);
        pos = pos + (speed * deltaTime);
        time = time + deltaTime;
    }
    

    @Override
    public String toString() {
        return "Porsche911GT2RS{" + time + " Sekunden " + "Pos = " + Math.round(pos) + " m, Speed=" + Math.round(speed * KM_PER_HOUR_IN_M_PER_SEC) + "km/h Force=" + Math.round(force) + ", acc=" + Math.round(acc) + '}';
    }

    
    public double getGround(){return traction;}
}
