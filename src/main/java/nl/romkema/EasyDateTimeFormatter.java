package nl.romkema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class EasyDateTimeFormatter {
    public static LocalDateTime format(String input) {
        LocalDateTime output = null;

        // Check against predefined patterns
        for (DateTimeFormatter formatter : EasyDateTimeFormatter.getAvailableDateTimeFormatters()) {
            try {
                output = LocalDateTime.parse(input, formatter);
                System.out.println("Pattern matched: " + getPattern(formatter));
                break;
            } catch (Exception e) {
                // Pattern didn't match, try the next one
            }
        }
        if (output == null) {
            throw new RuntimeException("String could not be matched to a given pattern");
        } else
            return output;
    }

    private static List<DateTimeFormatter> getAvailableDateTimeFormatters() {
        return List.of(
                // Predefined DateTimeFormatters
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ISO_OFFSET_DATE,
                DateTimeFormatter.ISO_DATE,
                DateTimeFormatter.ISO_LOCAL_TIME,
                DateTimeFormatter.ISO_OFFSET_TIME,
                DateTimeFormatter.ISO_TIME,
                DateTimeFormatter.ISO_LOCAL_DATE_TIME,
                DateTimeFormatter.ISO_OFFSET_DATE_TIME,
                DateTimeFormatter.ISO_ZONED_DATE_TIME,
                DateTimeFormatter.ISO_DATE_TIME,
                DateTimeFormatter.ISO_ORDINAL_DATE,
                DateTimeFormatter.ISO_WEEK_DATE,
                DateTimeFormatter.ISO_INSTANT,
                DateTimeFormatter.BASIC_ISO_DATE,
                DateTimeFormatter.RFC_1123_DATE_TIME,
                // Custom DateTimeFormatters
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S Z"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S z")
        );
    }

    private static String getPattern(DateTimeFormatter formatter) {
        if (DateTimeFormatter.ISO_LOCAL_DATE.equals(formatter)) {
            return "yyyy-MM-dd'"; // 2011-12-03
        } else if (DateTimeFormatter.ISO_OFFSET_DATE.equals(formatter)) {
            return "yyyy-MM-ddX"; // 2011-12-03+01:00
        } else if (DateTimeFormatter.ISO_DATE.equals(formatter)) {
            return "'yyyy-MM-dd' or 'yyyy-MM-ddX'"; // '2011-12-03' or '2011-12-03+01:00'
        } else if (DateTimeFormatter.ISO_LOCAL_TIME.equals(formatter)) {
            return "'HH:mm' or 'HH:mm:ss'"; // 10:15' or '10:15:30
        } else if (DateTimeFormatter.ISO_OFFSET_TIME.equals(formatter)) {
            return "'HH:mmX' or 'HH:mm:ssX'"; // '10:15+01:00' or '10:15:30+01:00'
        } else if (DateTimeFormatter.ISO_TIME.equals(formatter)) {
            return "'HH:mm' or 'HH:mm:ss' or 'HH:mm:ssX'"; // '10:15', '10:15:30' or '10:15:30+01:00'
        } else if (DateTimeFormatter.ISO_LOCAL_DATE_TIME.equals(formatter)) {
            return "yyyy-MM-ddTHH:mm:ss"; // '2011-12-03T10:15:30'
        } else if (DateTimeFormatter.ISO_OFFSET_DATE_TIME.equals(formatter)) {
            return "yyyy-MM-ddTHH:mm:ssX"; // 2011-12-03T10:15:30+01:00
        } else if (DateTimeFormatter.ISO_ZONED_DATE_TIME.equals(formatter)) {
            return "yyyy-MM-ddTHH:mm:ssX[V]"; // '2011-12-03T10:15:30+01:00[Europe/Paris]'
        } else if (DateTimeFormatter.ISO_DATE_TIME.equals(formatter)) {
            return "'yyyy-MM-ddTHH:mm:ss' or 'yyyy-MM-ddTHH:mm:ssX' or 'yyyy-MM-ddTHH:mm:ssX[V]'"; // '2011-12-03T10:15:30', '2011-12-03T10:15:30+01:00' or '2011-12-03T10:15:30+01:00[Europe/Paris]'
        } else if (DateTimeFormatter.ISO_ORDINAL_DATE.equals(formatter)) {
            return "yyyy-DDD"; // '2012-337'
        } else if (DateTimeFormatter.ISO_WEEK_DATE.equals(formatter)) {
            return "yyyy-\\Ww-e"; // '2012-W48-6'
        } else if (DateTimeFormatter.ISO_INSTANT.equals(formatter)) {
            return "yyyy-MM-ddTHH:mm:ss"; // '2011-12-03T10:15:30Z' TODO
        } else if (DateTimeFormatter.BASIC_ISO_DATE.equals(formatter)) {
            return "yyyyMMdd"; // '20111203'
        } else if (DateTimeFormatter.RFC_1123_DATE_TIME.equals(formatter)) {
            return "E, dd LLL yyyy HH:mm:ss O"; // 'Tue, 3 Jun 2008 11:05:30 GMT'
        } else if (DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").equals(formatter)) {
            return "yyyy-MM-dd HH:mm:ss";
        } else if (DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S Z").equals(formatter)) {
            return "yyyy-MM-dd HH:mm:ss.S Z";
        } else if (DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S z").equals(formatter)) {
            return "yyyy-MM-dd HH:mm:ss.S z";
        } else {
            throw new RuntimeException("Pattern not recognized!");
        }
    }
}
