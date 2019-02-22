package com.bombing.gameobjs;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public abstract class RangeObject extends GameObject implements Json.Serializable {
    public Vector3 rangeCoords = new Vector3();

    // ser
    @Override
    public void write(Json json) {
        json.writeValue("rangeCoords", rangeCoords);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        json.readField(this, "rangeCoords", jsonData);
    }
}
