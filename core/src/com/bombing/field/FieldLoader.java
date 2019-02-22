package com.bombing.field;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;


public class FieldLoader {
    public Field loadField(int fieldlId){
        try {
            FileHandle file = Gdx.files.internal(String.format("fields/%d.json", fieldlId));
            Json json = new Json();
            Field field = json.fromJson(Field.class, file.readString());
            return field;
        } catch (Exception e){
            return null;
        }
    }
}
