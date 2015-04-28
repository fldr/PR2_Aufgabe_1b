//package porsche911gt2rs;
//
//public class Porsche911GT2RS {
//
//    CarState currentState;
//    CarState nextState;
//
//    //---------------constans---------------
//    public static final double KM_IN_M = 1000.0;
//    public static final double M_IN_KM = 1 / KM_IN_M;
//
//    public static final double EPSILON = 0.1;
//
//    public static final double KM_PER_HOUR_IN_M_PER_SEC = 3.6;
//    public static final double M_PER_SEC_IN_KM_PER_HOUR = 1 / KM_PER_HOUR_IN_M_PER_SEC;
//
//    public static final double SPEEDMIN = 0.01; // [m/s]
//    public static final double ACCEARTH = 9.81; // [m/s^2]
//
//    //---------------attributes---------------
//    public double time; // [s]
//    public double pos; //  [m]
//    public double speed; // [m/s]
//
//    public double mass; // [kg]
//    public double powerPropMax; // [W]
//    public double speedMax; // [m/s]
//    public double force; // Resultierende Kraft [kg*m/s^-2]
//    public double acc; // Beschleunigung [m/s^-2]
//
//    public double proplevel; // Druck Gaspedal (0..1)
//    public double brakeProplevel; // Bremspedal
//    public double powerProp; // Anteil maximale Leistung [W]
//    public double forcePropMax; // Maximale Vortriebskraft[kg*m/s^-2]
//    public double forcePropAbs; // [kg*m/s^-2]
//    public double forceProp; // Vortriebskraft [kg*m/s^-2]
//    public double dragConst;//abs() == || [kg*m/s^-2]
//    public double forceDrag; // Resultierender Widerstand [kg*m/s^-2]
//
//    public double forcePropBrakeB;
//    public double powerPropB;   // Anteil max. Leistung [W]
//    public double forcePropB;   // Bremskraft [kg*m/s^-2]
//    public double forceOnWheel; // kraft an den Rädern [kg*m/s^-2] 
//    public double forceB;       // kraft in neg. richtung [kg*m/s^-2]
//    public double forceDiff;    // kraft unterschied [kg*m/s^-2]-[kg*m/s^-2] = [kg*m/s^-2]
//
//    public double traction = 1.0;        //Bodenhaftung (um welchen Faktor wird Kraft umgesetzt)
//
//    public boolean absState = true; // abs an oder aus ?
//    public boolean asrState = true; // asr an oder aus ?
//    public boolean absOn, asrOn;    // abfrage variablen ob abs und asr an sind
//    public boolean errorgas, errorbrems; // zu viel gas oder zu schnell bremsen variablen
//
//    public Porsche911GT2RS(double mass, double powerPropMax, double speedMax, double ground) {
//
//        //this.carState = carState;
//        this.mass = mass; // kg
//        this.powerPropMax = powerPropMax * 1000; // W
//        this.speedMax = speedMax / KM_PER_HOUR_IN_M_PER_SEC; // m/s
//        this.traction = ground;
//        currentState = CarState.STEHEN;
//
//        forcePropMax = mass * ACCEARTH;
//        dragConst = Math.abs(this.powerPropMax / (this.speedMax * this.speedMax * this.speedMax));//abs() == || [kg*m/s^-2]
//
//    }
//
//    //DRY = 1.0; // Bodenbehaftung
//    //WET = 0.7; // Bodenbehaftung
//    //ICE = 0.3; // Bodenbehaftung
//    public void setGround(double ground) {
//        //this.traction = ground; // Bodenbehaftung ändern
//        this.traction = ACCEARTH * ground;
//        forcePropMax = mass * traction; // Maximale Vortriebskraft[kg*m/s^-2]
//    }
//
//    //OPERATIONS
//    public void set(double time, double pos, double speed, double proplevel, double brakeProplevel) {
//        this.time = time;
//        this.pos = pos;
//        this.speed = speed;
//        this.proplevel = proplevel;
//        this.brakeProplevel = brakeProplevel;
//    }
//
//    public void reset() {
//        set(0.0, 0.0, 0.0, 0.0, 0.0);
//    }
//
//    public void step(double deltaTime, double proplevel, double brakeProplevel) {
//
////        acc = force / mass;
////        speed = speed + (acc * deltaTime);
////        pos = pos + (speed * deltaTime);
////        switch (currentState) {
////            case STEHEN:
////                acc = 0.0;
////                speed = 0.0;
////                if(proplevel > 0.0){ nextState = CarState.FAHREN; }
////                break;
////
////            case FAHREN:
////                if (this.speed < SPEEDMIN) {
////                    this.speed = SPEEDMIN;
////                }
////                acc = force / mass;
////                speed = speed + (acc * deltaTime);
////                pos = pos + (speed * deltaTime);
////                break;
////                
////            default:break;    
////        }
//
//        
//         if (this.speed < SPEEDMIN) {
//                    this.speed = SPEEDMIN;
//                }
//        forceDrag = (dragConst * (speed * speed) * Math.signum(-speed));
//
//        powerProp = proplevel * powerPropMax;
//        forcePropAbs = Math.min(forcePropMax, powerProp / speed);
//        forceProp = forcePropAbs * Math.signum(proplevel);
//        force = forceProp + forceDrag;
//
//        time = time + deltaTime;
//        currentState = nextState;
//         acc = force / mass;
//        speed = speed + (acc * deltaTime);
//        pos = pos + (speed * deltaTime);
//    }
//
//    @Override
//    public String toString() {
//        return "Porsche911GT2RS{" + time + " Sekunden " + "Pos = " + Math.round(pos) + " m, Speed=" + Math.round(speed * M_PER_SEC_IN_KM_PER_HOUR) + "km/h Force=" + Math.round(force) + ", acc=" + Math.round(acc) + '}';
//    }
//
//}
package porsche911gt2rs;


import jgame.*;


public class Porsche911GT2RS{

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
    public static final double ICE = 0.3; // Bodenbehaftung
    //---------------attributes---------------
    public double time; // [s]
    public double pos; //  [m]
    public double speed; // [m/s]

    public double mass; // [kg]
    public double powerPropMax; // [W]
    public double speedMax; // [m/s]
    public double force; // Resultierende Kraft [kg*m/s^-2]
    public double acc; // Beschleunigung [m/s^-2]
    
    
    public double proplevel; // Druck Gaspedal (0..1)
    public double brakeProplevel; // Bremspedal
    public double powerProp; // Anteil maximale Leistung [W]
    public double forcePropMax; // Maximale Vortriebskraft[kg*m/s^-2]
    public double forcePropAbs; // [kg*m/s^-2]
    public double forceProp; // Vortriebskraft [kg*m/s^-2]
    public double dragConst;//abs() == || [kg*m/s^-2]
    public double forceDrag; // Resultierender Widerstand [kg*m/s^-2]
    
    public double forcePropBrakeB;
    public double powerPropB;
    public double forcePropB;
    public double forceOnWheel;
    public double forceB; 
    public double forceDiff;
    
    public double traction;        //Bodenhaftung (um welchen Faktor wird Kraft umgesetzt) (DEFAULT)
    public double ground;
    
    
    public boolean absState = true;
    public boolean asrState = true;
    public boolean absOn, asrOn;
    public boolean errorgas, errorbrems;
    
    
    public Porsche911GT2RS(double mass, double powerPropMax, double speedMax, double ground) {
        
        //super("Porsche911GT2RS", true, 0, 255, 1, "porsche");
        
        this.mass = mass; // kg
        this.powerPropMax = powerPropMax*1000 ; // W
        this.speedMax = speedMax; // KM_PER_HOUR_IN_M_PER_SEC; // m/s
        this.ground = ground;
        
        traction = ACCEARTH * ground; // beschl * factor = beschl
        forcePropMax = mass * traction; // masse * beschl = kraft
        dragConst = Math.abs(this.powerPropMax / (this.speedMax * this.speedMax * this.speedMax));//abs() == || [kg*m/s^-2]

    }
    
        public void setGround(double ground){
        this.ground = ground; // Bodenbehaftung ändern
        traction = ACCEARTH * ground;
        forcePropMax = mass * traction; // Maximale Vortriebskraft[kg*m/s^-2]
    }
    
    public void setABS(boolean value) { absState = value; }
    public void setASR(boolean value) { asrState = value; }
    
    //OPERATIONS
    public void set(double time, double pos, double speed, double proplevel, double brakeProplevel) {
        this.time = time;
        this.pos = pos;
        this.speed = speed / KM_PER_HOUR_IN_M_PER_SEC;
        this.proplevel = proplevel;
        this.brakeProplevel = brakeProplevel;
    }

    public void reset() {
        set(0.0, 0.0, 0.0, 0.0, 0.0);
    }

    public void step(double deltaTime, double proplevel, double brakeProplevel) {
      
        this.proplevel = proplevel;
        this.brakeProplevel = brakeProplevel;
        errorgas = false;
        errorbrems = false;
        asrOn = false;
        absOn = false;
        
        if (this.speed < SPEEDMIN) {
            this.speed = SPEEDMIN;
        }
        forceDrag = (dragConst * speed * speed * Math.signum(-speed));
        
        forcePropBrakeB   = mass*ACCEARTH*this.brakeProplevel*Math.signum(-speed);// Motorleistung Brutto
        powerPropB = powerPropMax*this.proplevel;
        forcePropB = powerPropB / speed;
        forceOnWheel = forcePropB + forcePropBrakeB;
        forceB = forceOnWheel + forceDrag; 

        powerProp = this.proplevel * powerPropMax;
        forcePropAbs = Math.min(forcePropMax, powerProp / speed);
        forceProp = forcePropAbs * Math.signum(this.proplevel);
        force = Math.min(Math.abs(powerProp), forcePropMax) * Math.signum(powerProp);
        
        forceDiff = forceB - force;
        
        
        // ABS und ASR
        if ((forceDiff > 0.0)) {

            asrOn = asrState;
            errorgas = !asrState; 
            
            force = forcePropMax; // Regelung ASR

        } else if ((forceDiff < 0.0)) {
                   
            absOn = absState;
            errorbrems = !absState;

            force = forcePropMax*(-1); // Regelung ABS
        }
        
        
        
        acc = force / mass;
        speed = speed + (acc * deltaTime);
        pos = pos + (speed * deltaTime);
        time = time + deltaTime;
    }
    

    @Override
    public String toString() {
        return "Porsche911GT2RS{" + time + " Sekunden " + "Pos = " + Math.round(pos) + " m, Speed=" + Math.round(speed * KM_PER_HOUR_IN_M_PER_SEC) + "km/h Force=" + Math.round(force) + ", acc=" + Math.round(acc) + '}';
    }

}