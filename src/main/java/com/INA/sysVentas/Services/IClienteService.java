
package com.INA.sysVentas.Services;

import com.INA.sysVentas.domain.Cliente;
import java.util.List;


public interface IClienteService {
    public void guardar(Cliente cliente);
    public void eliminar(Cliente cliente);
    public List<Cliente> listar();
    public List<Cliente> listar(String nombre,String apellido);
    public List<Cliente> listar(double limite);
    public Cliente obtenerCliente(Long idCliente);
}
