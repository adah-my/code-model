package xiaoce.primary.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
    String value() default "default";
}