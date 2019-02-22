package com.bombing.field;

import com.badlogic.gdx.math.Vector3;
import com.bombing.gameobjs.RangeObject;
import com.bombing.render.RenderableObject;
import com.bombing.utils.visitor.Visitor;

public class SplitCoordsConverter implements Visitor {
    @Override
    public void visit(Object object) {
        if (object instanceof RangeObject){
            RangeObject rangeObject = (RangeObject) object;

            Vector3 fieldCoords = new Vector3();

            int offset = rangeObject.rangeCoords.x > 0 ? 2: -2;

            fieldCoords.x = (rangeObject.rangeCoords.x + offset) * Field.cellSize;
            fieldCoords.y = rangeObject.rangeCoords.y * Field.cellSize;
            fieldCoords.z = ((Field.rangeDepth / 2) - rangeObject.rangeCoords.z) * Field.cellSize;

            rangeObject.setPos(fieldCoords);
        }
    }
}
