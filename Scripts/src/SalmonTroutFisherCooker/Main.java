package SalmonTroutFisherCooker;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;

import java.awt.*;

@ScriptManifest(category = Category.FISHING, name = "Fish and Cook Varrock", author = "John", version = 1.0)
public class Main extends AbstractScript{

    Area fishArea = new Area(3238, 3145, 3245, 3155, 0);
    Area cookArea = new Area(3230, 3198, 3235, 3195, 0);

    @Override
    public void onStart(){
        log("Hi");
    }

    @Override
    public int onLoop() {
        if(!getInventory().isFull()){
            if(fishArea.contains(getLocalPlayer())){
                fish("Net"); //change "Tree" to the name of your tree.
            }else{
                if(getWalking().walk(fishArea.getRandomTile())){
                    sleep(Calculations.random(1000, 3000));
                }
            }
        }

        /**
         * Banking: Time to bank our logs. Our inventory is full, we want to empty it.
         */
        if(getInventory().isFull()){ //it is time to bank
            if(cookArea.contains(getLocalPlayer())){
                cook();
            }else{
                if(getWalking().walk(cookArea.getRandomTile())){
                    sleep(Calculations.random(1000, 3000));
                }
            }
        }
        return Calculations.random(300, 600);
    }

    @Override
    public void onExit() {
        log("Bye");
    }

    private void fish(String fishOption) {
        NPC fishingSpot = getNpcs().closest(NPC -> NPC != null && NPC.getName().equals("Fishing spot"));
        if (fishingSpot != null && fishingSpot.interact(fishOption)) {
            sleep(Calculations.random(3000, 4500));
            do {
                sleepUntil(() -> !getLocalPlayer().isAnimating(), Calculations.random(750, 2500));
            } while (getLocalPlayer().isAnimating());
        }
    }

    private void cook(){
        while (getInventory().contains((item) -> item != null && !item.getName().contains("Raw"))) {
            GameObject fire = getGameObjects().closest(gameObject -> gameObject != null && gameObject.getName().equals("Fire"));
            if (fire != null && fire.interact("Cook")) {
                sleep(Calculations.random(2000, 3000));
                getKeyboard().type(" ");
                sleep(Calculations.random(2000, 3000));
                do {
                    log("cookin");
                    sleepUntil(() -> !getLocalPlayer().isAnimating(), Calculations.random(750, 2500));
                } while (getLocalPlayer().isAnimating());
            }
        }
        getInventory().dropAll((item) -> item != null && !item.getName().contains("axe") && !item.getName().contains("net"));
    }
}