itRegistro
==========

Sistema cliente-servidor que permite registrar la entrada y salida de personal, en una o mas lugares, organizaciones. permite la creacion de nuevos usuarios con diversos tipos de permisos. permite la gestion y consulta de las asistencias del personal, ademas depoder exportarla a planillas de calculo.

Tecnologias Intervinientes
--------------------------

  - Java
  - RMI
  - Datanucleus
  - persistencia MYSQL o POSTGRESQL
  
Contenido Del Paquete
---------------------

  - Codigo
  - Ejemplo de Archivos de configuración para que funcione de manera local
  - Librerias Necesarias

Uso
---
utilizando Eclipse IDE

  1. Crear el proyecto que representa el comun (atraves del cual estableceremos la conexion entre Cliente y servidor)
  2. agregar el codigo pertinene que se encuentra en la carpeta comun.
  
  3.crear el proyecto de Cliente al cual agregamos el proyecto comun dentro del build path.
  4. agregamos las librerias correspondientes a dicho proyecto
  5. agregamos el codigo y los archivos de configuracion que se encuentran en la carpeta Cliente.
  
  6. Idem proceso al Cliente dentro del servidor (crear el proyecto, agregar el comun y librerias, y agregar el codigo y archivos de configuracion)
  
  7- revisar todas las referencias entre los proyectos
