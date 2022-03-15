/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.INA.sysVentas.controller;

import com.INA.sysVentas.Services.IClienteService;
import com.INA.sysVentas.domain.Cliente;
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
public class ClienteController {
    @Autowired
    private IClienteService servicioCliente;
   
    @GetMapping("/clientes")
    public String listar(Model model){
        List<Cliente> lista = servicioCliente.listar();
        model.addAttribute("clientes",lista);
        return "listaClientes";
    }
    @PostMapping("/filtrarClientes")
    public String filtrar(String txtTexto, Model model){
        List<Cliente> lista = servicioCliente.
                listar(txtTexto, txtTexto);
        model.addAttribute("clientes",lista);
        return "listaClientes";
    }
    @GetMapping("/nuevoCliente")
    public String nuevo(Cliente cliente){
        return "cliente";
    }
    @PostMapping("/guardarCliente")
    public String guardar(@Valid Cliente cliente, Errors er, RedirectAttributes red){
        if(er.hasErrors())
        {
            return "cliente";
        }
        servicioCliente.guardar(cliente);
        red.addFlashAttribute("Éxito", "Todo salió bien.");
        return "redirect:/clientes";
    }
    @GetMapping("/editarCliente/{idCliente}")
    public String editar(Cliente cliente,Model model){
        cliente = servicioCliente.obtenerCliente(cliente.getIdCliente());
        if(cliente != null){
            model.addAttribute("cliente",cliente);
            return "cliente";
        }
        String msg = "No se logró cargar el cliente";
        List<Cliente> lista = servicioCliente.listar();
        model.addAttribute("lista",lista);
        model.addAttribute("msg",msg);
        return "listaCliente";
        
        
    }
    
    @GetMapping("/eliminarCliente")
    public String eliminar(Cliente cliente, Model model, RedirectAttributes red){
        int resultado = servicioCliente.eliminar(cliente);
        String msg = "";
        if(resultado == 0){
            msg = "No se puede eliminar, porque tiene ventas asociadas!";
        }
        else{
            msg = "Cliente eliminado";
        }
       red.addFlashAttribute("msg",msg);
        return "redirect:/clientes";
    }
}
