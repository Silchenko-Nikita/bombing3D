package com.bombing.builders;

import com.bombing.render.RenderableObject;

public interface ObjectBuildDirector {
    RenderableObject buildObject(RenderableObjectBuilder objectBuilder);
}
