/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.INA.sysVentas.Services;

import com.INA.sysVentas.domain.Producto;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IProductoService {
    public void guardar(Producto producto);
    public void eliminar(Producto producto);
    public List<Producto> listar();
    public List<Producto> listar(String descripcion);
    public List<Producto> listar(int existencias);
    public Producto obtenerProducto(Long idProducto);
}
