
SELECT
    r.id_reserva,
    r.hora,
    CONCAT(c.nombre, ' ', c.apellido) AS cliente,
    c.telefono,
    m.numero_mesa,
    m.capacidad,
    r.num_personas,
    r.estado
FROM reserva r
JOIN cliente c ON r.id_cliente = c.id_cliente
JOIN mesa    m ON r.id_mesa    = m.id_mesa
WHERE r.fecha = CURRENT_DATE
ORDER BY r.hora;


SELECT
    m.id_mesa,
    m.numero_mesa,
    m.capacidad
FROM mesa m
WHERE m.capacidad >= 4   -- cambiar según búsqueda
  AND m.id_mesa NOT IN (
      SELECT id_mesa FROM reserva
      WHERE fecha = CURRENT_DATE
        AND estado IN ('pendiente', 'confirmada')
  )
ORDER BY m.capacidad;


SELECT
    p.id_pedido,
    CONCAT(c.nombre, ' ', c.apellido) AS cliente,
    m.numero_mesa,
    CONCAT(e.nombre, ' ', e.apellido) AS camarero,
    pl.nombre                          AS plato,
    lp.cantidad,
    lp.precio_unitario,
    (lp.cantidad * lp.precio_unitario) AS subtotal,
    p.estado
FROM pedido p
JOIN reserva     r  ON p.id_reserva  = r.id_reserva
JOIN cliente     c  ON r.id_cliente  = c.id_cliente
JOIN mesa        m  ON r.id_mesa     = m.id_mesa
JOIN empleado    e  ON p.id_empleado = e.id_empleado
JOIN linea_pedido lp ON p.id_pedido  = lp.id_pedido
JOIN plato       pl ON lp.id_plato   = pl.id_plato
WHERE p.id_pedido = 1
ORDER BY lp.id_linea;


SELECT
    pl.nombre       AS plato,
    cat.nombre      AS categoria,
    SUM(lp.cantidad) AS total_pedido,
    pl.precio
FROM linea_pedido lp
JOIN plato    pl  ON lp.id_plato    = pl.id_plato
JOIN categoria cat ON pl.id_categoria = cat.id_categoria
GROUP BY pl.id_plato, pl.nombre, cat.nombre, pl.precio
ORDER BY total_pedido DESC;


SELECT
    CONCAT(e.nombre, ' ', e.apellido) AS empleado,
    e.rol,
    COUNT(p.id_pedido)                AS pedidos_atendidos,
    SUM(p.total)                      AS total_facturado
FROM empleado e
LEFT JOIN pedido p ON e.id_empleado = p.id_empleado
                   AND p.estado = 'cobrado'
GROUP BY e.id_empleado, e.nombre, e.apellido, e.rol
ORDER BY total_facturado DESC NULLS LAST;


SELECT
    r.fecha,
    r.hora,
    m.numero_mesa,
    r.num_personas,
    r.estado,
    COALESCE(p.total, 0.00) AS importe
FROM reserva r
JOIN mesa m ON r.id_mesa = m.id_mesa
LEFT JOIN pedido p ON p.id_reserva = r.id_reserva
WHERE r.id_cliente = 1
ORDER BY r.fecha DESC, r.hora DESC;


SELECT
    cat.nombre      AS categoria,
    pl.nombre       AS plato,
    pl.descripcion,
    pl.precio,
    CASE WHEN pl.disponible THEN 'Disponible' ELSE 'No disponible' END AS estado
FROM plato pl
JOIN categoria cat ON pl.id_categoria = cat.id_categoria
WHERE pl.disponible = TRUE
ORDER BY cat.nombre, pl.precio;


SELECT
    r.fecha,
    COUNT(DISTINCT r.id_reserva)       AS total_reservas,
    COUNT(DISTINCT p.id_pedido)        AS total_pedidos,
    COALESCE(SUM(p.total), 0)          AS facturacion_total
FROM reserva r
LEFT JOIN pedido p ON p.id_reserva = r.id_reserva
                   AND p.estado = 'cobrado'
GROUP BY r.fecha
ORDER BY r.fecha DESC;
