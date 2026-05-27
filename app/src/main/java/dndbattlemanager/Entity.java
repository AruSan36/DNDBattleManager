package dndbattlemanager;

public class Entity {

    private String name;
    private int ArmorClass;
    private int HitPoints;
    private int CurrentHitPoints;
    private int Initiative;
    private boolean isPlayer;


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

/**
 * Updates the hit points of the entity. If the hit points are greater than 0, they will be added to the current hit points. If the hit points are less than 0, they will be subtracted from the current hit points.
 * @param HitPoints
 */

    public void updateHitPoints(int HitPoints) {
        if(HitPoints > 0) {
            this.HitPoints += HitPoints;
        } else {
            this.HitPoints -= HitPoints;
        }
    }

    public void updateInitiative(int Initiative) {
        this.Initiative = Initiative;
    }

    public void updateIsPlayer(boolean isPlayer) {
        this.isPlayer = isPlayer;
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

}