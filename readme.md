EuskalMap, una aplicación de Víctor González Fuente para visualizar el mapa de ocupación de la Euskal Encounter. 
Esta aplicación fue creada para la actividad llamada Fast Android Coding llevada a cabo en la Euskal Encounter; dicha actividad consistía en crear un mapa de la ocupación de la party que fuera interactivo, con la libertad de añadir las funcionalidades que el desarrollador deseara.
Entregado el día 25 de julio.
Nombre: Víctor González Fuente
Nick: Nabe

Este proyecto tiene las siguientes dependencias, que son librerías de código libre, encontradas en Github, que hacen uso de la licencia MIT:

compile 'com.android.support:design:22.2.0'         //Android design support library
compile 'com.jakewharton:butterknife:6.1.0'         //Butterknife, View injector
compile 'com.android.support:recyclerview-v7:+'     //Recyclerview, not included in support design
compile 'com.github.jhy:jsoup:jsoup-1.8.2'          //HTML parser and more -> https://github.com/jhy/jsoup
compile 'com.github.HackPlan:AndroidCharts:195635f8ee' //Charts -> https://github.com/HackPlan/AndroidCharts


La aplicación tiene las siguientes funcionalidades:
En la pantalla principal se muestra un mapa de ocupación, con el código del puesto y un color que indica el estado de la reserva del asistente que ocupa dicho puesto. Al pulsar sobre cualquiera de ellos, se mostrará el nombre(si está disponible) del asistente, y el nombre detallado del estado de reserva.

*Cuando se habla de mostrar usuarios, se refiere a usuarios que se han proporcionado, si están ocultos dirá que el usuario no está disponible o no existe.

En el menú de la aplicación está el acceso a las demás:
1-Recargar el mapa de ocupación, por si hubiera habido algun problema previamente, o simplemente se quiere actualizar
2-Mostrar gráficos de ocupación: esto mostrará un gráfico circular del porcentaje de cada estado de reserva, y un gráfico de barras con la cantidad de cada estado.
3-Buscar usuarios por nombre: se introduce un nombre completo o parte, y muestra el/los usuarios que contengan dicho texto.
4-Buscar usuarios de un grupo: se requerirá un nombre de grupo y mostrará los usuarios que pertenezcan a dicho grupo.
5-Inspeccionar una plaza: Es lo mismo que pulsar un cuadrado en el mapa, solo que se puede introducir manualmente el puesto.