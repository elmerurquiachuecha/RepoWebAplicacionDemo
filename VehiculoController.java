package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.entity.Vehiculo;
import com.example.demo.service.VehiculoService;

@Controller
public class VehiculoController {
	@Autowired
	private VehiculoService vehiculoService;
	

	@GetMapping("/listado")
	public String listar(Model model) {
		List<Vehiculo> lista = vehiculoService.listarTodos();
		model.addAttribute("titulo", "LISTA DE VEHICULO");
		model.addAttribute("vehiculos", lista);
		return "listado";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
	 Vehiculo vehiculo = new Vehiculo();
		List<Vehiculo> listaVehiculos = vehiculoService.listarTodos();
		model.addAttribute("titulo", "NUEVO VEHICULO");
		model.addAttribute("vehiculo", vehiculo);
		model.addAttribute("vehiculos", listaVehiculos);
	 return "frmEditar";
	}
	
	@PostMapping("/grabar")
	public String guardar(@ModelAttribute Vehiculo vehiculo) {
	vehiculoService.grabar(vehiculo);
	System.out.println("Vehiculo grabado con exito!");
	return "redirect:http://localhost:8080/listado";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id, Model model) {
	Vehiculo vehiculo = vehiculoService.buscarPorId(id);
	List<Vehiculo> listaVehiculos = vehiculoService.listarTodos();
	model.addAttribute("titulo", "EDITAR PRODUCTO (" + id + ")");
	model.addAttribute("vehiculo", vehiculo);
	model.addAttribute("vehiculos", listaVehiculos);
	return "frmEditar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long id) {
	vehiculoService.eliminar(id);
	System.out.println("Vehiculo eliminado con exito!");
	return "redirect:http://localhost:8080/listado";
	}
}





