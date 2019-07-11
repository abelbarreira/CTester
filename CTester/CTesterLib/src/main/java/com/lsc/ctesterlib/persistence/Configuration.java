package com.lsc.ctesterlib.persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * Class to handle the configuration file.
 *
 * @author dma@logossmartard.com
 */
public class Configuration
{
    private static final Logger LOGGER = Logger.getLogger(Configuration.class);

    // Config.xml fields
    public static final String CTESTER = "CTester";
        public static final String JAVA_HOME   = "JavaHome";
        public static final String LAST_READER = "Reader";
        public static final String LAST_PATH   = "TestsPath";

    public static final String VIRGINIZE = "Virginize";
        public static final String MODES = "Modes";
            public static final String MODE = "Mode";
                public static final String KEY        = "Key";
                public static final String PARAMETERS = "Parameters";
                    public static final String PARAMETER  = "Parameter";

    public static final String SHELL = "Shell";
        public static final String READER = "Reader";

    // Config.xml attributes
    public static final String TAG  = "tag";
    public static final String NAME = "name";
    public static final String MAC  = "mac";

    // Private constants
    private static final String CONFIG_PATH =
        System.getProperty("user.dir") + System.getProperty("file.separator") + "Config" + System.getProperty("file.separator");
    private static final String CONFIG_NAME = "config.xml";

    // Reference to the configuration file.
    private final File configurationFile;

    /**
     * Class used to modify the configuration file safely.
     */
    public class Editor
    {
        private Document document;

        /**
         * Constructor.
         */
        public Editor()
        {
            try
            {
                SAXReader reader = new SAXReader();
                document = reader.read(configurationFile);

            } catch (DocumentException ex) {
                LOGGER.error("Exception opening config.xml'");
                LOGGER.error(ex);
            }
        }

        /**
         * Updates the node with a new value.
         *
         * @param key: node to be updated.
         * @param newValue: new value to be set.
         */
        public void edit(String key, String newValue)
        {
            LOGGER.debug("Updating '" + newValue + "' into" + "'" + key + "'");

            document.selectSingleNode("//" + key).setText(newValue);
        }

        /**
         * Commits all the changes done to the document.
         */
        public void commit()
        {
            XMLWriter writer = null;

            try
            {
                LOGGER.debug("Committing changes in config.xml");
                writer = new XMLWriter(new FileWriter(configurationFile));
                writer.write(document);
                LOGGER.debug("Changes saved correctly");

            } catch (UnsupportedEncodingException ex) {
                LOGGER.error("Exception writing config.xml'");
                LOGGER.error(ex);

            } catch (IOException ex) {
                LOGGER.error("Exception writing config.xml'");
                LOGGER.error(ex);

            } finally {
                if (writer != null)
                {
                    try
                    {
                        writer.close();

                    } catch (IOException ex) {
                        LOGGER.error("Exception closing config.xml'");
                        LOGGER.error(ex);
                    }
                }
            }
        }
    }

    /**
     * Constructor.
     */
    public Configuration()
    {
        configurationFile = new File(CONFIG_PATH + CONFIG_NAME);
    }

    /**
     * Creates and returns a new configuration editor.
     *
     * @return reference to a new configuration editor.
     */
    public Editor getEditor()
    {
        return new Editor();
    }

    /**
     * Finds all the elements defined by the series of keys.
     *
     * @param keys list of keys to find the elements.
     * @return all the elements defined by the series of keys.
     */
    public List<Element> getValuesAsList(String... keys)
    {
        StringBuilder xpath = new StringBuilder();
        for (String key : keys)
        {
            xpath.append((xpath.length() == 0) ? "//" : "/");
            xpath.append(key);
        }

        try
        {
            SAXReader reader = new SAXReader();
            Document document = reader.read(configurationFile);

            List<Element> elements = new ArrayList<>();
            List<Node> nodes = document.selectNodes(xpath.toString());
            if ((nodes != null) && !nodes.isEmpty())
            {
                nodes.forEach((node) ->
                {
                    elements.add((Element) node);
                });
            }

            return elements;

        } catch (DocumentException ex) {
            LOGGER.error("Exception reading config.xml'");
            LOGGER.error(ex);
        }

        return null;
    }

     /**
     * Finds all the elements defined by the series of keys.
     *
     * @param parent: parent from where to start the search.
     * @param name: name attribute of the parent.
     * @param keys list of keys to find the elements.
     * @return all the elements defined by the series of keys.
     */
    public List<Element> getValuesAsList(String parent, String name, String... keys)
    {
        String key = parent + "[@name='" + name + "']";

        String[] newKeys = new String[keys.length + 1];
        newKeys[0] = key;
        for (int i = 0; i < keys.length; ++i)
        {
            newKeys[i + 1] = keys[i];
        }

        return getValuesAsList(newKeys);
    }

    /**
     * Finds the last element defined by the series of keys.
     *
     * @param keys list of keys to find the element.
     * @return the value pointed by keys as a string.
     */
    public String getValueAsString(String... keys)
    {
        StringBuilder xpath = new StringBuilder();
        for (String key : keys)
        {
            xpath.append((xpath.length() == 0) ? "//" : "/");
            xpath.append(key);
        }

        try
        {
            SAXReader reader = new SAXReader();
            Document document = reader.read(configurationFile);

            return document.selectSingleNode(xpath.toString()).getStringValue();

        } catch (DocumentException ex) {
            LOGGER.error("Exception reading config.xml'");
            LOGGER.error(ex);
        }

        return null;
    }

    /**
     * Finds the last element defined by the series of keys. It is also
     * used the name attribute of the parent.
     *
     * @param parent: parent from where to start the search.
     * @param name: name attribute of the parent.
     * @param keys: array of keys that will drive the search.
     * @return the value pointed by keys as a string.
     */
    public String getValueAsString(String parent, String name, String[] keys)
    {
        String key = parent + "[@name='" + name + "']";

        String[] newKeys = new String[keys.length + 1];
        newKeys[0] = key;
        for (int i = 0; i < keys.length; ++i)
        {
            newKeys[i + 1] = keys[i];
        }

        return getValueAsString(newKeys);
    }
}
