package com.takipi.benchmark.logging.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToStringTest extends BaseTest
{
	@Override
	protected void test()
	{
		logger.info("to string test: [lala] [{}] [{}]", new ToStringObject(), getRandomList());
	}
	
	class ToStringObject
	{
		private String name;
		private int age;
		
		public ToStringObject()
		{
			Random rand = new Random();
			this.name = Long.toString(rand.nextInt(), 36);
			this.age = rand.nextInt(120);
		}
		
		@Override
		public String toString()
		{
			return "Name: " + name + ", Age: " + age + ", :)";
		}
	}
	
	protected List<Integer> getRandomList()
	{
		List<Integer> list = new ArrayList<Integer>();
		Random rand = new Random();
		
		for (int i = 0; i < 10; i++)
		{
			list.add(rand.nextInt(100));
		}
		
		return list;
	}
}
