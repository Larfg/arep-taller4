# Taller 4 
## Diseño y descripción del diseño:   

Para este taller modificamos el servidor del taller 3, para poder utilizar etiquetas, y cargar facilmente métodos y clases que nosotros querramos, además creamos un sistema que nos lee todos los archivos java dentro del servidor y nos permite identificar los componentes para cargarlos cuando se inicié el servidor

### Extensibilidad   

Para asegurar la extensibilidad se utilizó un sistema en donde facilmente podemos añadir nuevos componentes y mapear sus comportamientos unicamente con el manejo de etiquetas.

### Uso de patrones   

Utilizamos un patrón **SINGLETON** para asegurarnos de que solo exista una instancia del servidor y del blog servicie, y usamos un patrón **ABSTRACT FACTORY** a la hora de manejar nuestros servicios.   

### Modularidad   

Al tener un sistema de etiquetas que nos permite identificar fácilmente las clases que queremos cargar, y ademas los metodos que queremos utilizar, podemos facilmente cambiar, eliminar o añadir nuestras clases y el servidor seguira funcionando. 

## Prerrequisitos:   

Debe tener java, Maven y un navegador web, preferiblemente Firefox.  


## Instalación:   

Clone el repositorio.  

## Realizando pruebas:   

Para probar el funcionamiento del servidor podemos acceder directamente al localhost:35000 desde nuestro navegador, principalmente al path /apps/, en donde se ven los distintos servicios a los cuales podemos acceder desde el navegador cambiando la URI, igualmente estos servicios son, página principal (http://localhost:35000/apps/), página de búsqueda de archivos estáticos (http://localhost:35000/apps/search) y por último página de blog (http://localhost:35000/apps/blog). Estos son los elementos que nos retornan los metodos de los pojos que cargamos al iniciar la aplicación.

## Despliegue:   

- Para poder iniciar el servidor se puede iniciar por Maven, con el siguiente comando "mvn compile exec:java" o puede iniciarlo directamente con la clase ejecutiva del proyecto.   
- Para probar los servicios pruebe el siguiente enlace después de ejecutar el servidor (http://localhost:35000/apps/). 
## Construido con:   

[Maven](https://maven.apache.org/) - Dependency Management     

## Autores:   

- Luis Felipe Andres Giraldo Rodriguez 
