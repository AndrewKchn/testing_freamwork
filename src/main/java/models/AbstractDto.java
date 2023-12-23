package models;


import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import models.anotations.SortBy;
import object_mappper.DtoConvert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDto implements Comparable<Object> {
    protected static final Logger LOGGER = LogManager.getLogger();

    public AbstractDto() {
    }

    @JsonIgnore
    public Map<Object, Object> unknownFields = new HashMap<>();

    @JsonGetter
    public Map<Object, Object> any() {
        return unknownFields;
    }

    @JsonAnySetter
    public void set(Object name, Object value) {
        unknownFields.put(name, value);
    }

    public Object getFieldValueByJsonPropertyName(String name) {
        return ReflectionUtil.getFieldByJsonPropertyName(this, name);
    }

    public void setFieldValueByJsonPropertyName(String name, Object value) {
        ReflectionUtil.setFieldByJsonPropertyName(this, name, value);
    }

    public boolean hasUnknownProperties() {
        return !unknownFields.isEmpty();
    }

    public static <T> T fromMap(Map<String, Object> obj, Class<T> tClass) {
        return DtoConvert.mapToDto(tClass, obj, false);
    }

    @Override
    public int compareTo(Object obj) {
        String fieldName = this.getClass().getAnnotation(SortBy.class).value();
        boolean found = true;
        Object valueThis = null;
        Object valueOther = null;
        if (obj instanceof AbstractDto) {
            try {
                Field fieldObj = this.getClass().getField(fieldName);
                valueThis = fieldObj.get(this);
                valueOther = fieldObj.get(obj);
            } catch (Exception e) {
                found = false;
            }
        }
        if (found && valueThis instanceof Comparable && valueOther instanceof Comparable) {
            return ((Comparable) valueThis).compareTo(valueOther);
        }
        return 0;
    }
}
