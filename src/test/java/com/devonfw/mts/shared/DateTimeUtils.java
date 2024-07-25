package com.devonfw.mts.shared;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeUtils {
    public static String DATE_TIME_FORMAT_UI = "M/d/yyyy, H:m a";

    public static Instant parseUiDateTime(String dateFromTable) {
        DateTimeFormatter readingFormat = DateTimeFormatter.ofPattern(DateTimeUtils.DATE_TIME_FORMAT_UI)
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault());

        return readingFormat.parse(dateFromTable, Instant::from);
    }
}
