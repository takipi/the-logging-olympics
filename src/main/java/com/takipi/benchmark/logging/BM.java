package com.takipi.benchmark.logging;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.takipi.benchmark.logging.tests.BaseTest;

public class BM
{
	static int THREAD_COUNT = 10;
	static long TIMEOUT = 60 * 1000;
	
	static String logFolder = "logs/";
	static String logFile = "test.log";
	static String testNamePrefix = "com.takipi.benchmark.logging.tests.";
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{	
		if (args.length < 3)
		{
			System.out.println("Not enough arguments. Please supply [logger name] [test name (= HelloWorldTest|ToStringTest|DebugTest|ThrowableTest|TimeThreadTest|HelloWorldAspectTest)] [test id]");
			System.exit(0);
		}
	
		try 
		{
			String loggerName = args[0];
			String testName = args[1];
			String id = args[2];
			
			String className = testNamePrefix + ((testName.equals("TimeThreadTest")) ? "ToStringTest" : testName); 
			
			Class<BaseTest> testClass = (Class<BaseTest>) Class.forName(className);
						
			ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
			
			for (int j = 0; j < THREAD_COUNT; j++)
			{
				threadPool.execute(testClass.newInstance());
			}
			
			threadPool.awaitTermination(TIMEOUT, TimeUnit.MILLISECONDS);
			threadPool.shutdownNow();
			
			saveLogFile(loggerName + "-" + testName + "-" + id);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void saveLogFile(String name)
	{
		copyFile(logFolder + logFile, logFolder + name);
		clearFileContent(logFolder + logFile);
		
		System.out.println("Saving " + name);
	}
	
	public static void sleep(long millis)
	{
		try
		{
			Thread.sleep(millis);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void copyFile(String source, String dest)
	{
		InputStream inStream = null;
		OutputStream outStream = null;
		
		try
		{
			File sourceFile = new File(source);
			File destFile = new File(dest);
			
			inStream = new FileInputStream(sourceFile);
			outStream = new FileOutputStream(destFile);
			
			byte[] buffer = new byte[1024];
			int length;

			while ((length = inStream.read(buffer)) > 0)
			{
				outStream.write(buffer, 0, length);
			} 
			
			inStream.close();
			outStream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void clearFileContent(String filename)
	{
		PrintWriter writer = null;
		
		try
		{
			writer = new PrintWriter(filename);
			writer.print("");
			writer.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}