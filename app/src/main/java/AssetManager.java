
import java.util.HashMap;
import javafx.scene.image.Image;
public class AssetManager {

    public static final HashMap<String, Image> assets = new HashMap<>();

    public static void loadAll(){
        load("DNDIcon", "DnD-Symbol.png");
    }

    private static void load(String key , String filename){
        try{
            Image img = new Image(AssetManager.class.getResourceAsStream("/" + filename));
            
            if(img.isError()){
                System.err.println("Fehler beim laden");
            } else {
                assets.put(key, img);
            }
        } catch(Exception e) {
            System.err.println("Fehler: " + filename + " ->" + e.getMessage());
        
        }
    }

}

