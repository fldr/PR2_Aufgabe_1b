package porsche911gt2rs;

import jgame.*;
import jgame.JGColor;
import jgame.JGPoint;
import jgame.platform.JGEngine;

public class Simulation extends JGEngine {

    private double lastFrameTime = 0.0;
    private Porsche911GT2RS porsche;
    
        // Gas- & Bremspedal
    Pedal gaspedal = new Pedal(0.3,2); // Max Bremswirkung nach 0.3 S erreicht
    Pedal bremspedal = new Pedal(0.2,0.2);
    
        // Tilde-State
    private double tilde;
    
    public Simulation() {
        super();
        this.initEngineApplet();
    }

    public Simulation(JGPoint window_size) {
        super();
        this.initEngine(window_size.x, window_size.y);
    }

    @Override
    public void initCanvas() {
        this.setCanvasSettings(1000, 580, 1, 1, JGColor.black, JGColor.white, null);
    }
    
     @Override
    public void initGame() {
        
        // Framerate festlegen
        setFrameRate(35, 2);
        
        // Media Table Laden
        defineMedia("media.tbl");
        
        // Background
        JGObject road = new JGObject("Background", true, 0, 0, 1, "background");
        
        
        // Porsche erstellen und initialisieren
        porsche = new Porsche911GT2RS(1445.0,456.0,330.0,1.0);
        porsche.reset();
        
        setGameState("Wait");
    }
    
        public void doFrameWait() {
        if (getKey(KeyEnter) || getKey(' ')) {
            setGameState("Simulation");
        }
    }
        
    public void paintFrameWait() {
        drawString("Car simulation", pfWidth() / 2, 140, 0, new JGFont("Arial", 1, 55), JGColor.white); // / (position) , new JFront(schriftart,besonderheit(zb Kursiv),größe), JGColor.Farbe
        drawString("Press 'ENTER' to start.", pfWidth() / 2, 250, 0, new JGFont("Arial", 1, 25), JGColor.white);
    }    
    
    public void doFrameSimulation() {
        
        // Zeitdifferenz berechnen
        double brakelevel, level, now = System.currentTimeMillis() / 1000.0;
        double diff = (lastFrameTime == 0.0) ? lastFrameTime : (now - lastFrameTime);
        
        // Reset wenn -R- gedrückt
        if (getKey(82)) {porsche.reset();}
        
        // ABS/ASR steuern (ein/aus):
        if (getKey(49)) {porsche.setABS(true);}
        if (getKey(50)) {porsche.setABS(false);}
        if (getKey(51)) {porsche.setASR(true);}
        if (getKey(52)) {porsche.setASR(false);}
        
        // Untergrund ändern?
        if (getKey(81)) {porsche.setGround(1.0);}
        else if (getKey(87)) {porsche.setGround(0.7);}
        else if (getKey(69)) {porsche.setGround(0.1);}
        
        // Hebel benutzen
        gaspedal.step(diff, getKey(KeyRight));
        bremspedal.step(diff, getKey(KeyLeft));
        
        // Sind wir abgeflogen? Steuerung sperren
        if (tilde == 0.0) {
            level = gaspedal.getLevel();
            brakelevel = bremspedal.getLevel();
        } else {
            level = 0.0;
            brakelevel = 0.0;
        }
        
        // Berechnung des Autos aktualisieren
        porsche.step(diff , level, brakelevel);
        
        //drawImage(porsche.pos % pfWidth(),(porsche.abflug ? viewWidth()/45 +50*Math.cos(texttimer) : 15), "porsche");
        
        // Zeitstempel auf aktuelle Systemzeit setzen
        lastFrameTime = now;
        
        
    }
    
    
    
    public void paintFrameSimulation() {
         
         // Farb-Konstanten
         final JGColor c_green = new JGColor(0,8*16+5,0);
         final JGColor c_red = new JGColor(10*16+6,12,0);
         
         final JGColor c_wet = new JGColor(0x42,0x84,0xd3);
         final JGColor c_dry = new JGColor(0x23,0xd2,0x2e);
         final JGColor c_icy = new JGColor(0xaa,0xd7,0xff);
         
         // Offset für HUD (position)
         int xoff = 100, yoff = 20;
         
         // HUD Info
         drawString(("Speed: " + Math.round(porsche.speed) + " km/h"), xoff + 130, yoff + 0, JGFont.BOLD, new JGFont("Arial",1,14), JGColor.yellow);
         drawString(("Position: " + Math.round(porsche.pos)+ " m"), xoff + 130, yoff + 30, 1, new JGFont("Arial",JGFont.BOLD,14), JGColor.yellow);
         drawString(("Time: " + Math.round(porsche.time)+ " s"), xoff + 0, yoff + 0, 1, new JGFont("Arial",JGFont.BOLD,14), JGColor.yellow);
         drawString(("ACC: " + Math.round(porsche.acc)+ " m/s2"), xoff + 250, yoff + 0, 1, new JGFont("Arial",JGFont.BOLD,14), JGColor.yellow);
         drawString(("Windwiderstand: " + Math.round(porsche.forceDrag)+ " N"), xoff + 450, yoff + 0, 1, new JGFont("Arial",0,14), JGColor.yellow);
         drawString(("Antriebskraft: " + Math.round(porsche.forcePropB)+ " N"), xoff + 450, yoff + 30, 1, new JGFont("Arial",0,14), JGColor.yellow);
         drawString(("Resultierende: " + Math.round(porsche.force)+ " N"), xoff + 450, yoff + 60, 1, new JGFont("Arial", JGFont.BOLD,14), JGColor.yellow);
         
         
         // Gas
         drawString(("Level: "), xoff + 520, yoff + 0, 1, new JGFont("Arial", JGFont.BOLD,10), JGColor.yellow);
         drawLine( xoff + 500, yoff + 20, xoff + 700, yoff + 20, 13, JGColor.white);
         drawLine( xoff + 500, yoff + 20, xoff + (porsche.proplevel * 200) + 500, yoff + 20, 13, new JGColor(0x95,0x00,0x2B)); 
         
         // Bremse
         drawString(("Brake: "), xoff + 520, yoff + 40, 1, new JGFont("Arial", JGFont.BOLD,10), JGColor.yellow);
         drawLine( xoff + 500, yoff + 60, xoff + 700, yoff + 60, 13, JGColor.white);
         drawLine( xoff + 500, yoff + 60, xoff + (porsche.brakeProplevel * 200) + 500, yoff + 60, 13, new JGColor(0x9,0x69,0xA2));
          
                  // ABS Info wenn gerade eingreift
         if (porsche.absOn) {
            drawString("ABS aktiv", pfWidth() / 2, 230, 0, new JGFont("Tahoma", JGFont.BOLD, 24), JGColor.red);
         }
         // ASR Info wenn gerade eingreift
         if (porsche.asrOn) {
            drawString("ASR aktiv", pfWidth() / 2, 230, 0, new JGFont("Tahoma", JGFont.BOLD, 24), JGColor.red);
         }
         
         
         // Steuerungsinfo
         drawString(("Gas: Right // Bremse: Left // ABS 1(an)/2(aus)  // ASR 3(an)/4(aus)  //  "), 170, 560, 0, new JGFont("Arial", JGFont.BOLD,10), JGColor.yellow);
         
         
         // ABS Status
         int xoff2 = 900, yoff2 = 560;
         
         drawLine( xoff2 - 57 , yoff2, xoff2 - 46, yoff2, 15, ((porsche.ground == 1.0) ? c_dry : ((porsche.ground == 0.7) ? c_wet : c_icy)));
         drawString("Road: " + ((porsche.ground == 1.0) ? "DRY" : ((porsche.ground == 0.7) ? "WET" : "ICY")), xoff2 - 95, yoff2 - 4, -1, new JGFont("Arial", JGFont.BOLD,11), JGColor.black);
         
         drawString("ABS:", xoff2 - 22, yoff2 - 4, 0, new JGFont("Arial", JGFont.BOLD,11), JGColor.black);
         
         if (porsche.absState) {
            drawLine( xoff2 , yoff2, xoff2 + 10, yoff2, 15, c_green); 
            drawString("An", xoff2 + 5, yoff2 - 4, 0, new JGFont("Arial", JGFont.PLAIN,10), JGColor.white);
         } else {
            drawLine( xoff2 , yoff2, xoff2 + 10, yoff2, 15, c_red); 
            drawString("Aus", xoff2 + 5, yoff2 - 4, 0, new JGFont("Arial", JGFont.PLAIN,10), JGColor.white);
         }
          
         // ASR Status
         int xoff3 = 960, yoff3 = 560;
         drawString("ASR:", xoff3 - 22, yoff3 - 4, 0, new JGFont("Arial", JGFont.BOLD,11), JGColor.black);
         
         if (porsche.asrState) {
            drawLine( xoff3 , yoff3, xoff3 + 10, yoff3, 15, c_green); 
            drawString("An", xoff3 + 5, yoff3 - 4, 0, new JGFont("Arial", JGFont.PLAIN,10), JGColor.white);
         } else {
            drawLine( xoff3 , yoff3, xoff3 + 10, yoff3, 15, c_red); 
            drawString("Aus", xoff3 + 5, yoff3 - 4, 0, new JGFont("Arial", JGFont.PLAIN,10), JGColor.white);
         
         }
         
         if (tilde == 0 && (porsche.errorgas || porsche.errorbrems)) {
             tilde = System.currentTimeMillis();
         }    
         
         if (tilde != 0 && System.currentTimeMillis() - tilde > 2000) {
             tilde = 0;
         } else if (tilde != 0) {
             drawString("Abflug!", pfWidth() / 2, 250, 0, new JGFont("Tahoma", 1, 28), JGColor.white);
             drawString("Auto wird noch " + ((System.currentTimeMillis() - tilde) / 1000) + " Sekunden abgefangen", pfWidth() / 2, 300, 0, new JGFont("Tahoma", 1, 14), JGColor.white);
         }
         drawImage(porsche.pos % pfWidth(),255, "porsche");
         
    }   
    
}
