/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.INA.sysVentas.dao;

import com.INA.sysVentas.domain.Venta;
import java.util.Calendar;
import java.util.HashMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Progra
 */
public interface IVentaDao extends JpaRepository<Venta,Long>{
    public Iterable<Venta> findByCancelada(boolean cancelada);
    
    public Iterable<Venta> findByFecha(Calendar fecha);
    
    @Procedure(name = "facturar")
    public HashMap facturar(@Param("TIPO") String tipo,
            @Param("ID_CLIENTE") Long idCliente,
            @Param("ID_PRODUCTO") Long idProducto,
            @Param("CANTIDAD") int tip,
            @Param("PRECIO_VENTA") double precioVenta,
            @Param("ID_VENTA") Long idVenta,
            @Param("retorno") int retorno);
}
