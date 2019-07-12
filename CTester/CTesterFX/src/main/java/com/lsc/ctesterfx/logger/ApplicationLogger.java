package com.lsc.ctesterfx.logger;

/**
 * This layer will manage the different loggers in the system. It will store
 * one instance of Printer (there can only be one in the system) and one instance
 * of FileLogger.
 *
 * As every test needs one FileLogger (different files), the reference must be changed
 * everytime a new test starts.
 *
 * @author dma@logossmartcard.com
 */
public class ApplicationLogger extends AbstractLogger
{
    private static ApplicationLogger applicationLogger;
    // Internal references to the different loggers.
    private Printer printer;
    private FileLogger fileLogger;

    // Mode
    private MODE mode;

    public enum MODE
    {
        GUI,
        CL_ONLY
    }

    private ApplicationLogger() {}

    public static synchronized ApplicationLogger newInstance()
    {
        if (applicationLogger == null)
        {
            applicationLogger = new ApplicationLogger();
        }

        return applicationLogger;
    }

    /**
     * Sets if the logger should log something to the GUI or
     * just to the file.
     *
     * @param mode GUI or CL_ONLY mode.
     */
    public void setMode(MODE mode)
    {
        if (mode == MODE.GUI)
        {
            printer = Printer.newInstance();
        }

        this.mode = mode;
    }

    /**
     * Sets a new FileLogger reference to be used.
     *
     * @param fileLogger: reference to a FileLogger.
     */
    public void setFileLogger(FileLogger fileLogger)
    {
        this.fileLogger = fileLogger;
    }

    @Override
    public void log(String text)
    {
        if (mode == MODE.GUI) printer.log(text);
        fileLogger.log(text);
    }

    @Override
    public void logComment(String text)
    {
        if (mode == MODE.GUI) printer.logComment(text);
        fileLogger.logComment(text);
    }

    @Override
    public void logError(String text)
    {
        if (mode == MODE.GUI) printer.logError(text);
        fileLogger.logError(text);
    }

    @Override
    public void logWarning(String text)
    {
        if (mode == MODE.GUI) printer.logWarning(text);
        fileLogger.logWarning(text);
    }

    @Override
    public void logDebug(String text)
    {
        if (mode == MODE.GUI) printer.logDebug(text);
        fileLogger.logDebug(text);
    }

    @Override
    public void logSuccess(String text)
    {
        if (mode == MODE.GUI) printer.logSuccess(text);
        fileLogger.logSuccess(text);
    }
}
