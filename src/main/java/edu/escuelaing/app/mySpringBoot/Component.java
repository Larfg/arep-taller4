package edu.escuelaing.app.mySpringBoot;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Etiqueta que nos permite identificar si un objeto es un componente
 * @author Luis Felipe Giraldo Rodriguez
 * @version 4.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    
}
