package com.takipi.benchmark.logging.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class JulFormatter extends Formatter {
	private final MessageFormat messageFormat = new MessageFormat(
			"{0,date,yyyy-MM-dd} {0,time,HH:mm:ss,SSS} [{1}] {2} {3} - {4}\n");

	@Override
	public String format(LogRecord record) {
		Object[] arguments = new Object[5];
		arguments[0] = new Date(record.getMillis());
		arguments[1] = Thread.currentThread().getName();
		arguments[2] = record.getLevel();
		arguments[3] = record.getSourceClassName();
		arguments[4] = record.getMessage();

		String formattedRecord = messageFormat.format(arguments);

		Throwable t = record.getThrown();

		if (t != null) {
			StringWriter sw = new StringWriter();
			sw.write(formattedRecord);
			PrintWriter pw = new PrintWriter(sw);
			try {
				t.printStackTrace(pw);
				formattedRecord = sw.toString();
			} finally {
				pw.close();
			}
		}

		return formattedRecord;
	}
}
