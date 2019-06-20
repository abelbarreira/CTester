package com.lsc.ctesterfx.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;

/**
 * Class that manages the readers connected to the computer.
 *
 * @author dma@logossmartard.com
 */
public class ReaderController
{
    private static Reader currentReader;

    /**
     * Returns a list containing all the readers' names.
     *
     * @return list with all the readers' names.
     */
    public static List<String> list()
    {
        List<String> readers = new ArrayList<>();

        try
        {
            for (CardTerminal cardTerminal : TerminalFactory.getDefault().terminals().list())
            {
                readers.add(cardTerminal.getName());
            }

        } catch (CardException ex) {
            Logger.getLogger(ReaderController.class.getName()).log(Level.SEVERE, null, ex);

            return new ArrayList<>();
        }

        return readers;
    }

    /**
     * Selects a specific reader to be used from now on.
     *
     * @param index index of the reader to be selected.
     */
    public static void select(int index)
    {
        // Release the previous one
        if (currentReader != null)
        {
            currentReader.release();
        }

        try
        {
            currentReader = new PCSCReader.Builder()
                    .fromCardTerminal(TerminalFactory.getDefault().terminals().list().get(index))
                    .build();

        } catch (CardException ex) {
            Logger.getLogger(ReaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns the selected reader.
     *
     * @return the selected reader. Null if there is no reader selected.
     */
    public static Reader getSelected()
    {
        return currentReader;
    }
}
