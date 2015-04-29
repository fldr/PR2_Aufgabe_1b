package porsche911gt2rs;

import jgame.*;
import jgame.JGColor;
import jgame.JGPoint;
import jgame.platform.JGEngine;
import jgame.JGImage;

public class Simulation extends JGEngine {

    private String ground="trocken";
    private double lastFrameTime;
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
        //this.initEngine(window_size.x, window_size.y);
        this.initEngine(640, 480);
    }

    @Override
    public void initCanvas() {
      //  this.setCanvasSettings(640, 480, 1, 1, JGColor.black, JGColor.white, null);
    setCanvasSettings(30,22,16,16,null,null,null); 
    
    }

     @Override
    public void initGame() {

        setPFSize(120,90);
		// Set the wrap-around mode to vertical.
		setPFWrap(
			false, // horizontal wrap
			false,  // vertical wrap
			-10, -10 // shift the center of the view to make objects wrap at
			       // the right moment (sprite size / 2).
		);
        
        // Framerate festlegen
        setFrameRate(60, 2);

        // Media Table Laden
        defineMedia("media.tbl");

        // Background
        JGObject road = new JGObject("Background", true, 0, 0, 0, "track_1");


        // Porsche erstellen und initialisieren
        porsche = new Porsche911GT2RS(1445.0,456.0,330.0,1.0,0.0);
        porsche.reset();
        porsche.x=10;
        porsche.y=105;
       // porsche.powerPropMax=porsche.powerPropMax*1000;

        setGameState("Wait");
    }

        public void doFrameWait() {
        if (getKey(KeyEnter) || getKey(' ')) {
            setGameState("Simulation");
        }
    }

    public void paintFrameWait() {
        drawString("Car simulation", pfWidth() / 2, 140, 0, new JGFont("Courier", 1, 55), JGColor.white); // / (position) , new JFront(schriftart,besonderheit(zb Kursiv),größe), JGColor.Farbe
        drawString("Press 'ENTER' to start.", pfWidth() / 2, 250, 0, new JGFont("Arial", 1, 25), JGColor.white);
    }

    //Scrolling Offset
        int xofs=0;
        int yofs=0;
    
    public void doFrameSimulation() {
        
        //Scrolling Offset
        moveObjects(null,0);
		// Update the desired view offset according to mouse position.
		// The X offset is proportional to the mouse position.
		// The mouse position is a number between 0 and viewWidth.
		// We scale it to a number between 0 and pfWidth (playfield width).
		//xofs =  ((int)porsche.x * pfWidth() / 2)/15;
                xofs =  ((int)porsche.x) ;
		// the Y offset changes proportional to the offset of the mouse
		// position from the center of the window.  If the mouse is in the
		// center, we don't scroll, if it is close to the upper or lower
		// border of the window, it scrolls quickly in that direction.
		//yofs += ((int)porsche.y - viewHeight()/2) / 15;
                yofs = ((int)porsche.y );
		// Set the view offset.  Note that if our offset is out of the
		// playfield bounds, the position is clipped so that it is inside.
		// (this is only relevant for non-wrappable axes; a wrappable
		// axis is never out of bounds!)
		setViewOffset(
			xofs,yofs, // the position within the playfield
			true       // true means the given position is center of the view,
			           // false means it is topleft.
		);
//        if(porsche.x>320){
//            xofs=(int)porsche.x-320;
//        }
//        else{xofs=0;}
//        if(porsche.y>240){
//        yofs=(int)porsche.y-240;
//        }
//        else{yofs=0;}
        
       
        //tempLenken
        //if (getKey(KeyLeft)) {porsche.carAngle=porsche.carAngle-0.5;}
        
        if (getKey(KeyLeft)) {if(porsche.carAngle<=0){porsche.carAngle=358.0;} else{porsche.carAngle=porsche.carAngle-2;}}
        
        
        if (getKey(KeyRight)) {porsche.carAngle=porsche.carAngle+2;}
        
        
        // Zeitdifferenz berechnen
        double brakelevel, level, now = System.currentTimeMillis() / 1000.0;
        double diff = (lastFrameTime == 0) ? lastFrameTime : (now - lastFrameTime);

        // Reset wenn -R- gedrückt
        if (getKey(82)) {porsche.reset();}

        // ABS/ASR steuern (ein/aus):
        if (getKey(49)) {porsche.setABS(true);}
        if (getKey(50)) {porsche.setABS(false);}
        if (getKey(51)) {porsche.setASR(true);}
        if (getKey(52)) {porsche.setASR(false);}

        // Untergrund ändern?
        if (getKey('T')) {porsche.setGround(1.0);ground="trocken";}
        else if (getKey('Q')) {porsche.setGround(0.7); ground="Nässe";}
        else if (getKey('W')) {porsche.setGround(0.1); ground="Eis";}
        else if (getKey('E')) {porsche.setGround(0.3); ground="Schnee";}



        // Hebel benutzen
        gaspedal.step(diff, getKey(KeyUp));
        bremspedal.step(diff, getKey(KeyDown));

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

    double rot=45, rotinc;

    public void paintFrameSimulation() {

         // Farb-Konstanten
         final JGColor c_green = new JGColor(0,8*16+5,0);
         final JGColor c_red = new JGColor(10*16+6,12,0);

         final JGColor c_wet = new JGColor(0x42,0x84,0xd3);
         final JGColor c_dry = new JGColor(0x23,0xd2,0x2e);
         final JGColor c_icy = new JGColor(0xaa,0xd7,0xff);

         // Offset für HUD (position)
         int xoff = 64, yoff = 8;

         // HUD Info
         drawString(("Time: " + Math.round(porsche.time)+ " s"), xoff + 0, yoff + 0, 1, new JGFont("Arial",JGFont.BOLD,10), JGColor.yellow);
         
         drawString(("Speed: " + Math.round(porsche.speed*3.6) + " km/h"), xoff + 100, yoff + 0, JGFont.BOLD, new JGFont("Arial",1,10), JGColor.yellow);
         drawString(("Position: " + Math.round(porsche.pos)+ " m"), xoff + 100, yoff + 15, 1, new JGFont("Arial",JGFont.BOLD,10), JGColor.yellow);
         drawString(("Untergrund: " + ground), xoff + 100, yoff + 30, 1, new JGFont("Arial",JGFont.BOLD,10), JGColor.yellow);

         
         drawString(("ACC: " + Math.round(porsche.acc)+ " m/s2"), xoff + 200, yoff + 0, 1, new JGFont("Arial",JGFont.BOLD,10), JGColor.yellow);
         drawString(("Angle: " + Math.round(porsche.carAngle)+ "°"), xoff + 200, yoff + 15, 1, new JGFont("Arial",JGFont.BOLD,10), JGColor.yellow);
         drawString(("Frame: " + porsche.graphicName), xoff + 200, yoff + 30, 1, new JGFont("Arial",JGFont.BOLD,10), JGColor.yellow);
         drawString(("X/Y: " + Math.sin(porsche.carAngle)+"/"+Math.cos(porsche.carAngle)), xoff + 200, yoff + 45, 1, new JGFont("Arial",JGFont.BOLD,10), JGColor.yellow);

         drawString(("Windwiderstand: " + Math.round(porsche.forceDrag)+ " N"), xoff + 350, yoff + 0, 1, new JGFont("Arial",0,10), JGColor.yellow);
         drawString(("Antriebskraft: " + Math.round(porsche.forcePropB)+ " N"), xoff + 350, yoff + 15, 1, new JGFont("Arial",0,10), JGColor.yellow);
         drawString(("Resultierende: " + Math.round(porsche.force)+ " N"), xoff + 350, yoff + 30, 1, new JGFont("Arial", JGFont.BOLD,10), JGColor.yellow);


         // Gas
//         drawString(("Level: "), xoff + 400, yoff + 0, 1, new JGFont("Arial", JGFont.BOLD,10), JGColor.yellow);
//         drawLine( xoff + 380, yoff + 20, xoff + 500, yoff + 20, 13, JGColor.white);
//         drawLine( xoff + 380, yoff + 20, xoff + (porsche.proplevel * 200) + 300, yoff + 20, 13, new JGColor(0x95,0x00,0x2B));

         drawString(("Level: "), xoff + 400, yoff + 0, 1, new JGFont("Arial", JGFont.BOLD,10), JGColor.yellow);
         drawLine( xoff + 380, yoff + 20, xoff + 480, yoff + 20, 13, JGColor.white);
         drawLine( xoff + 380, yoff + 20, xoff + (porsche.proplevel * 100)+380 , yoff + 20, 13, new JGColor(0x95,0x00,0x2B));
         
         // Bremse
         drawString(("Brake: "), xoff + 400, yoff + 30, 1, new JGFont("Arial", JGFont.BOLD,10), JGColor.yellow);
         drawLine( xoff + 380, yoff + 40, xoff + 480, yoff + 40, 13, JGColor.white);
         drawLine( xoff + 380, yoff + 40, xoff + (porsche.brakeProplevel * 100) + 380, yoff + 40, 13, new JGColor(0x9,0x69,0xA2));

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
         int xoff2 = 640, yoff2 = 480;

        // drawLine( xoff2 - 57 , yoff2, xoff2 - 46, yoff2, 15, ((porsche.ground == 1.0) ? c_dry : ((porsche.ground == 0.7) ? c_wet : c_icy)));
       //  drawString("Road: " + ((porsche.ground == 1.0) ? "DRY" : (porsche.ground == 0.7) ? "WET" : ((porsche.ground == 0.1) ? "ICY")), xoff2 - 95, yoff2 - 4, -1, new JGFont("Arial", JGFont.BOLD,11), JGColor.black);

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
         drawImage(porsche.x % pfWidth(),porsche.y % pfHeight(), porsche.graphicName,new JGColor(1.0,1.0,1.0),1,rot,1,true);


    }

}
