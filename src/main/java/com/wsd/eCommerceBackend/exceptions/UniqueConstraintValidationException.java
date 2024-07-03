package com.wsd.eCommerceBackend.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class UniqueConstraintValidationException extends RuntimeException {

    public UniqueConstraintValidationException(Class clazz, String... searchParamsMap) {
        super(UniqueConstraintValidationException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
    }

    public UniqueConstraintValidationException(Class clazz, Map<String, String> searchParams) {
        super(UniqueConstraintValidationException.generateMessage(clazz.getSimpleName(), searchParams));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return entity +
                " has duplicate values as " +
                searchParams;
    }

    private static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }

}
