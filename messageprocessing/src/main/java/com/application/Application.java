package com.application;

import com.processor.MessageProcessor;

public class Application {

	public static void main(String[] args){
        MessageProcessor messageProcessor = new MessageProcessor();
        messageProcessor.messageProcessing();
    }
}