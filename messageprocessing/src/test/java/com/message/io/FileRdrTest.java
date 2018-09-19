package com.message.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FileRdrTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void testIfFilenameIsNull() {
        List<String> expectedMsgList = new ArrayList<String>();
        FileRdr fileRdrTest = new FileRdr();
        List<String> actaulMsgList =  fileRdrTest.getMessagesFromFile(null);
        assertEquals(actaulMsgList,expectedMsgList);
        assertEquals("No new message to process.", systemOutRule.getLog());
    }

    @Test
    public void testIfFilenameIsEmpty() {
        List<String> expectedMsgList = new ArrayList<String>();
        FileRdr fileRdrTest = new FileRdr();
        List<String> actaulMsgList =  fileRdrTest.getMessagesFromFile("");
        assertEquals(actaulMsgList,expectedMsgList);
        assertEquals("No new message to process.", systemOutRule.getLog());
    }

    @Test
    public void testIfFilenameIsNotEmptyAndReadsMessages() {
        FileRdr fileRdrTest = new FileRdr();
        List<String> actaulMsgList =  fileRdrTest.getMessagesFromFile(
                "C:\\Users\\Abhimanyu Taunk\\Documents\\FirstJavaApp\\messageprocessing\\messageprocessing" +
                        "\\src\\main\\resources\\MessageSender.txt");
        assert(actaulMsgList.size()==51);

    }

    @Test
    public void testIfFilenameDoesNotExistGivesException() {
        FileRdr fileRdrTest = new FileRdr();
        fileRdrTest.getMessagesFromFile("src\\main\\resources\\MessageSender1.txt");
        assertEquals("No new message to process.", systemOutRule.getLog());
    }
}