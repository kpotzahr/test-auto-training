package com.devonfw.mts.shared;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class DateTimeUtils {
    public static String DATE_TIME_FORMAT_UI = "M/dd/yyyy, hh:mm a";


    public static String convertDateTimeFormat(String inputFormat, String outputFormat, String value) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputFormat);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat);

        TemporalAccessor date = inputFormatter.parse(value);
        return outputFormatter.format(date);
    }
}
