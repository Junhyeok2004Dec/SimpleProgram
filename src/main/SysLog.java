package main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.*;

public class SysLog {
    private final static Logger LOG = Logger.getGlobal();

    Logger rootLogger = Logger.getLogger("");
    Handler[] handlers;
    LogFormatter formatter;
    Handler handler;


    public void init() throws IOException {
        handlers = rootLogger.getHandlers();

        if(handlers == null) {
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }}
        //=============================================

        LOG.setLevel(Level.INFO);


        handler = new ConsoleHandler();
        handler.setEncoding("UTF-8");
        handler = new FileHandler(tempData.path3, true);
        formatter = new LogFormatter();
        handler.setFormatter(formatter);
        LOG.addHandler(handler);
    }
    public void log(String txt){

        LOG.info(txt);
    }

    public void err(String txt) {
        LOG.severe(txt);
    }

    public void warning(String txt) {
        LOG.warning(txt);
    }
}
