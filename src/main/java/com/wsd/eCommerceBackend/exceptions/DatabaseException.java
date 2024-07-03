package com.wsd.eCommerceBackend.exceptions;


import com.wsd.eCommerceBackend.utils.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
public class DatabaseException extends RuntimeException {
    public DatabaseException(Class clazz) {
        super(DatabaseException.generateDatabaseExceptionMessage(clazz.getSimpleName()));
    }

    public DatabaseException(String errMessage) {
        super(DatabaseException.generateMessage(errMessage));
    }

    public DatabaseException(Exception e) {
        super(DatabaseException.generateMessage(e));
    }

    public static String generateMessage(Exception e) {
        String errMessage = "Database Error";
        if(e.getCause() != null && e.getCause().getCause() instanceof Exception) {
            Exception ex = (Exception) e.getCause().getCause();
            errMessage = Strings.extractError(ex.getMessage());
        } else {
            errMessage = e.getMessage();
        }
        log.error( errMessage);
        return errMessage;
    }

    public static String generateMessage(String entity, Map<String, String> searchParams) {

        String error = StringUtils.capitalize(entity) +
                " was not found for parameters " +
                searchParams;

        log.error( error);
        return error;
    }

    public static String generateMessage(String errMessage) {
        log.error( errMessage);
        return errMessage;
    }

    public static String generateDatabaseExceptionMessage(String entity) {

        String error = "Database error for: " + entity;

        log.error( error);
        return error;
    }

    public static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries) {

        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");

        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }

}
