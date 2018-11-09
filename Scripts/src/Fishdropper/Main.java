package Fishdropper;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;

import java.awt.*;

@ScriptManifest(category = Category.FISHING, name = "Fishdropper", author = "John", version = 1.0)
public class Main extends AbstractScript{

    @Override
    public void onStart(){
        log("Hi");
    }

    @Override
    public int onLoop() {
        if(!getInventory().isFull()){
            fish("Lure");
        } else {
            getInventory().dropAll((item) -> item != null && !item.getName().contains("axe") && !item.getName().contains("rod") && !item.getName().contains("Feather"));
        }
        return Calculations.random(300, 600);
    }

    @Override
    public void onExit() {
        log("Bye");
    }

    private void fish(String fishOption) {
        NPC fishingSpot = getNpcs().closest(NPC -> NPC != null && NPC.getName().equals("Rod Fishing spot"));
        if (fishingSpot != null && fishingSpot.interact(fishOption)) {
            sleep(Calculations.random(3000, 4500));
            do {
                log("fishin");
                sleepUntil(() -> !getLocalPlayer().isAnimating(), Calculations.random(750, 2500));
            } while (getLocalPlayer().isAnimating());
            sleep(Calculations.random(1000, 2500));
        }
    }

    private void cook(){
        GameObject range = getGameObjects().closest(gameObject -> gameObject != null && gameObject.getName().equals("Range"));
        if(range != null && range.interact("Cook")){
            sleep(Calculations.random(3000, 4500));
            getKeyboard().type(" ");
            do {
                log("cookin");
                sleepUntil(() -> !getLocalPlayer().isAnimating(), Calculations.random(750, 2500));
            } while(getLocalPlayer().isAnimating());
        }
    }
}