package edu.escuelaing.app;

import java.net.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.escuelaing.app.mySpark.Request;
import edu.escuelaing.app.mySpringBoot.MicroSpringBoot;
import edu.escuelaing.app.mySpringBoot.RequestMapping;
import edu.escuelaing.app.services.NotFoundService;
import edu.escuelaing.app.services.Service;

/**
 * Servidor ws que nos permite enviar y recibir elementos por ws
 * 
 * @author Luis Felipe Giraldo Rodriguez
 * @version 4.0
 */
public final class HttpServer {
    private static HttpServer instance;

    /**
     * Retorna la instancia del servidor, en caso de que no exista lo crea
     * 
     * @return instancia del servidor
     */
    public static HttpServer getInstance() {
        if (instance == null) {
            instance = new HttpServer();
        }
        return instance;
    }

    /**
     * Metodo principal que nos inicia un servidor socket http, que carga pojos con @component
     * y mapea los metodos requestMapping para retornar solicitudes get
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void run() throws IOException, ClassNotFoundException {
        Map<String, Method> methods = new HashMap<>();
        MicroSpringBoot msb = new MicroSpringBoot();
        ArrayList<String> componentNames = msb.getComponents(new ArrayList<>(), ".");
        for (String componentName : componentNames) {
            /**
             * Cargar la clase con forname
             * Extraigo los metodos request mapping
             */
            Class c = Class.forName(componentName);
            for (Method method : c.getMethods()) {
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    /**
                     * Extraigo el valor del path
                     * extraigo una instancia del metodo
                     * poner el metodo con llave path
                     */
                    String path = method.getAnnotationsByType(RequestMapping.class)[0].value();
                    methods.put(path, method);
                }
            }
        }
        System.out.println("Servidor funcionando ...");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);

        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running) {

            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;

            // Proceso de lectura de los datos header y body de una solicitud
            String headerLine = "";
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.length() == 0) {
                    break;
                }
                headerLine += inputLine + "\r\n";
            }
            StringBuilder body = new StringBuilder();
            while (in.ready()) {
                body.append((char) in.read());
            }

            // Proceso de procesamiento de las solicitudes get a los pojo component
            Request request = new Request(headerLine, body.toString());
            Service service = new NotFoundService();
            try {
                if (request.getUri().contains("file")){
                    service = (Service) methods.get("/file/").invoke(null, request.getUri()); 
                }
                else if (methods.containsKey(request.getUri())) {
                    service = (Service) methods.get(request.getUri()).invoke(null);
                }

            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
            outputLine = service.getHeader() + service.getBody();
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();

        }
        serverSocket.close();
        System.out.println("Servidor apagado.");
    }
}