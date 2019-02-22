package com.bombing.utils.observer;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Observable {
    private HashMap<String, ArrayList<Observer>> observersMap = new HashMap<String, ArrayList<Observer>>();

    public void registerObserver(String cls, Observer o){
        if (!observersMap.containsKey(cls)){
            observersMap.put(cls, new ArrayList<Observer>());
        }
        observersMap.get(cls).add(o);
    }

    public void removeObserver(String cls, Observer o){
        if (observersMap.containsKey(cls)){
            observersMap.get(cls).remove(o);
        }
    }

    protected void notifyObservers(String cls, Object data){
        if (observersMap.containsKey(cls)) {
            ArrayList<Observer> observers = observersMap.get(cls);
            for (Observer observer : observers) {
                observer.update(cls, data);
            }
        }
    }
}
