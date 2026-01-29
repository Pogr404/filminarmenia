package com.registration.util;

import java.text.DecimalFormat;

/**
 * Defines common constants.
 */
public class Constants {
    public static final String UTF8 = "UTF-8";

    public static final String NUMBER_FORMAT = "#,###,###";
    public static final DecimalFormat DECIMAL_FORMAT;

    static {
        DECIMAL_FORMAT = new DecimalFormat(Constants.NUMBER_FORMAT);
    }
}
