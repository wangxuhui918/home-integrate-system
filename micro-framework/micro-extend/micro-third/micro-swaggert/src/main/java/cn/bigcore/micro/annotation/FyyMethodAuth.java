
package cn.bigcore.micro.annotation;

import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FyyMethodAuth {
    String code() default "";

    String name() default "";
}
