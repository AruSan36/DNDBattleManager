
package ui;

import java.util.ArrayList;
import java.util.List;

import dndbattlemanager.Entity;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import ui.Buttons;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Screen extends Pane{
    
    private final Canvas canvas;
    private final List<Buttons> buttons = new ArrayList<>();
    private final List<Entity> entities = new ArrayList<>();
    private  AnimationTimer timer;

    private float globalX; 
    private float globalY;
    private final float offset = 10;
    /*
    Layout Plan
    Create Button, x = 10, y = 10
    Delete Button, x = createButtonWidth+10, y = 10

    */

    public Screen(int WIDTH, int HEIGHT) {
        this.canvas = new Canvas(WIDTH, HEIGHT);
        getChildren().add(canvas);
        setPrefSize(WIDTH, HEIGHT);


        initButtons();
        initTestEntities();
        draw();
        startLoop();
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public void startLoop(){
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                draw();
            }
        };
        timer.start();
    }

    public void draw(){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //Hintergrund löschen und neu erstellen in Schwarz
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.BLUE);
        gc.fillRoundRect(20, 20, 60, 20, 5, 5);

        gc.setStroke(Color.WHITE);
        gc.strokeRoundRect(20, 20, 60, 20, 5, 5);

        gc.setFill(Color.WHITE);
        gc.fillText("Create", 30, 35);

        for(Buttons b : buttons){
            drawButtons(gc, b);
        }

        globalX = 100;
        globalY = 100;

        for(Entity e : entities){
            e.setStartPosX(globalX);
            e.setStartPosY(globalY);

            drawEntitiy(gc, e);

            e.setHeight(globalY - e.getStartPosY() );
            globalX += 100;
            e.setWidth(globalX - e.getStartPosX());
            globalY = 100; // Zurücksetzen der Y-Position für die nächste Spalte

        }

    } 

    private void drawButtons(GraphicsContext gc, Buttons b){
        gc.setFill(Color.BLUE);
        gc.fillRoundRect(b.x, b.y, b.width, b.height, 0, 0);

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.strokeRoundRect(b.x, b.y, b.width, b.height, 0, 0);

        // Text (grob mittig; für genau mittig müsste man Textbreite messen)
        gc.setFill(Color.WHITE);
        gc.fillText(b.text, b.x + 10, b.y + b.height / 2 + 5);
        
    }

    private void drawEntitiy(GraphicsContext gc, Entity e){
        gc.setFill(Color.WHITE);
        gc.fillText(e.getName(), globalX, globalY);
        globalY += 20; // Abstand für die nächste Zeile
        gc.fillText("Armor Class: " + e.getArmorClass(), globalX, globalY);
        globalY += 20;  
        gc.fillText("Hit Points: " + e.getCurrentHitPoints() + "/ " + e.getCurrentHitPoints(), globalX, globalY);
        globalY += 20;
        gc.fillText("Initiative: " + e.getInitiative(), globalX, globalY);
        globalY += 20; // Extra Abstand zwischen Einheiten  

    }
        

    private void initButtons(){
        Buttons b1 = new Buttons(
            20,20,60,20,
            "Create",
            () -> System.out.println("Entry Created")
        );
        Buttons b2 = new Buttons(
            20 + b1.width + offset,20f,60f,20f,
            "Delete",
            () -> System.out.println("Entry Deleted")
        );

        buttons.add(b1);
        buttons.add(b2);
    }

    private void initTestEntities(){
        Entity e1 = new Entity("Goblin", 15, 30, 30, 10, false);
        Entity e2 = new Entity("Player", 18, 50, 50, 15, true);

        entities.add(e1);
        entities.add(e2);
    }

}