package edu.mum.component;

import java.io.IOException;

public interface MessageDisplay {
	
    void display() throws IOException;
    void setMessageSource(MessageOrigin source);
    MessageOrigin getMessageSource();
    
}
