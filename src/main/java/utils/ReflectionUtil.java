package utils;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class ReflectionUtil {

    public static final Logger LOGGER = LogManager.getLogger();

    private ReflectionUtil() {
    }

    public static Object getFieldByJsonPropertyName(Object obj, String fieldName, Boolean ignoreCase) {
        List<Field> fields = new ArrayList<>();
        Collections.addAll(fields, obj.getClass().getDeclaredFields());
        Collections.addAll(fields, obj.getClass().getSuperclass().getDeclaredFields());
        for (Field field : fields) {
            if (isEquals(fieldName, field, ignoreCase)) {
                return getValueOfField(field, obj);
            }
        }
        return null;
    }

    public static void setFieldByJsonPropertyName(Object obj, String fieldName, Object value) {
        List<Field> fields = new ArrayList<>();
        Collections.addAll(fields, obj.getClass().getDeclaredFields());
        Collections.addAll(fields, obj.getClass().getSuperclass().getDeclaredFields());
        try {
            for (Field field : fields) {
                if (isEquals(fieldName, field)) {
                    callSetter(obj, value, field);
                    break;
                }
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.error(e);
        }
    }

    public static void callSetter(Object obj, Object value, Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String setterName = "set" + StringUtils.capitalize(field.getName());
        obj.getClass().getMethod(setterName, field.getType()).invoke(obj, value);
    }

    public static Object callGetter(Object obj, Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String getterName = "get" + StringUtils.capitalize(field.getName());
        return obj.getClass().getMethod(getterName).invoke(obj);
    }

    public static Object getFieldByJsonPropertyName(Object obj, String fieldName) {
        return getFieldByJsonPropertyName(obj, fieldName, false);
    }



    public static Object getValueOfField(Field field, Object object) {
        try {
            return callGetter(object, field);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.error(e);
        }
        return null;
    }


    private static boolean isEquals(String fieldName, Field field, Boolean ignoreCase) {
        if (Boolean.TRUE.equals(ignoreCase)) {
            return isEqualsIgnoreCase(fieldName, field);
        }
        return isEquals(fieldName, field);
    }

    private static boolean isEquals(String fieldName, Field field) {
        return field.getAnnotation(JsonProperty.class).value().equalsIgnoreCase(fieldName) ||
                field.getName().equals(fieldName);
    }

    private static boolean isEqualsIgnoreCase(String fieldName, Field field) {
        return field.getAnnotation(JsonProperty.class).value().equalsIgnoreCase(fieldName) ||
                field.getName().equalsIgnoreCase(fieldName);
    }

}
