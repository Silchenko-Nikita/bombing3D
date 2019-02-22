package com.bombing;

import com.bombing.gameobjs.ShotFlagSetter;
import com.bombing.render.ObjectsRemover;
import com.bombing.utils.visitor.Visitor;

public class Globals {
    public static final Visitor objectsRemover = new ObjectsRemover();
    public static final Visitor shotFlagSetter = new ShotFlagSetter();
}
