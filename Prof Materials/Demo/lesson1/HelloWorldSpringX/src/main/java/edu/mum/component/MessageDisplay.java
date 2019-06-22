package edu.mum.component;

public interface MessageDisplay {
    void display();
    void setMessageSource(MessageSource source);
    MessageSource getMessageSource();
}
