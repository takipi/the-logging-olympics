package com.takipi.benchmark.logging.tests;

public class DebugTest extends ToStringTest
{
	@Override
	protected void test()
	{
		logger.info("to string test: [lala] [{}] [{}]", new ToStringObject(), getRandomList());
		logger.debug("to string test: [lala] [{}] [{}]", new ToStringObject(), getRandomList());
	}
}
