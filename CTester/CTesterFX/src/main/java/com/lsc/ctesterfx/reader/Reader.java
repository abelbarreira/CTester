package com.lsc.ctesterfx.reader;

import com.lsc.ctesterfx.iso7816.ApduCommand;
import com.lsc.ctesterfx.iso7816.ApduResponse;
import javax.smartcardio.CardException;

/**
 * Abstract class that every reader should extend. It will contain
 * the public API plus some methods to be used from within the application.
 *
 * @author dma@logossmartcard.com
 */
public abstract class Reader implements IReader
{
    @Override
    public abstract byte[] reset() throws CardException;

    @Override
    public abstract ApduResponse transmit(ApduCommand command) throws CardException;

    /**
     * Creates a new connection with the card.
     */
    public abstract void connect();

    /**
     * Releases the connection with the card.
     *
     * @throws CardException if the connection has already been disposed.
     */
    public abstract void release() throws CardException;

    /**
     * Returns the name of the reader.
     *
     * @return name of the reader.
     */
    public abstract String getName();
}
