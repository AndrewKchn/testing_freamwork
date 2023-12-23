package models.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SortBy {

    /**
     * Defines a name of the fields that be used to sort collection.
     * @return String value? the field name
     */
    String value() default "";
}
