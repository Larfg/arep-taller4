package edu.escuelaing.app.components;

import edu.escuelaing.app.mySpringBoot.Component;
import edu.escuelaing.app.mySpringBoot.RequestMapping;
import edu.escuelaing.app.services.FileReader;
import edu.escuelaing.app.services.Service;

/**
 * Componente que nos permite acceder al servicio de lectura de archivos
 * @author Luis Felipe Giraldo Rodriguez
 * @version 4.0
 */
@Component
public class FileManager {
    @RequestMapping("/file/")
    public static Service files(String path){
        return  new FileReader(path);
    }
}
