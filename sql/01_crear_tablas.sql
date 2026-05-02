


DROP TABLE IF EXISTS linea_pedido CASCADE;
DROP TABLE IF EXISTS pedido CASCADE;
DROP TABLE IF EXISTS reserva CASCADE;
DROP TABLE IF EXISTS plato CASCADE;
DROP TABLE IF EXISTS categoria CASCADE;
DROP TABLE IF EXISTS mesa CASCADE;
DROP TABLE IF EXISTS empleado CASCADE;
DROP TABLE IF EXISTS cliente CASCADE;


CREATE TABLE cliente (
    id_cliente   SERIAL PRIMARY KEY,
    nombre       VARCHAR(50)  NOT NULL,
    apellido     VARCHAR(50)  NOT NULL,
    telefono     VARCHAR(15),
    email        VARCHAR(100) UNIQUE
);


CREATE TABLE mesa (
    id_mesa       SERIAL PRIMARY KEY,
    numero_mesa   INT         NOT NULL UNIQUE,
    capacidad     INT         NOT NULL CHECK (capacidad > 0),
    estado        VARCHAR(20) NOT NULL DEFAULT 'libre'
                  CHECK (estado IN ('libre', 'ocupada', 'reservada'))
);

CREATE TABLE empleado (
    id_empleado  SERIAL PRIMARY KEY,
    nombre       VARCHAR(50)  NOT NULL,
    apellido     VARCHAR(50)  NOT NULL,
    rol          VARCHAR(30)  NOT NULL CHECK (rol IN ('camarero', 'cocinero', 'encargado')),
    telefono     VARCHAR(15)
);

CREATE TABLE categoria (
    id_categoria  SERIAL PRIMARY KEY,
    nombre        VARCHAR(50)  NOT NULL UNIQUE,
    descripcion   VARCHAR(200)
);

CREATE TABLE plato (
    id_plato      SERIAL PRIMARY KEY,
    id_categoria  INT            NOT NULL REFERENCES categoria(id_categoria),
    nombre        VARCHAR(100)   NOT NULL,
    descripcion   VARCHAR(300),
    precio        DECIMAL(6,2)   NOT NULL CHECK (precio >= 0),
    disponible    BOOLEAN        NOT NULL DEFAULT TRUE
);

CREATE TABLE reserva (
    id_reserva    SERIAL PRIMARY KEY,
    id_cliente    INT          NOT NULL REFERENCES cliente(id_cliente),
    id_mesa       INT          NOT NULL REFERENCES mesa(id_mesa),
    fecha         DATE         NOT NULL,
    hora          TIME         NOT NULL,
    num_personas  INT          NOT NULL CHECK (num_personas > 0),
    estado        VARCHAR(20)  NOT NULL DEFAULT 'pendiente'
                  CHECK (estado IN ('pendiente', 'confirmada', 'cancelada', 'completada'))
);

CREATE TABLE pedido (
    id_pedido    SERIAL PRIMARY KEY,
    id_reserva   INT             REFERENCES reserva(id_reserva),
    id_empleado  INT             NOT NULL REFERENCES empleado(id_empleado),
    fecha_hora   TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total        DECIMAL(8,2)    NOT NULL DEFAULT 0.00,
    estado       VARCHAR(20)     NOT NULL DEFAULT 'abierto'
                 CHECK (estado IN ('abierto', 'en_preparacion', 'servido', 'cobrado'))
);

CREATE TABLE linea_pedido (
    id_linea        SERIAL PRIMARY KEY,
    id_pedido       INT           NOT NULL REFERENCES pedido(id_pedido),
    id_plato        INT           NOT NULL REFERENCES plato(id_plato),
    cantidad        INT           NOT NULL CHECK (cantidad > 0),
    precio_unitario DECIMAL(6,2)  NOT NULL CHECK (precio_unitario >= 0)
);

CREATE INDEX idx_reserva_cliente ON reserva(id_cliente);
CREATE INDEX idx_reserva_fecha   ON reserva(fecha);
CREATE INDEX idx_pedido_reserva  ON pedido(id_reserva);
CREATE INDEX idx_linea_pedido    ON linea_pedido(id_pedido);
CREATE INDEX idx_plato_categoria ON plato(id_categoria);
