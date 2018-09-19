package com.message.app;

import com.message.service.MessageService;

/**
 * Application Entry point Class having main method.
 */
public class Application {

    /**
     *  This main method is the entry point of the application.
     *  It instantiates MessageService and passes the file path of the MessageSender.txt which acts as a interface for
     *  3rd party messaging system.
     * @param args of type {@link String []}
     */
	public static void main(String[] args){
	    MessageService messageService = new MessageService();
        messageService.processMessage("C:\\Users\\Abhimanyu Taunk\\Documents\\FirstJavaApp\\" +
                "messageprocessing\\messageprocessing\\src\\main\\resources\\MessageSender.txt");
    }
}