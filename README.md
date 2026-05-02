GESTIÓN DE RESTAURANTE – PROYECTO

Este proyecto consiste en una aplicación de escritorio desarrollada en
Java que permite gestionar un restaurante. Forma parte del Proyecto
Intermodular de 1º DAM.

1.  FUNCIONALIDAD PRINCIPAL

La aplicación permite gestionar tres elementos clave:

-   Clientes: crear, buscar, modificar y eliminar
-   Mesas: consultar disponibilidad y cambiar estado
-   Carta (menú): ver platos, añadir nuevos y gestionar disponibilidad

Toda la información se guarda en una base de datos PostgreSQL conectada
mediante JDBC.

2.  TECNOLOGÍAS UTILIZADAS

-   Java 17 o superior
-   PostgreSQL
-   JDBC
-   SQL
-   XML y XSD
-   Git y GitHub

3.  ESTRUCTURA DEL PROYECTO

    RestauranteApp/
    ├── sql/
    │   ├── 01_crear_tablas.sql       # Creación del esquema de BD
    │   ├── 02_insertar_datos.sql     # Datos de ejemplo
    │   └── 03_consultas.sql          # Consultas útiles del proyecto
    ├── src/main/java/com/restaurante/
    │   ├── Main.java                 # Punto de entrada
    │   ├── db/
    │   │   └── ConexionDB.java       # Conexión JDBC (Singleton)
    │   ├── model/
    │   │   ├── Cliente.java
    │   │   ├── Mesa.java
    │   │   └── Plato.java
    │   ├── service/
    │   │   ├── ClienteService.java   # CRUD clientes
    │   │   ├── MesaService.java      # Operaciones mesas
    │   │   └── PlatoService.java     # Gestión carta
    │   ├── controller/
    │   │   ├── ClienteController.java
    │   │   └── MesaPlatoController.java
    │   └── util/
    │       └── EntradaUsuario.java   # Lectura de consola
    ├── xml/
    │   ├── datos.xml                 # Exportación de la carta en XML
    │   └── esquema.xsd               # Esquema de validación XSD
    ├── docs/
    │   ├── sistemas/                 # Informe técnico de entorno
    │   └── empleabilidad/            # Portfolio profesional
    └── README.md


4.  INSTALACIÓN Y EJECUCIÓN

Requisitos: - Java instalado - PostgreSQL funcionando - Driver JDBC

Pasos: 1. Crear la base de datos 2. Ejecutar scripts SQL 3. Configurar
conexión en ConexionDB.java 4. Compilar y ejecutar

5.  ARQUITECTURA

Modelo MVC simplificado: - Controller: interacción con usuario -
Service: lógica de negocio - Model: datos - ConexionDB: conexión

Principios: - Encapsulación - Separación de responsabilidades -
Reutilización - Seguridad SQL

6.  XML

-   datos.xml: exportación
-   esquema.xsd: validación

7.  MÓDULOS

-   Bases de Datos
-   Programación
-   MPO
-   Lenguajes de marcas
-   Entornos de desarrollo
-   Sistemas informáticos
-   Empleabilidad

