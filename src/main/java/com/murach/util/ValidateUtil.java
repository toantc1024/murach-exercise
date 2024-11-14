package com.murach.util;

import java.lang.reflect.Field;
import java.util.List;

public class ValidateUtil {

    public static <T> boolean validatePropertiesAreNotNullOrEmpty(T instance, List<String> propertyNames) {
        if (instance == null || propertyNames == null || propertyNames.isEmpty()) {
            throw new IllegalArgumentException("Instance and propertyNames must not be null or empty.");
        }

        try {
            for (String propertyName : propertyNames) {
                Field field = instance.getClass().getDeclaredField(propertyName);
                field.setAccessible(true); // Make private fields accessible

                Object value = field.get(instance);

                // Check if value is null
                if (value == null) {
                    System.out.println(propertyName + " is null.");
                    return false;
                }

                // Check if value is empty (for Strings or Collections)
                if (value instanceof String && ((String) value).isEmpty()) {
                    System.out.println(propertyName + " is an empty String.");
                    return false;
                }
                if (value instanceof List && ((List<?>) value).isEmpty()) {
                    System.out.println(propertyName + " is an empty List.");
                    return false;
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
