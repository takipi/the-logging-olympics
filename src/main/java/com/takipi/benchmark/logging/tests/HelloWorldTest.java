package com.takipi.benchmark.logging.tests;

public class HelloWorldTest extends BaseTest
{
	@Override
	protected void test()
	{
		logger.info("hello world test");
	}
}
