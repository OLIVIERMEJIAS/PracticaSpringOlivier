/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.INA.sysVentas.controller;
import com.INA.sysVentas.Services.IClienteService;
import com.INA.sysVentas.Services.IProductoService;
import com.INA.sysVentas.domain.Cliente;
import com.INA.sysVentas.domain.Producto;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

public class ProductoController {
    @Autowired
    private IProductoService servicioProducto;

    @GetMapping("/productos")
    public String listar(Model model){
        List<Producto> lista = servicioProducto.listar();
        model.addAttribute("productos",lista);
        return "listaProductos";
    }
     @PostMapping("/filtrarProductos")
    public String filtrar(String txtTexto, Model model){
        List<Producto> lista = servicioProducto.listar(txtTexto);
        model.addAttribute("productos",lista);
        return "listaProductos";
    }
    @GetMapping("/nuevoProducto")
    public String nuevo(Producto producto){
        return "producto";
    }
    @PostMapping("/guardarProducto")
    public String guardar(@Valid Producto producto, Errors er){
        if(er.hasErrors())
        {
            return "producto";
        }
        servicioProducto.guardar(producto);
       
        return "redirect:/productos";
    }
    @GetMapping("/editarProducto/{idProducto}")
    public String editar(Producto producto,Model model,RedirectAttributes red){
        producto = servicioProducto.obtenerProducto(producto.getIdProducto());
        if(producto != null){
            model.addAttribute("producto",producto);
            return "producto";
        }
        String msg = "No se logró cargar el producto";
        red.addFlashAttribute("msg",msg);
        return "redirect:/listaProductos";
        
        
    }
    @GetMapping("/borradoProducto")
    public String confirmarBorrado(Producto producto,Model model,RedirectAttributes red){
        producto = servicioProducto.obtenerProducto(producto.getIdProducto());
         if(producto != null){
            model.addAttribute("producto",producto);
            return "borrarProducto";
        }
        String msg = "No se logró cargar el producto";
        red.addFlashAttribute("msg",msg);
        return "redirect:/listaProductos";
    }
    @GetMapping("/eliminarProducto")
    public String eliminar(Producto producto, Model model, RedirectAttributes red){
        int resultado = servicioProducto.eliminar(producto);
        String msg = "";
        if(resultado == 0){
            msg = "No se puede eliminar, porque este producto ya fue registrado en facturas!";
        }
        else{
            msg = "Producto eliminado";
        }
       red.addFlashAttribute("msg",msg);
        return "redirect:/productos";
    }
}
