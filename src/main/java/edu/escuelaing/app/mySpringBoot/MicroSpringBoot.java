package edu.escuelaing.app.mySpringBoot;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * Clase que nos carga los componentes que detecte en el directorio de la aplicacion
 * @author Luis Felipe Giraldo Rodriguez
 * @version 4.0
 */
public class MicroSpringBoot {

    ArrayList<String> clases;

    /**
     * Metodo recursivo que busca los archivos java con la etiqueta component y los almacena
     * @param javaFiles Array en donde vamos a almacenar el path de los archivos java component
     * @param path path en donde buscamos los archivos
     * @return Lista con todos los archivos java con la etiqueta component
     */
    public ArrayList<String> getComponents(ArrayList javaFiles, String path) {
        Path filePath = Paths.get(path);
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(filePath)) {
            for (Path files : directoryStream) {
                if (!Files.isHidden(files)) {
                    if (Files.isDirectory(files)) {
                        getComponents(javaFiles, files.toString());
                    }
                    else if (Files.isRegularFile(files)) {
                        if(files.toString().split("\\.")[2].equals("java")){

                            String className = files.toString().split("java/")[1].replace("/", ".").replace(".java", "");
                            try {
                                if(Class.forName(className).isAnnotationPresent(Component.class)){
                                    javaFiles.add(className);
                                }
                            } catch (ClassNotFoundException e) {
                            }
                        }
                    }
                }
            }
        } catch (IOException ex) {
        }
        return javaFiles;
    }
}
