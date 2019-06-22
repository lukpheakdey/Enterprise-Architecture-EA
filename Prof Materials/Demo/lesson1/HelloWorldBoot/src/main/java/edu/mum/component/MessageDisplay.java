package edu.mum.component;

public interface MessageDisplay {
    void display();
    void setMessageSource(MessageOrigin source);
    MessageOrigin getMessageSource();
}
