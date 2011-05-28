package clickgame.service;
import java.util.Date;

import javax.swing.JTextArea;

import org.orman.util.logging.ILogger;
import org.orman.util.logging.LoggingLevel;

public class JTextAreaLogAppender implements ILogger {

	private long start;
	private JTextArea textArea;
	private LoggingLevel level = LoggingLevel.WARN;
	
	public JTextAreaLogAppender(JTextArea textArea){
		this.textArea = textArea;
		start = System.currentTimeMillis();
	}
	public void trace(String message, Object... params) {
		if (isLoggable(LoggingLevel.TRACE))
			log("TRACE", String.format(message, params));
	}

	public void debug(String message, Object... params) {
		if (isLoggable(LoggingLevel.DEBUG))
			log("DEBUG", String.format(message, params));
	}

	public void info(String message, Object... params) {
		if (isLoggable(LoggingLevel.INFO))
			log("INFO", String.format(message, params));
	}

	public void warn(String message, Object... params) {
		if (isLoggable(LoggingLevel.WARN))
			log("WARN", String.format(message, params));
	}

	public void error(String message, Object... params) {
		if (isLoggable(LoggingLevel.ERROR))
			log("ERROR", String.format(message, params));
	}

	public void fatal(String message, Object... params) {
		if (isLoggable(LoggingLevel.FATAL))
			log("FATAL", String.format(message, params));
	}


	private boolean isLoggable(LoggingLevel logLevel){
		return (this.level.getValue() <= logLevel.getValue());
	}
	public void setLevel(LoggingLevel level) {
		this.level  = level == null ? LoggingLevel.WARN : level;
	}
	
	private void log(String level, Object message) {
		textArea.append((System.currentTimeMillis()-start) + " [" + level + "] "
				+ message + '\n');
	}
}
