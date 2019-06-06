package com.lsc.ctesterfx.test;

import com.lsc.ctesterfx.dao.Test;
import com.lsc.ctesterfx.printer.Printer;
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.util.Pair;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * Class that manages the compilation and loading of the test files.
 *
 * @author dma@logossmartcard.com
 */
public class TestLoader extends ClassLoader
{
    private static final String PACKAGE = "runnables.";
    private static final String RUN_METHOD = "run";

    // Single instance of a TestLoader.
    private static TestLoader mTestLoader;
    private static Printer mPrinter;

    private TestLoader()
    {
        // Get a printer instance to log stuff.
        mPrinter = Printer.newInstance();
    }

    public static synchronized TestLoader newInstance()
    {
        if (mTestLoader == null)
        {
            mTestLoader = new TestLoader();
        }

        return mTestLoader;
    }

    /**
     * Method that compiles the tests given. It automatically creates the correct folder structure
     * and sets the classpath.
     *
     * @param test: test file to be compiled.
     * @return true if the compilation is succesful, false otherwise.
     * @throws Exception
     */
    public boolean compile(final Test test) throws Exception
    {
        Path dest = Paths.get(test.getPath());
        File[] tests = new File[]{ test.getFile() };

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        try (StandardJavaFileManager fm = compiler.getStandardFileManager(null, null, null))
        {
            Iterable<? extends JavaFileObject> fileObjects = fm.getJavaFileObjectsFromFiles(Arrays.asList(tests));

            List<String> options = new ArrayList<>();

            // Create the folder structure.
            options.add("-d");
            options.add(dest.toString());

            // Sets the classpath.
            options.add("-cp");
            options.add(System.getProperty("java.class.path"));

            JavaCompiler.CompilationTask task =
                compiler.getTask(null, fm, null, options, null, fileObjects);

            return task.call();
        }
    }

    /**
     * Method that dynamically loads the .class file previously generated.
     *
     * @param test: test to be loaded.
     * @return Pair containing the object and the method 'run'. Null if there's an exception.
     */
    public Pair<Object, Method> load(final Test test)
    {
        try {
            // create FileInputStream object
            File file = new File(test.getPath());

            // Convert File to a URL
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            // Create a new class loader with the directory
            ClassLoader cl = new URLClassLoader(urls);

            // Load in the class; Test.class. Should be located in path + \runnables\
            Class cls = cl.loadClass(PACKAGE + test.getName());
            Object obj = cls.newInstance();
            Method method = cls.getDeclaredMethod(RUN_METHOD);

            return new Pair<>(obj, method);

        } catch (MalformedURLException | ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
}
