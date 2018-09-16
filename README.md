# messageprocessing

Assuming that the message text/pattern will be same always like:
<product name> at <price> for all message 1 types
<number of sales> sales of <product name> at <price> each for all message 2 types
<operation> <price> <product name> for all message 3 types


As we can choose any interface to show a 3rd party message system. Here i have used a text file containing  predefined messages.
and reading the messages from this file to be proceesed in my application to generate the report. FileRdr.java will deal with reading the message from the MessageSender.txt file.

ReportGenerator deals with the logic of repost generation after 10th and 50th message.

SaleRecord is a Bean to hold the value of the product, its total count and adjustment if any.

Main application class is Application.java

MessagingProcessor.java has the business logic.



