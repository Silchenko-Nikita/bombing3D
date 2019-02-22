package com.bombing.gameobjs;

import com.badlogic.gdx.math.Vector3;
import com.bombing.Globals;
import com.bombing.field.Field;
import com.bombing.utils.observer.Observable;

import java.util.ArrayList;


// Observer
public class GameObjectsMediator extends Observable {
    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    ArrayList<Target> targets = new ArrayList<Target>();

    public int addGameObject(GameObject gameObject){
        if (gameObject instanceof Target) {
            targets.add((Target) gameObject);
        }

        gameObjects.add(gameObject);
        return gameObjects.size() - 1;
    }

    public void removeGameObject(GameObject gameObject){
        if (gameObject instanceof Target) {
            targets.remove((Target) gameObject);
        }
    }

    public void collide(int gameObject1Id, int gameObject2Id) {
        try {
            GameObject gameObject1 = gameObjects.get(gameObject1Id);
            GameObject gameObject2 = gameObjects.get(gameObject2Id);

            if (gameObject1 instanceof Cannonball && gameObject2 instanceof Ground) {

                Cannonball cannonball = (Cannonball) gameObject1;
                Vector3 velocity = cannonball.getVelocity();

                if (cannonball.isShot && velocity.len() < Field.cellSize * 0.02f) {

                    if (!cannonball.landed && !targets.isEmpty()){
                        notifyObservers("cannonball landed", cannonball);
                        cannonball.landed = true;
                    }
                    cannonball.acceptIn(Globals.objectsRemover, 0.5f);
                }

            } else if (gameObject1 instanceof Target && gameObject2 instanceof Ground) {
                gameObject1.acceptIn(Globals.objectsRemover, 0.5f);

                if (targets.contains(gameObject1)) {
                    targets.remove(gameObject1);

                    if (targets.isEmpty()) {
                        notifyObservers("targets are over", null);
                    }
                }
            }

        } catch (Exception e){
            System.out.println(e);
        }
    }
}
