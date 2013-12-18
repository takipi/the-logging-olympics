package com.takipi.benchmark.logging.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class JulFormatter extends Formatter
{
	Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
	
	@Override
	public String format(LogRecord record) 
	{
		String formattedRecord = formatter.format(new Date(record.getMillis())) + " " +
			"[" + Thread.currentThread().getName() + "] " + 
			record.getLevel() + " " +
			record.getSourceClassName() + " - " +
			record.getMessage() + "\n";
				
		Throwable t = record.getThrown();

		if (t != null)
		{
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			
			return formattedRecord + sw.toString();
		}
		
		return formattedRecord;
	}
}
