package com.lsc.ctesterlib.iso7816;

import com.lsc.ctesterlib.utils.Formatter;

/**
 * Class that represents a response APDU.
 *
 * @author dma@logossmartcard.com
 */
public class ApduResponse
{
    private byte[] sw;
    private byte[] data;

    private ApduResponse()
    {
        sw = new byte[2];
    }

    /**
     * Builder class.
     */
    public static class Builder
    {
        byte[] sw;
        byte[] data;

        public Builder()
        {
            sw = new byte[2];
        }

        public Builder withSw1(byte sw1)
        {
            sw[0] = sw1;

            return this;
        }

        public Builder withSw2(byte sw2)
        {
            sw[1] = sw2;

            return this;
        }

        public Builder withData(byte[] data)
        {
            this.data = data;

            return this;
        }

        public ApduResponse build()
        {
            ApduResponse apdu = new ApduResponse();

            apdu.sw   = this.sw;
            apdu.data = this.data;

            return apdu;
        }
    }

    /**
     * Returns the status word.
     *
     * @return status word.
     */
    public byte[] getSw() { return sw; }

    /**
     * Returns the data, null if there's no data.
     *
     * @return body of the response. Null if there's none.
     */
    public byte[] getData() { return data; }

    @Override
    public String toString()
    {
        String swStr = Formatter.fromByteArrayToString(this.sw);
        String dataStr = "";

        if (this.data != null && this.data.length > 0)
        {
            dataStr = Formatter.fromByteArrayToString(this.data) + " ";
        }

        return dataStr + swStr;
    }
}
