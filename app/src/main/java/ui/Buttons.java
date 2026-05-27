package ui;

import javafx.scene.image.Image;

public class Buttons{

    public float x, y, width, height;
    public String text;
    Runnable action;
    public Image image; 

    public Buttons(float x, float y, float width, float height, String text, Runnable action){

        this.x = x;
        this.y = y;
        this.text = text;
        this.width = width;
        this.height = height; 
        this.action = action;
    }

    public boolean contains(float px, float py){
        return px >= x && px <= x+width 
        && py > y && py <= y+height;
    }

    public void fire(){
        if(action != null){
            action.run();
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }
}
