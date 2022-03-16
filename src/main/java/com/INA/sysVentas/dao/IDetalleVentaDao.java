/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.INA.sysVentas.dao;

import com.INA.sysVentas.domain.DetalleVenta;
import com.INA.sysVentas.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Progra
 */
public interface IDetalleVentaDao extends JpaRepository<DetalleVenta,Long>{
    public Iterable<DetalleVenta> findByVenta(Venta venta);
}
