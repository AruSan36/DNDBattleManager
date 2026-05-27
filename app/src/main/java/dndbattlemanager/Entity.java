package dndbattlemanager;

import javafx.scene.text.Font;

public class Entity {

    private String name;
    private int ArmorClass;
    private int HitPoints;
    private int CurrentHitPoints;
    private int Initiative;
    private boolean isPlayer;
    private float width;
    private float height;
    private float startPosX;
    private float startPosY;
    

    public Entity(String name, int ArmorClass, int HitPoints, int CurrentHitPoints, int Initiative, boolean isPlayer) {
        this.name = name;
        this.ArmorClass = ArmorClass;
        this.HitPoints = HitPoints;
        this.CurrentHitPoints = CurrentHitPoints;
        this.Initiative = Initiative;
        this.isPlayer = isPlayer;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateArmorClass(int ArmorClass) {
        if(ArmorClass <= 0) {
            System.out.println("Armor Class must be greater than 0");
        } else {
            this.ArmorClass = ArmorClass;
        }
    }


    public void updateMaxHP(int HP){
        this.HitPoints = HP;
    }

    public void updateCurrentHP(int HP){
        this.CurrentHitPoints = HP;
    }

    public void updateInitiative(int Initiative) {
        this.Initiative = Initiative;
    }

    public void updateIsPlayer(boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

    public boolean contains(double x, double y){
        return startPosX < x && startPosX + width > x 
        && startPosY < y && startPosY + height > y;
    }

    public static boolean convertTextToisPlayer(String txt){
        switch(txt){
            case "ja", "Ja", "yes", "Yes" , "j", "y": return true;
            case "nein", "Nein" , "no" , "No" , "n": return false;
            default: return false;
        }
    }

    

    public String getName() {
        return name;
    }

    public int getArmorClass() {
        return ArmorClass;
    }

    public int getHitPoints() {
        return HitPoints;
    }

    public int getInitiative() {
        return Initiative;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public int getCurrentHitPoints() {
        return CurrentHitPoints;
    }

    public void setWidth(float w){
        this.width = w;
    }

    public void setHeight(float h){
        this.height = h;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public void setStartPosX(float startPosX) {
        this.startPosX = startPosX;
    }

    public void setStartPosY(float startPosY) {
        this.startPosY = startPosY;
    }

    public float getStartPosX() {
        return startPosX;
    }

    public float getStartPosY() {
        return startPosY;
    }

}