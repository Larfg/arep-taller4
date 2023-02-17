package edu.escuelaing.app.services;

import edu.escuelaing.app.RequestMapping;

public class ServiceManager {
    @RequestMapping("/")
    public static Service wService(){
        return new WebService();
    }
}
