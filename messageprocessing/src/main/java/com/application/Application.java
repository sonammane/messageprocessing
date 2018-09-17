package com.application;

import com.service.MessageService;
public class Application {

	public static void main(String[] args){

	    MessageService messageProcessor = new MessageService();
        messageProcessor.processMessage();
    }
}