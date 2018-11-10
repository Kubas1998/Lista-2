package com.obsidiam.output;

import com.obsidiam.util.BasicHandler;
import com.obsidiam.util.model.Summary;
import com.obsidiam.util.model.User;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 *  Manages standard output and formatters.
 */
final public class OutputController {
    private static volatile OutputController controllerInstance = new OutputController();
    final private StandardOutputFormatter basicFormatter = (StandardOutputFormatter) StandardFormatterFactory.getInstance().getFormatter();
    final private SummaryOutputFormatter summaryFormatter = (SummaryOutputFormatter) StandardFormatterFactory.getInstance().getFormatter(FormatterType.SUMMARY);

    private OutputController(){}

    public static synchronized OutputController getControllerInstance(){
        return controllerInstance;
    }

    /**
     * Prints standard LogRecord object to the standard input using given basicFormatter
     *
     * @param record LogRecord object
     * @see StandardOutputFormatter
     */
    public void printMessage(LogRecord record){
        BasicHandler logHelper = new BasicHandler();
        logHelper.setFormatter(basicFormatter);
        if(record.getLevel() == Level.WARNING || record.getLevel() == Level.SEVERE) printSeparator();
        logHelper.publish(record);
        if(record.getLevel() == Level.WARNING || record.getLevel() == Level.SEVERE) printSeparator();
    }

    /**
     * Prints summary object to the standard input using prepared formatter for it.
     *
     * @param summary Summary class object to be printed to stdout.
     * @see Summary
     * @see SummaryOutputFormatter
     */
    public void printSummary(Summary summary){
        BasicHandler logHelper = new BasicHandler();
        summaryFormatter.setSummary(summary);
        logHelper.setFormatter(summaryFormatter);
        LogRecord logRecord = new ContentAdapter(summary).toLogRecord();
        logHelper.publish(logRecord);
    }

    /**
     * Prints options to the standard output.
     * @param options
     * @see List
     */
    @SuppressWarnings("unused")
    @Deprecated

    public void printUser(User user){
       System.out.println(user.toString());
    }


    public void printOptions(List<String> options){
        for (int i = 0; i < options.size(); i++){
            System.out.println(i + 1 +  ". " + options.get(i));
        }
    }

    //Prints prompt
    public void printPrompt(){
        System.out.print(">");
    }
    //Prints separator
    public void printSeparator(){
        StringBuilder separator = new StringBuilder();
        for(int i = 0; i < 40; i++){
            separator.append("=");
        }
        System.out.println(separator);
    }

    /**
     * Class responsible for adapting content to the LogRecord object properly.
     */
    private class ContentAdapter{
        private Summary summary;
        ContentAdapter(Summary summary){
            this.summary = summary;
        }

        LogRecord toLogRecord(){
            return new LogRecord(Level.ALL, summary.toString());
        }
    }

    private class UserContentAdapter {
        private User user;
        UserContentAdapter (User user) {this.user = user;}

        LogRecord toLogRecord(){return new LogRecord(Level.ALL, user.toString());}
    }
}
