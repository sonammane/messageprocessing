package com.message.service;

import org.junit.Test;

public class MessageServiceTest {

    @Test
    public void testWhenAfterTenthMsgIsReceivedLimitThenVerifyAfterTenthMsg() {
        MessageService messageService = new MessageService();
        messageService.processMessage("C:\\Users\\Abhimanyu Taunk\\Documents\\FirstJavaApp\\" +
                "messageprocessing\\messageprocessing\\src\\main\\resources\\MessageSenderTestWithElevenMessages.txt");
        assert(messageService.afterTenthMsg==0);
        assert(messageService.salesMap.size()>0);
    }

    @Test
    public void testWhenAfterFiftythMsgIsReceivedThenVerifyMsgCount() {
        MessageService messageService = new MessageService();
        messageService.processMessage("C:\\Users\\Abhimanyu Taunk\\Documents\\FirstJavaApp\\" +
                "messageprocessing\\messageprocessing\\src\\main\\resources\\MessageSender.txt");
        assert(messageService.msgCount>50);
        assert(messageService.salesMap.size()>0);
    }
}