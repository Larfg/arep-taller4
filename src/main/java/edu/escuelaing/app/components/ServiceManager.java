package edu.escuelaing.app.components;

import edu.escuelaing.app.mySpringBoot.Component;
import edu.escuelaing.app.mySpringBoot.RequestMapping;
import edu.escuelaing.app.services.*;

/**
 * Componente que nos permite acceder a 3 servicios web simples
 * @author Luis Felipe Giraldo Rodriguez
 * @version 4.0
 */
@Component
public class ServiceManager {
    @RequestMapping("/apps/")
    public static Service wService() {
        return new WebService();
    }

    @RequestMapping("/apps/search")
    public static Service sService() {
        return new SearchService();
    }

    @RequestMapping("/apps/blog")
    public static Service bService() {
        Service service = BlogService.getInstance();
        return service;
    }
}
