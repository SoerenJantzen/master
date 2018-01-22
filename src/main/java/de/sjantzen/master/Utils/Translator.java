package de.sjantzen.master.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by sJantzen on 29.12.2017.
 */
public class Translator {

    private static final Logger LOG = LoggerFactory.getLogger(Translator.class);

    final static DateTimeFormatter formatterShort = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    final static DateTimeFormatter formatterMiddle = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm");
    final static DateTimeFormatter formatterLong = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss");

    public static String getDatetimeFormatted(final Date dateToFormat, final DateFormat dateFormat) {

        if(dateToFormat == null) {
            LOG.info("The given date was null.");
            return "";
        }
        LocalDateTime dateToParse = dateToFormat.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        switch (dateFormat) {
            case SHORT:
                return dateToParse.format(formatterShort);
            case MIDDLE:
                return dateToParse.format(formatterMiddle);
            case LONG:
            default:
                return dateToParse.format(formatterLong);
        }
    }
}
