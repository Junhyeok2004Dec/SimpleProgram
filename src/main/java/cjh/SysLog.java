package cjh;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class SysLog {
	private final static Logger LOG = Logger.getGlobal();

	Logger rootLogger = Logger.getLogger("");
	Handler[] handlers;
	LogFormatter formatter;
	Handler handler;


	public void init() throws IOException {
		handlers = rootLogger.getHandlers();

		if (handlers == null) {
			if (handlers[0] instanceof ConsoleHandler) {
				rootLogger.removeHandler(handlers[0]);
			}
		}
		//=============================================

		LOG.setLevel(Level.INFO);
		try {
			handler = new FileHandler(tempData.path3, true);
		}catch(IOException e) {
			e.printStackTrace();
		}

		formatter = new LogFormatter();
		handler.setFormatter(formatter);
		LOG.addHandler(handler);
	}

	public void log(String txt) {


		try {
			handler.setFormatter(formatter);


			handler = new FileHandler(tempData.path3, true);
			LOG.addHandler(handler);

			LOG.info(txt);

			LOG.removeHandler(handler);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void err(String txt) {

		try {

			handler.setFormatter(formatter);


			handler = new FileHandler(tempData.path6, true);
			LOG.addHandler(handler);
			LOG.info(txt);

			LOG.removeHandler(handler);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void warning(String txt) {
		LOG.warning(txt);
	}
}
