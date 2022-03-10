/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.INA.sysVentas.Services;

import com.INA.sysVentas.dao.IClienteDao;
import com.INA.sysVentas.domain.Cliente;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Progra
 */
public class ClienteService implements IClienteService{
    @Autowired
    private IClienteDao clienteDao;
    
    @Override
    @Transactional
    public void guardar(Cliente cliente) {
        clienteDao.save(cliente);
    }

    
    @Override
    public void eliminar(Cliente Cliente) {
        clienteDao.delete(Cliente);
    }
   
    
    @Transactional(readOnly = true)
    @Override
    public List<Cliente> listar() {
        return clienteDao.findAll();
    }

    
    @Override
    public List<Cliente> listar(String nombre,String apellido) {
        return (List<Cliente>) clienteDao.findByNombreContainsOrApellidoContains(nombre, apellido);
    }

    @Override
    public List<Cliente> listar(double limite) {
        
    }

    @Override
    public Cliente obtenerCliente(Long idCliente) {
        return clienteDao.findById(idCliente).orElse(null);
    }
    
}
