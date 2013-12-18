package com.takipi.benchmark.logging.tests;

public class ThrowableTest extends ToStringTest
{
	@Override
	protected void test()
	{
		logger.info("to string test: [lala] [" + new ToStringObject() + "] [" + getRandomList() + "]", new IllegalStateException("throwable test"));
	}
}
