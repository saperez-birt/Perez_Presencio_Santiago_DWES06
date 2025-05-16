package birt.daw.apirest.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import birt.daw.apirest.entity.Usuario;
import birt.daw.apirest.dao.UsuarioDAO;

@Service
public class ServicioUsuarioImpl implements ServicioUsuario {
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	public List<Usuario> findAll() {
		List<Usuario> usuarios = usuarioDAO.findAll();
		return usuarios;
	}

	@Override
	public Usuario findById(int id) {
		Usuario usuario = usuarioDAO.findById(id);
		return usuario;
	}

	@Override
	public void save(Usuario usuario) {
		usuarioDAO.save(usuario);
	}

	@Override
	public void deleteById(int id) {
		usuarioDAO.deleteById(id);

	}

}
