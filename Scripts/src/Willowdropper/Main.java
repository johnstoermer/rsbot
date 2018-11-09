package Willowdropper;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;

import java.awt.*;

@ScriptManifest(category = Category.WOODCUTTING, name = "Willowdropper", author = "John", version = 1.0)
public class Main extends AbstractScript{

    @Override
    public void onStart(){
        log("Hi");
    }

    @Override
    public int onLoop() {
        /**
         * Chopping trees: Time to chop some trees, our inventory isn't full. We want to fill it up.
         */
        if(!getInventory().isFull()){
            chopTree("Willow");
        } else {
            getInventory().dropAll((item) -> item != null && !item.getName().contains("axe"));
        }
        return Calculations.random(1500, 3500);
    }

    @Override
    public void onExit() {
        log("Bye");
    }

    private void chopTree(String nameOfTree){
        GameObject tree = getGameObjects().closest(gameObject -> gameObject != null && gameObject.getName().equals(nameOfTree));
        log("yoop");
        if(tree != null && tree.interact("Chop down")){
            sleep(Calculations.random(3000, 4500));
            do {
                log("yey");
                sleepUntil(() -> !getLocalPlayer().isAnimating(), Calculations.random(750, 2500));
            } while(getLocalPlayer().isAnimating());
        }
    }
}