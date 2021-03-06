package com.lsc.ctesterfx.background;

import com.lsc.ctesterfx.background.MultithreadController.TYPE;
import com.lsc.ctesterfx.test.Test.TEST_STATE;
import com.lsc.ctesterfx.test.TestController;
import com.lsc.ctesterfx.test.TestLoader;
import java.lang.reflect.Method;
import java.util.List;
import javafx.concurrent.Task;
import javafx.util.Pair;
import org.apache.log4j.Logger;

/**
 * Class to run the compilation process in the background.
 *
 * @author dma@logossmartcard.com
 */
public class CompilationTask extends Task
{
    private static final Logger LOGGER = Logger.getLogger(CompilationTask.class);

    // Instance to the test controller.
    private final TestController testController;

    public CompilationTask(TestController testController)
    {
        this.testController = testController;
    }

    @Override
    protected Object call() throws Exception
    {
        return compileTest();
    }

    /**
     * Compiles and loads the test, returning the object and a list of methods.
     *
     * @return Pair containing the object and the methods to be invoked.
     */
    private Pair<Object, List<Method>> compileTest()
    {
        try
        {
            boolean success = true;

            LOGGER.info("Compiling '" + testController.getTestName() + "'");

            // First we need to notify the controller that the task has started.
            testController.notifyStartTest(TYPE.COMPILATION);

            // Compilation process starts here.
            testController.getLogger().logComment("Compiling " + testController.getTestName() + "\n");
            testController.setState(TEST_STATE.COMPILING);

            Pair<Object, List<Method>> result = null;
            TestLoader testLoader = TestLoader.newInstance();

            try
            {
                // Compile and load the test class.
                if (testLoader.compile(testController.getTest()))
                {
                    LOGGER.info("Compilation of '" + testController.getTestName() + "' succesful");

                    if ((result = testLoader.load(testController.getTest())) == null)
                    {
                        LOGGER.error("Loading of '" + testController.getTestName() + "' failed");

                        success = false;
                    }
                    else
                    {
                        LOGGER.info("Loading of '" + testController.getTestName() + "' succesful");

                        testController.getLogger().logComment("Compilation of " + testController.getTestName() + " succesful!\n");
                        testController.setState(TEST_STATE.COMPILATION_OK);
                    }
                }
                else
                {
                    LOGGER.error("Compilation of '" + testController.getTestName() + "' failed");

                    success = false;

                    testController.getLogger().logError("Compilation of " + testController.getTestName() + " failed\n");
                    testController.setState(TEST_STATE.COMPILATION_FAILED);
                }

            } catch (Exception ex) {
                LOGGER.error("Exception compiling test (JavaHome not configured in config.xml?)");
                LOGGER.error(ex);

                success = false;

                testController.getLogger().logError("Compilation of " + testController.getTestName() + " failed");
                testController.getLogger().logError("Exception: " + ex.toString() + "\n");
                testController.setState(TEST_STATE.COMPILATION_FAILED);

            } finally {
                testController.notifyFinishTest(success, TYPE.COMPILATION);
            }

            return result;

        } catch (Exception e) {
            LOGGER.error("Unexpected exception running test (" + e.getMessage() + ")");
        }

        return null;
    }
}
