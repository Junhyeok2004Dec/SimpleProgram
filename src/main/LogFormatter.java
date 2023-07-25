package main;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {



    public String getHead(Handler h) {
        return (new Date().getDate()) + " , " + (new Date().getTime()) + "" + "\n";
    }

    @Override
    public String format(LogRecord record) {
        StringBuffer sbf = new StringBuffer(2000);
        sbf.append(new Date().getDate());

        sbf.append("[");
        sbf.append(record.getSourceMethodName());
        sbf.append("] ");

        sbf.append(record.getMessage());
        sbf.append("\n");



        return sbf.toString();
    }


    public String getTail(Handler h) {
        return "Log off \n";
    }


}
