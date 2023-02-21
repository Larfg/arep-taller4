package edu.escuelaing.app.mySpringBoot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Etiqueta que nos permite conocer si un metodo es de tipo requestmaping y almacena su path en value
 * @author Luis Felipe Giraldo Rodriguez
 * @version 4.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {
    String value();
}
