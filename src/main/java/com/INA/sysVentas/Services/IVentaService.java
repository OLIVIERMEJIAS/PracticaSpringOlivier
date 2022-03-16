/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.INA.sysVentas.Services;

import com.INA.sysVentas.domain.DetalleVenta;
import com.INA.sysVentas.domain.Factura;
import com.INA.sysVentas.domain.Venta;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Progra
 */
public interface IVentaService {
     public List<Venta> listarVentas();
     public List<Venta> listarVentas(boolean cancelada);
     public List<Venta> listarVentas(Calendar fecha);
     public List<DetalleVenta> listarDetalles(Long idVenta);
     
     public Venta buscarVenta(Long id);
     public DetalleVenta buscarDetalle(Long id);
     
     public Factura guardar(Factura factura);
     
     public void eliminar(Venta venta);
     
     public Factura eliminarDetalle(DetalleVenta detalle);
     
     public int pagarVenta(Long idVenta);
}
