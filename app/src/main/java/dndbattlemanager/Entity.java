package dndbattlemanager;

public class Entity {

    private String name;
    private int ArmorClass;
    private int HitPoints;
    private int Initiative;


    public Entity(String name, int ArmorClass, int HitPoints, int Initiative) {
        this.name = name;
        this.ArmorClass = ArmorClass;
        this.HitPoints = HitPoints;
        this.Initiative = Initiative;
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

}