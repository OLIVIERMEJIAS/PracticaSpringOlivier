
package com.INA.sysVentas.Services;

import com.INA.sysVentas.dao.IDetalleVentaDao;
import com.INA.sysVentas.dao.IProductoDao;
import com.INA.sysVentas.dao.IVentaDao;
import com.INA.sysVentas.domain.DetalleVenta;
import com.INA.sysVentas.domain.Factura;
import com.INA.sysVentas.domain.Producto;
import com.INA.sysVentas.domain.Venta;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{
    @Autowired
    private IVentaDao ventaDao;
    @Autowired
    private IDetalleVentaDao detalleDao;
    @Autowired
    private IProductoDao productoDao;
    
    @Override
    public List<Venta> listarVentas() {
        return ventaDao.findAll();
    }

    @Override
    public List<Venta> listarVentas(boolean cancelada) {
        return (List<Venta>) ventaDao.findByCancelada(cancelada);
    }

    @Override
    public List<Venta> listarVentas(Calendar fecha) {
        return (List<Venta>) ventaDao.findByFecha(fecha);
    }

    @Override
    public List<DetalleVenta> listarDetalles(Long idVenta) {
           Venta tempVenta = new Venta();
           tempVenta.setIdVenta(idVenta);
        return (List<DetalleVenta>) detalleDao.findByVenta(tempVenta);
    }

    @Override
    public Venta buscarVenta(Long id) {
        return ventaDao.findById(id).orElse(null);
    }

    @Override
    public DetalleVenta buscarDetalle(Long id) {
        return detalleDao.findById(id).orElse(null);
    }

    @Override
    public Factura guardar(Factura factura) {
        Producto producto = productoDao.findById(factura.getIdProducto()).orElse(null);
        if(producto != null){
            factura.setPrecio(producto.getPrecio());
        }
        HashMap resultado = ventaDao.facturar(factura.getTipo(), 
                factura.getIdCliente(),factura.getIdProducto(),
               factura.getCantidad(), factura.getPrecio(),
                factura.getIdVenta(),factura.getRetorno());
        factura.setIdVenta((long) resultado.get("idVenta"));
        factura.setRetorno((int) resultado.get("retorno"));
        return factura;
    }
    

    @Override
    public void eliminar(Venta venta) {
          venta = ventaDao.findById(venta.getIdVenta()).orElse(null);
          if(venta != null){
              if(!venta.isCancelada()){
              ventaDao.delete(venta);
              }
          }
    }

    @Override
    public Factura eliminarDetalle(DetalleVenta detalle) {
        Factura factura = new Factura();
        detalle = detalleDao.findById(detalle.getIdDetalle()).orElse(null);
          if(detalle != null){
              Venta venta = ventaDao.findById(detalle.getVenta().getIdVenta()).orElse(null);
              if(venta != null){
                  factura.setIdVenta(venta.getIdVenta());
                  factura.setIdCliente(venta.getCliente().getIdCliente());
                  factura.setNombreCliente(venta.getCliente().getNombre() + " " +
                          venta.getCliente().getApellido());
                  factura.setTipo(venta.getTipo());
                  factura.setFecha(venta.getFecha());
                  factura.setDetalles(venta.getDetalles());
                  
              }
                if(!venta.isCancelada()){
                     detalleDao.delete(detalle);
                     factura.setDetalles(listarDetalles(venta.getIdVenta()));
                  }
          }
        return factura;
    }

    @Override
    public int pagarVenta(Long idVenta) {
       //TAREA!!!!!!!!!!!!!
       //tRANSACTIONALES AGREGAR!!!!!!!!!!!
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   
}
