package FishAndCook;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.input.Keyboard;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;

import java.awt.*;

@ScriptManifest(category = Category.FISHING, name = "Fish and Cook!", author = "John", version = 1.0)
public class Main extends AbstractScript{

    Area fishArea = new Area(3238, 3145, 3245, 3155, 0);
    Area cookArea = new Area(3222, 3172, 3226, 3174, 0);

    boolean fishing = true;

    @Override
    public void onStart(){
        log("Hi");
    }

    @Override
    public int onLoop() {
        if (fishing) {
            log("yuh");
            if (fishArea.contains(getLocalPlayer())) {
                fish("Lure");
            } else {
                if (getWalking().walk(fishArea.getRandomTile())) {
                    sleep(Calculations.random(3000, 5500));
                }
            }
        } else {
            log("nah");
            if (cookArea.contains(getLocalPlayer())) {
                chopLight("Raw shrimps", "Logs", "Dead tree");
            } else {
                if (getWalking().walk(cookArea.getRandomTile())) {
                    sleep(Calculations.random(3000, 5500));
                }
            }
        }
        return Calculations.random(1500, 3500);
    }

    @Override
    public void onExit() {
        log("Bye");
    }

    private void fish(String fishOption) {
        while (!getInventory().isFull()) {
            NPC fishingSpot = getNpcs().closest(NPC -> NPC != null && NPC.getName().equals("Fishing spot"));
            if (fishingSpot != null && fishingSpot.interact(fishOption)) {
                sleep(Calculations.random(3000, 4500));
                do {
                    log("fishin");
                    sleepUntil(() -> !getLocalPlayer().isAnimating(), Calculations.random(750, 2500));
                } while (getLocalPlayer().isAnimating());
            }
        }
        log("we done fishin");
        getInventory().drop("Raw shrimp"); //make a free inventory space
        fishing = false;
    }

    private void chopLight(String foodName, String logName, String nameOfTree) {
        while (getInventory().contains(foodName)) {
            sleep(Calculations.random(3000, 4500));
            GameObject fire = getGameObjects().closest(gameObject -> gameObject != null && gameObject.getName().equals("Fire"));
            if (fire == null) {
                sleep(Calculations.random(1000, 2500));
                if (getInventory().contains(logName)) {
                    getInventory().get("Tinderbox").useOn(logName);
                    do {
                        log("lighting");
                        sleepUntil(() -> !getLocalPlayer().isAnimating(), Calculations.random(750, 2500));
                    } while (getLocalPlayer().isAnimating());
                } else {
                    GameObject tree = getGameObjects().closest(gameObject -> gameObject != null && gameObject.getName().equals(nameOfTree));
                    if (tree != null && tree.interact("Chop down")) {
                        do {
                            log("choppin");
                            sleepUntil(() -> getInventory().contains(logName), Calculations.random(750, 2500));
                        } while (getLocalPlayer().isAnimating());
                    }
                }
            } else {
                sleep(Calculations.random(1000, 2500));
                getInventory().get(foodName).useOn(fire);
                sleep(Calculations.random(1000, 2500));
                getKeyboard().type(" ");
                do {
                    log("cooking");
                    sleepUntil(() -> !getLocalPlayer().isAnimating(), Calculations.random(750, 2500));
                } while (getLocalPlayer().isAnimating());
            }
        }
        fishing = true;
    }
}