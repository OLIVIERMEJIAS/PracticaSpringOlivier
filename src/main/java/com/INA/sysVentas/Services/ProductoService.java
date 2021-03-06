/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.INA.sysVentas.Services;


import com.INA.sysVentas.dao.IProductoDao;
import com.INA.sysVentas.domain.Cliente;
import com.INA.sysVentas.domain.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HP
 */
@Service
public class ProductoService implements IProductoService{
    @Autowired
    private IProductoDao productoDao;
    
    @Override
    @Transactional
    public void guardar(Producto producto) {
        productoDao.save(producto);
    }

    
     @Override
    @Transactional
    public Integer eliminar(Producto producto) {
        return productoDao.eliminar_producto(producto.getIdProducto());
    }
   
    
    @Transactional(readOnly = true)
    @Override
    public List<Producto> listar() {
        return productoDao.findAll();
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> listar(String descripcion) {
        return (List<Producto>) productoDao.findByDescripcionContains(descripcion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listar(int existencias) {
        return (List<Producto>) productoDao.buscarPorExistencias(existencias);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto obtenerProducto(Long idProducto) {
        return productoDao.findById(idProducto).orElse(null);
    }
    
}
