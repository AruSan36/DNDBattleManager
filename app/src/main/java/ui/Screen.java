
package ui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import ui.Buttons;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Screen extends Pane{
    
    private final Canvas canvas;
    private final List<Buttons> buttons = new ArrayList<>();
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
        draw();
    }

    public Canvas getCanvas(){
        return getCanvas();
    }

    public void draw(){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //Hintergrund löschen und neu erstellen in Schwarz
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        for(Buttons b : buttons){
            drawButtons(gc, b);
        }

    } 

    private void drawButtons(GraphicsContext gc, Buttons b){
        gc.setFill(Color.BLUE);
        gc.fillRoundRect(b.x, b.y, b.width, b.height, 5, 5);

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.strokeRoundRect(b.x, b.y, b.width, b.height, 5, 5);

        // Text (grob mittig; für genau mittig müsste man Textbreite messen)
        gc.setFill(Color.WHITE);
        gc.fillText(b.text, b.x + 10, b.y + b.height / 2 + 5);
    }

    private void initButtons(){
        Buttons b1 = new Buttons(
            20,20,60,20,
            "Create",
            () -> System.out.println("Entry Created")
        );
        Buttons b2 = new Buttons(
            20 + b1.width + offset,20,60,20,
            "Delete",
            () -> System.out.println("Entry Deleted")
        );

        buttons.add(b1);
        buttons.add(b2);
    }

    

}
