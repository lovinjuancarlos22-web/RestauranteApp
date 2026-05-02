


INSERT INTO cliente (nombre, apellido, telefono, email) VALUES
('Ana',      'García López',     '612345678', 'ana.garcia@email.com'),
('Carlos',   'Martínez Ruiz',    '623456789', 'carlos.m@email.com'),
('Sofía',    'Fernández Díaz',   '634567890', 'sofia.fd@email.com'),
('Miguel',   'López Sánchez',    '645678901', 'miguel.ls@email.com'),
('Lucía',    'Pérez González',   '656789012', 'lucia.pg@email.com'),
('David',    'Romero Torres',    '667890123', 'david.rt@email.com'),
('Elena',    'Jiménez Morales',  '678901234', 'elena.jm@email.com'),
('Pablo',    'Alonso Navarro',   '689012345', 'pablo.an@email.com');


INSERT INTO mesa (numero_mesa, capacidad, estado) VALUES
(1,  2, 'libre'),
(2,  4, 'libre'),
(3,  4, 'ocupada'),
(4,  6, 'reservada'),
(5,  6, 'libre'),
(6,  2, 'libre'),
(7,  8, 'libre'),
(8,  4, 'ocupada');


INSERT INTO empleado (nombre, apellido, rol, telefono) VALUES
('Javier',   'Rueda Molina',     'encargado', '611111111'),
('Marta',    'Castillo Vera',    'camarero',  '622222222'),
('Tomás',    'Benítez Lara',     'camarero',  '633333333'),
('Rosa',     'Delgado Fuente',   'cocinero',  '644444444'),
('Antonio',  'Vega Ramos',       'cocinero',  '655555555');


INSERT INTO categoria (nombre, descripcion) VALUES
('Entrantes',   'Primeros platos, tapas y aperitivos'),
('Principales', 'Platos de carne, pescado y pasta'),
('Postres',     'Postres caseros y repostería'),
('Bebidas',     'Refrescos, agua, vinos y cócteles');


INSERT INTO plato (id_categoria, nombre, descripcion, precio, disponible) VALUES

(1, 'Croquetas de jamón',      '8 unidades, bechamel casera',           7.50,  TRUE),
(1, 'Ensalada mixta',          'Tomate, lechuga, atún, aceituna',       6.00,  TRUE),
(1, 'Patatas bravas',          'Con salsa brava y alioli',              5.50,  TRUE),
(1, 'Jamón ibérico',           'Loncheado, con pan con tomate',        12.00,  TRUE),

(2, 'Secreto ibérico',         'Con patatas panaderas y pimientos',    15.00,  TRUE),
(2, 'Merluza a la romana',     'Con ensalada y limón',                 14.00,  TRUE),
(2, 'Pechuga de pollo',        'A la plancha con verduras salteadas',  11.00,  TRUE),
(2, 'Paella de marisco',       'Para 2 personas, precio por persona',  18.00,  TRUE),
(2, 'Pasta carbonara',         'Tallarines, bacon, huevo, parmesano',  10.50,  TRUE),

(3, 'Tarta de queso',          'Casera, con mermelada de frutos rojos', 5.00,  TRUE),
(3, 'Flan de huevo',           'Casero con caramelo',                   4.00,  TRUE),
(3, 'Coulant de chocolate',    'Tibio, con helado de vainilla',         5.50,  TRUE),

(4, 'Agua mineral',            '50cl',                                  1.50,  TRUE),
(4, 'Refresco',                'Coca-cola, Fanta, Sprite 33cl',         2.50,  TRUE),
(4, 'Vino de la casa',         'Copa de vino tinto o blanco',           2.50,  TRUE),
(4, 'Cerveza',                 'Caña o botellín',                       2.00,  TRUE);


INSERT INTO reserva (id_cliente, id_mesa, fecha, hora, num_personas, estado) VALUES
(1, 2, CURRENT_DATE,         '20:30', 3, 'confirmada'),
(2, 4, CURRENT_DATE,         '21:00', 5, 'confirmada'),
(3, 1, CURRENT_DATE + 1,     '14:00', 2, 'pendiente'),
(4, 5, CURRENT_DATE + 1,     '21:30', 4, 'pendiente'),
(5, 7, CURRENT_DATE - 1,     '21:00', 7, 'completada'),
(6, 3, CURRENT_DATE,         '14:30', 4, 'confirmada'),
(7, 6, CURRENT_DATE + 2,     '20:00', 2, 'pendiente'),
(8, 2, CURRENT_DATE - 2,     '14:00', 3, 'completada');


INSERT INTO pedido (id_reserva, id_empleado, fecha_hora, total, estado) VALUES
(5, 2, CURRENT_TIMESTAMP - INTERVAL '1 day',  78.50, 'cobrado'),
(8, 3, CURRENT_TIMESTAMP - INTERVAL '2 days', 52.00, 'cobrado'),
(1, 2, CURRENT_TIMESTAMP,                      0.00, 'abierto'),
(6, 3, CURRENT_TIMESTAMP,                      0.00, 'abierto');


INSERT INTO linea_pedido (id_pedido, id_plato, cantidad, precio_unitario) VALUES

(1, 1,  2, 7.50),   -- Croquetas x2
(1, 8,  3, 18.00),  -- Paella de marisco x3 personas
(1, 5,  2, 15.00),  -- Secreto ibérico x2
(1, 15, 6,  2.50),  -- Vino de la casa x6
(1, 10, 4,  5.00),  -- Tarta de queso x4

(2, 3,  1, 5.50),   -- Patatas bravas
(2, 7,  2, 11.00),  -- Pechuga de pollo x2
(2, 9,  1, 10.50),  -- Pasta carbonara
(2, 16, 3,  2.00),  -- Cerveza x3
(2, 11, 2,  4.00);  -- Flan de huevo x2


UPDATE pedido SET total = (
    SELECT SUM(cantidad * precio_unitario) FROM linea_pedido WHERE id_pedido = pedido.id_pedido
) WHERE id_pedido IN (1, 2);
