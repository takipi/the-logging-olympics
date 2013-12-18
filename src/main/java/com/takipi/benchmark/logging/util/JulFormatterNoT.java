package com.takipi.benchmark.logging.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class JulFormatterNoT extends Formatter 
{
	Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
	
	@Override
	public String format(LogRecord record)
	{
		return record.getLevel() + " " +
			record.getSourceClassName() + " - " +
			record.getMessage() + "\n";
	}
}
