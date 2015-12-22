/**
 * 
 */
package com.takipi.benchmark.logging.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nastacio
 *
 */
public aspect Logging {

	private static Logger logger = LoggerFactory.getLogger("trace.my.project");

	/**
	 * Initialization of all classes in the project.
	 */
	pointcut classInitializer() :
    	staticinitialization(com.takipi.benchmark.logging.tests.*AspectTest) &&
        !within(Logging);

	/**
	 * Execution body for all methods.
	 */
	pointcut method():
        (
          execution(* com.takipi.benchmark.logging.tests..*AspectTest.*(..))
        )
        && !within(Logging);

    /*
     * Join points
     */

    /**
     * After the initialization for all classes in this project.
     */
    after(): classInitializer() {
    	String classname = thisJoinPointStaticPart.getSourceLocation().getWithinType().getName();
    	logger = LoggerFactory.getLogger(classname);
    }
    
	/**
	 * Entry trace statements for all methods.
	 */
	before(): method() {
		if (logger.isInfoEnabled()) {
			logger.info("test with aspect");
		}
	}

}
