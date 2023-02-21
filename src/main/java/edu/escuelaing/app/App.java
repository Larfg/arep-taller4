package edu.escuelaing.app;

import java.io.IOException;

/**
 * Clase main de nuestro servidor de solicitudes HTTP.
 * @author Luis Felipe Giraldo Rodriguez
 * @version 4.0
 */
public class App {
    static HttpServer httpServer = HttpServer.getInstance();
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        httpServer.run();
    }
}
