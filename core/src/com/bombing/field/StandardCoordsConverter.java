package com.bombing.field;

import com.badlogic.gdx.math.Vector3;
import com.bombing.gameobjs.RangeObject;
import com.bombing.render.RenderableObject;
import com.bombing.utils.visitor.Visitor;

public class StandardCoordsConverter implements Visitor {
    @Override
    public void visit(Object object) {
        if (object instanceof RangeObject){
            RangeObject rangeObject = (RangeObject) object;

            Vector3 fieldCoords = new Vector3();
            fieldCoords.x = rangeObject.rangeCoords.x * Field.cellSize;
            fieldCoords.y = rangeObject.rangeCoords.y * Field.cellSize;
            fieldCoords.z = ((Field.rangeDepth / 2) - rangeObject.rangeCoords.z) * Field.cellSize;

            rangeObject.setPos(fieldCoords);
        }
    }
}
