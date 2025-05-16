package birt.daw.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import birt.daw.apirest.entity.Usuario;
import birt.daw.apirest.servicio.ServicioUsuario;

@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	private ServicioUsuario usuarioServicio;
	
	@GetMapping("/usuarios")
	public List<Usuario> findAll() {
		return usuarioServicio.findAll();
	}
	
	@GetMapping("/usuarios/{idUsuario}")
	public Usuario getUsuario(@PathVariable int idUsuario) {
		Usuario usuario = usuarioServicio.findById(idUsuario);
		
		if (usuario == null) {
			throw new RuntimeException("No existe el usuario: " + idUsuario);
		}
		
		return usuario;
	}
	
	@PostMapping("/usuarios")
	public Usuario addUsuario(@RequestBody Usuario usuario) {
		
		usuarioServicio.save(usuario);
		
		return usuario;
	}

	@PutMapping("/updateusuarios")
	public Usuario updateUsuario(@RequestBody Usuario usuario) {
		usuarioServicio.save(usuario);
		
		return usuario;
	}
	
	@DeleteMapping("/usuarios/{idUsuario}")
	public String deleteUsuario(@PathVariable int idUsuario) {
		Usuario usuario = usuarioServicio.findById(idUsuario);
		if (usuario == null) {
			throw new RuntimeException("No existe el alumno " + idUsuario);
		}
		
		usuarioServicio.deleteById(idUsuario);
		
		return "Eliminado el usuario " + idUsuario;
	}
}
