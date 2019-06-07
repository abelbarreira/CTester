package com.lsc.ctesterfx.constants;

/**
 * Class containing different colors.
 *
 * @author dma@logossmartcard.com
 */
public class Colors
{
    public enum Color
    {
        ACCENT("#53DBB6"),
        RED("#FF0000"),
        YELLOW("#FFCC00"),
        BLUE("#00FFEA"),
        DARK_GRAY("#888888"),
        GRAY("#CCCCCC");

        public final String value;

        private Color(String value)
        {
            this.value = value;
        }
    }

    /**
     * Method that returns the hex value of a color as a string.
     *
     * @param color: field of the Color enum.
     * @return hex value of a color as a string.
     */
    public static String createAsString(Color color)
    {
        return color.value;
    }
}
