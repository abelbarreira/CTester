package com.lsc.ctesterfx.background;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class that handles the executors needed in the system.
 *
 * @author dma@logossmartcard.com
 */
public class MultithreadController
{
    public enum TYPE
    {
        COMPILATION,
        EXECUTION;
    }

    // Executor used to compile the test in the background.
    private static ExecutorService mCompilationExecutor;
    // Executor used to execute the test in the background.
    private static ExecutorService mExecutionExecutor;

    /**
     * Initializes the controller creating different executor instances
     *
     * FOR INTERNAL USE ONLY
     */
    public static void initialize()
    {
        mCompilationExecutor = Executors.newFixedThreadPool(1);
        mExecutionExecutor   = Executors.newFixedThreadPool(1);
    }

    /**
     * Frees resources.
     *
     * FOR INTERNAL USE ONLY
     */
    public static void shutdown()
    {
        mCompilationExecutor.shutdownNow();
        mExecutionExecutor.shutdownNow();
    }

    /**
     * Executes a task using the corresponding executor.
     *
     * FOR INTERNAL USE ONLY
     *
     * @param task: task to be executed.
     * @param type: type of executor to be used.
     */
    public static void execute(Runnable task, TYPE type)
    {
        switch (type)
        {
            case COMPILATION:
                mCompilationExecutor.execute(task);
                break;

            case EXECUTION:
                mExecutionExecutor.execute(task);
                break;
        }
    }
}