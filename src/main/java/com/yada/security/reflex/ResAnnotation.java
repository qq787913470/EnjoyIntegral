package com.yada.security.reflex;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE}) 
@Retention(RetentionPolicy.RUNTIME) 
@Documented 
@Inherited
public @interface ResAnnotation {
     enum typeEnum{none,menu,url,all};
     typeEnum resType() default typeEnum.all; 
     String chName() default "";
}