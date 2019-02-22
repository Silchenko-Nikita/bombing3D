package com.bombing.catapult;

import com.badlogic.gdx.math.Vector3;
import com.bombing.field.Field;
import com.bombing.gameobjs.Cannonball;
import com.bombing.utils.observer.Observer;

import java.util.ArrayList;

public class CatapultCharger {
    private ArrayList<Cannonball> cannonballs;
    private Catapult catapult;

    public CatapultCharger(Catapult catapult){
        this.catapult = catapult;
    }

    public void setCannonballs(ArrayList<Cannonball> cannonballs) {
        this.cannonballs = cannonballs;
        positionCannonballs();
    }

    private void positionCannonballs(){
        Vector3 pos = catapult.getInitialCannonballPos();
//        pos.add(0, 0, -1 * Field.cellSize);
        for (int i = 0; i < cannonballs.size(); i++) {
            Cannonball cannonball = cannonballs.get(i);
//            pos.add(Field.cellSize, 0, Field.cellSize * (float) Math.sqrt((double) i));
            pos.add(Field.cellSize, 0, 0);
            cannonball.setPos(pos);
        }
    }

    public boolean cannonballsOver() {
        return cannonballs.isEmpty();
    }

    public void charge(){
        Cannonball cannonball = cannonballs.get(0);

        if (cannonball != null){
            catapult.charge(cannonball);
            cannonballs.remove(cannonball);
        }
        
        positionCannonballs();
    }
}
