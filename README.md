# Distribucion-Golosa-2020

##### Interfaz:

Para la interfaz principal de la aplicación se usó dos tablas, una para cargar y visualizar los datos de los Clientes y otra para los puntos en el mapa (posibles centros de distribución), se cuenta con un botón para agregar un cliente a la tabla con su latitud y longitud, y un botón para agregar un punto con su latitud y longitud. También se cuenta con un botón para salir de la aplicación, un botón para eliminar una persona o un punto de la tabla, y luego se cuenta con un botón que busca los centros para abrir. También se cuenta con un spinner para elegir la cantidad de centros a abrir.
Luego se cuenta con la interfaz secundaria en la que se muestran los resultados, estos se muestran en una tabla junto con su latitud y longitud, también se muestra el costo total de la solución. Luego se cuenta con un botón para volver atrás (este cierra la ventana del resultado y vuelve a la anterior) y con un botón para ver el resultado en el mapa. Allí se podrá ver todos los clientes y los centros de distribución de la solución.

##### Implementacion: 

Para el funcionamiento de la aplicación, cuando se ingresaron los datos en ambas tablas, se presiona el botón de búsqueda de centros y lo primero que hace es mandar la información de las tablas a archivos de texto. Luego se ejecuta el algoritmo, el cual lee los datos de los archivos de texto y los guarda en listas (clientes y puntos), luego se usa un ciclo for para recorrer ambas listas y para cada cliente se realiza la sumatoria de distancias a cada punto y cada resultado se guarda en un arreglo de enteros, esto se hace para cada punto (todos los clientes al primer punto y luego segundo, etc). Luego cada punto va a tener un entero que es la sumatoria de todas las distancias de cada cliente a ese punto, luego el arreglo de enteros se ordena. Luego se usa un ciclo for y se recorren los puntos nuevamente y se van agregando a la lista distribucionResultado en el mismo orden que se encuentran los enteros en el arreglo de enteros. Luego se escribe el resultado en un archivo de texto llamado Resultado, los que se guardan en el archivo son los primeros k puntos, estos terminan siendo los centros a abrir y son los que tienen el menor costo.

Finalmente se leen los resultados y se añaden a una tabla junto con su latitud y longitud, y el costo total de la solución que seria la suma de las distancias totales de los centros elegidos.

![Captura de pantalla 2022-04-15 162915](https://user-images.githubusercontent.com/82238605/163625872-59f6db93-0c8e-4ea4-aeb0-09f7cb79c891.png)
