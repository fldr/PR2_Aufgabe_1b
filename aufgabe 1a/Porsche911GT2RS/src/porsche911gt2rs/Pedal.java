package porsche911gt2rs;

public class Pedal {

    private double level;
    private double maxTimeFactorUp;
    private double maxTimeFactorDown;
    private double lastPressed;
    private double timePressed; 
    private double timeUnpressed;

       public Pedal(double upTime, double downTime) { // in Sekunden
       
       maxTimeFactorUp = (1/upTime);
       maxTimeFactorDown = (1/downTime);
       
   }
       
       // deltaTime in Sekunden
   public void step(double deltaTime, boolean isPressed) {
       
       // War gedrückt und ist es immer noch
       if (isPressed) { 
            
            timeUnpressed = 0;
            timePressed += deltaTime;
            level = (timePressed/4)*maxTimeFactorUp;
            level = ( level > 1) ? 1 : level;
        
        // Ist gedrückt aber war es nicht
        } else {
            
            timePressed = 0;
            timeUnpressed += deltaTime;
            level = (level - (timeUnpressed)*maxTimeFactorDown);
            level = (level <= 0) ? 0 : level;
            
        }
       
   }
       
   public double getLevel()
   {
       return level;
   }
    
}
