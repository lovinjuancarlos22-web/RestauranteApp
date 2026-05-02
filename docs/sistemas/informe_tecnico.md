# Informe Técnico – Sistemas Informáticos (0483)
## Proyecto: Gestión de Restaurante

---

## 1. Tipo de sistema

La aplicación la he puesto a correr en un PC normal de escritorio, que sería el ordenador que tendría el encargado del restaurante en la barra o en la sala.

No necesita ningún servidor especial ni nada en la nube. Con un ordenador corriente es suficiente porque la base de datos también corre en el mismo equipo y no hay muchos usuarios a la vez.

---

## 2. Requisitos de hardware

| Componente | Mínimo | Recomendado |
|---|---|---|
| CPU | Intel Core i3 o similar | Intel Core i5 o superior |
| RAM | 4 GB | 8 GB |
| Almacenamiento | 10 GB libres | 50 GB SSD |
| Pantalla | Cualquiera | 1080p |

La aplicación no consume casi nada porque es solo consola de texto. PostgreSQL tampoco necesita mucho. Con 4 GB de RAM va sobrado.

---

## 3. Sistema operativo

He usado **Windows 11** porque es lo que tengo en mi ordenador y es lo que suele haber en la mayoría de sitios. Además Java y PostgreSQL funcionan perfectamente en Windows.

También valdría Ubuntu o cualquier Linux pero para un restaurante normal Windows es más fácil de manejar para alguien que no sabe de informática.

---

## 4. Cómo se instala

### 1. Instalar Java
- Descargar Java 17 o superior desde https://adoptium.net
- Ejecutar el instalador y seguir los pasos
- Comprobar que funciona abriendo el CMD y escribiendo `java -version`

### 2. Instalar PostgreSQL
- Descargar desde https://www.postgresql.org/download/windows/
- Ejecutar el instalador
- Poner una contraseña al usuario postgres (no olvidarla)
- El puerto por defecto es el 5432, dejarlo así

### 3. Crear la base de datos
- Abrir pgAdmin
- Crear una base de datos llamada `restaurante`
- Ejecutar el archivo `sql/01_crear_tablas.sql`
- Ejecutar el archivo `sql/02_insertar_datos.sql`

### 4. Configurar la contraseña en el código
- Abrir `ConexionDB.java`
- Cambiar la contraseña por la que pusiste al instalar PostgreSQL

### 5. Ejecutar la aplicación
- Abrir el proyecto en IntelliJ
- Añadir el driver JDBC de PostgreSQL en las dependencias
- Ejecutar `Main.java`

---

## 5. Usuarios y permisos

He pensado en dos tipos de usuario:

- **Encargado**: tiene acceso a todo, puede gestionar clientes, mesas y la carta
- **Camarero**: solo usaría la aplicación para consultar mesas y la carta

En cuanto a la base de datos, el usuario que usa la app es `postgres` directamente porque es un proyecto pequeño. En una situación real crearía un usuario específico solo con permisos de lectura y escritura, sin poder borrar tablas.

Las carpetas del proyecto están en:
```
C:\RestauranteApp\
```

---

## 6. Mantenimiento

Lo básico que habría que hacer para mantener esto funcionando:

- Hacer una copia de seguridad de la base de datos de vez en cuando. Se puede hacer con pgAdmin o con el comando `pg_dump`
- Actualizar Windows cuando toque
- Si la aplicación falla, lo primero es comprobar que el servicio de PostgreSQL está activo en el administrador de servicios de Windows

---

## 7. Capturas de funcionamiento

Las capturas están en la carpeta `docs/sistemas/capturas/`:

- `01_menu_principal.png` – menú principal de la aplicación
- `02_listado_clientes.png` – listado de clientes desde la BD
- `03_mesas_libres.png` – consulta de mesas disponibles
- `04_carta_menu.png` – carta completa del restaurante
