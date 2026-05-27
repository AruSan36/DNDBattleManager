
package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dndbattlemanager.Entity;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Screen extends Pane{
    
    private final Canvas canvas;
    private final List<Button> buttons = new ArrayList<>();
    private final List<Entity> player = new ArrayList<>();
    private final List<Entity> enemies = new ArrayList<>();
    private AnimationTimer timer;

    private float globalX; 
    private float globalY;
    private final float offset = 10;
    private int fontSizeEntity = 18;
    private int fontSizeButton = 14;
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
        startListening();
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

        for(Button b : buttons){
            drawButtons(gc, b);
        }
     
        float entityGap = 20;
        float playerStartX = 100;
        float playerStartY = 200;
        globalX = playerStartX;
        globalY = playerStartY;

        for(Entity e : player){
            e.setStartPosX(globalX);
            e.setStartPosY(globalY);

            drawEntitiy(gc, e);

            globalX += e.getWidth() + entityGap;
            globalY = playerStartY;
            
             // Zurücksetzen der Y-Position für die nächste Spalt
        }
    
        float enemyStartX = 100;
        float enemyStartY = 400;
        globalX = enemyStartX;
        globalY = enemyStartY;

        for(Entity e : enemies) {
            e.setStartPosX(globalX);
            e.setStartPosY(globalY);

            drawEntitiy(gc, e);

            globalX += e.getWidth() + entityGap;
            globalY = enemyStartY;
        }
    } 

    private void drawButtons(GraphicsContext gc, Button b){
        gc.setFont(new Font("System" , fontSizeButton));
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
        float yGap = 20;
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("System" , fontSizeEntity));
        gc.fillText(e.getName(), globalX, globalY);
        globalY += yGap; // Abstand für die nächste Zeile
        gc.fillText("Armor Class: " + e.getArmorClass(), globalX, globalY);
        globalY += yGap;  
        gc.fillText("Hit Points: " + e.getCurrentHitPoints() + "/ " + e.getCurrentHitPoints(), globalX, globalY);
        globalY += yGap;
        gc.fillText("Initiative: " + e.getInitiative(), globalX, globalY);
        globalY += yGap; // Extra Abstand zwischen Einheiten  

        e.setHeight(4*yGap);
        e.setWidth(getEntityTextWidth(e));
    }

    public void startListening(){

        canvas.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();

            for(Button b : buttons){
                if(b.contains(x, y)){
                    try {
                        handleButtonClicked(b);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

            for(Entity e : player){
                if(e.contains(x, y)){
                    handleTextFieldClicked();
                }
            }
            for(Entity e: enemies){
                if(e.contains(x, y)){
                    handleTextFieldClicked();
                }

            }
    });
    }
    
    private void handleButtonClicked(Button b) throws Exception{
        switch(b.getText()){
            case "Create": 
                Entity e = showCreateButtonDialog();
                if(e != null)
                    if(e.isPlayer()){
                        player.add(e);
                    } else {
                        enemies.add(e);
                    }
                else{
                    throw new Exception("Create did not work");
                }
                break;
            case "Delete": 
                showDeleteButtonDilaog();
                break;
            case "SortByInitiative":
                sortByInitiative();
                break;
        }
    }

    private void showDeleteButtonDilaog(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete");
        dialog.setHeaderText("Gib den Namen des zu löschenden Eintrags ein");
        dialog.setContentText("Eingabe: ");

        Optional<String> result = dialog.showAndWait();
        
        if(result.isPresent()){

            String eingabe = result.get().trim();

            if(eingabe.equals("deleteAll")){
                    enemies.clear();
                    player.clear();
            }else if(eingabe.equals("deleteEnemies")){
                    enemies.clear();
            } else {
                for(Entity e : player){
                    if(e.getName().toLowerCase().equals(eingabe.toLowerCase())){
                        player.remove(e);
                    }
                }
                for(Entity e : enemies){
                    if(e.getName().toLowerCase().equals(eingabe.toLowerCase())){
                        enemies.remove(e);
                    }
                }
            }
        }
        
    }

    private Entity showCreateButtonDialog(){
        Dialog<Entity> dialog = new Dialog<>();
        dialog.setTitle("Charakter erstellen");
        dialog.setHeaderText("Werte eingeben");

        ButtonType okButtonType = new ButtonType("Erstellen", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
        
        TextField nameField = new TextField();  
        TextField armorClassField = new TextField();
        TextField maxHPField = new TextField();
        TextField Initiative = new TextField();
        TextField IsPlayer = new TextField();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
        grid.add(new Label("Name: ") ,0,0);
        grid.add(nameField, 1, 0);

        grid.add(new Label("ArmorClass: ") ,0,1);
        grid.add(armorClassField, 1, 1);

        grid.add(new Label("maxHpField: ") ,0,2);
        grid.add(maxHPField, 1, 2);

        grid.add(new Label("Intiative: ") ,0,3);
        grid.add(Initiative, 1, 3);

        grid.add(new Label("isPlayer: ") ,0,4);
        grid.add(IsPlayer, 1, 4);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(button -> {
            if(button == okButtonType){
                try{

                    String name = nameField.getText();
                    int ArmorClass = Integer.parseInt(armorClassField.getText());
                    int maxHp = Integer.parseInt(maxHPField.getText());
                    int initiative = Integer.parseInt(Initiative.getText());
                    boolean isPlayer = Entity.convertTextToisPlayer(IsPlayer.getText());

                    return new Entity(name, ArmorClass, maxHp, maxHp, initiative, isPlayer);
                    
                } catch (NumberFormatException e){
                    return null;
                }
            }
            return null;
        });

        return dialog.showAndWait().orElse(null);

    }
    /*
    Die Idee ist mit Hilfe des Pointers immer ganz hinten bei der Liste anzufangen und zu schauen ob der Eintrag vor einem Größer ist
    
    */
    private void sortByInitiative(){
        player.sort((a, b) -> Integer.compare(b.getInitiative(), a.getInitiative()));
        enemies.sort((a, b) -> Integer.compare(b.getInitiative(), a.getInitiative()));

        System.out.println("Player:");
        for (Entity e : player) {
        System.out.println(e.getName() + " -> " + e.getInitiative());
        }

        System.out.println("Enemies:");
        for (Entity e : enemies) {
        System.out.println(e.getName() + " -> " + e.getInitiative());
        }
    }


    private void handleTextFieldClicked(){
        System.out.println("Text Feld wurde geclickedt");
    }

    private float getEntityTextWidth(Entity e) {
        Font font = new Font("System", 18);

        double w1 = getTextWidth(e.getName(), font);
        double w2 = getTextWidth("Armor Class: " + e.getArmorClass(), font);
        double w3 = getTextWidth("Hit Points: " + e.getCurrentHitPoints() + "/ " + e.getHitPoints(), font);
        double w4 = getTextWidth("Initiative: " + e.getInitiative(), font);

        return (float) Math.max(Math.max(w1, w2), Math.max(w3, w4));
    }

    private double getTextWidth(String content, Font font) {
        Text text = new Text(content);
        text.setFont(font);
        return text.getLayoutBounds().getWidth();
    }

    private void initButtons(){
        Button b1 = new Button(
            20,20,60f,20,
            "Create",
            () -> System.out.println("Entry Created")
        );
        Button b2 = new Button(
            20 + b1.width + offset,20f,60f,20f,
            "Delete",
            () -> System.out.println("Entry Deleted")
        );
        Button b3 = new Button(
            20 + b1.width + b2.width + offset + offset, 20f, 110f, 20f,
            "SortByInitiative",
            () -> System.out.println("Sorted")
        );

        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
    }

    private void initTestEntities(){
        Entity e1 = new Entity("Goblin", 15, 30, 30, 10, false);
        Entity e2 = new Entity("Player", 18, 50, 50, 15, true);

        enemies.add(e1);
        player.add(e2);
    }


}