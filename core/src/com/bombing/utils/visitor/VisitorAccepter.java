package com.bombing.utils.visitor;

public interface VisitorAccepter {
    void accept(Visitor visitor);
    void acceptIn(Visitor visitor, float seconds);
}
