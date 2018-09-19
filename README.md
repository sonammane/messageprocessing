This is a messageprocessing application developed on Java8 using IntelliJ IDE, Maven,JUnit.
Keeping in mind that With No DB,NO UI,only single threaded environment,max 1or 2 dependency,output format printed on console as mentioned
in tech test problem statement.

1.Assuming that the message text/pattern will be same always like:
<product name> at <price> for all message 1 types e.g. apple at 10p
<number of sales> sales of <product name> at <price> each for all message 2 types e.g. 20 sales of apple at 10p each
<operation> <price> <product name> for all message 3 types. e.g. Add 20p apple
  
2.As we can choose any interface to show a 3rd party message system. Here I have used a text file containing  predefined messages.
and reading the messages from this file to be proceesed in my application to generate the report. FileRdr.java will deal with reading the message from the MessageSender.txt file.

3.Application.java is application Entry point Class having main method.
4.MessageService class has all the business logic of how to process each message.
5.SaleRecord is a Bean to hold the value of the product or any sale record, its total count and adjustment if any.
5.ReportGenerator deals with the logic of report generation after 10th and 50th message.
6.ApplicationConstants.java is a class contains all the string constants declared/used through out the application source code.

7.Added 2 dependency only as mentioned the Tech test problem statement in pom.xml, Junit and System rules to write test for system.print code used.

8. Written JUnit test with 100% code coverage. Created a MessageSenderTestWithElevenMessage.txt for JUnit having 11 messages for testing some scenarios.

9. Added Java Docs for all classes and methods of the source code.

10. Due to dependency limitations of max 2 could not use spring framework & mockito, so as to use autowiring & mock. As it is a single threaded environment , have instantaited the classes with new operator in old traditional way.



