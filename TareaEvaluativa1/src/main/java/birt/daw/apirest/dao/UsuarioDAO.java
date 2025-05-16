package birt.daw.apirest.dao;

import java.util.List;

import birt.daw.apirest.entity.Usuario;

public interface UsuarioDAO {
	public List<Usuario>findAll();
	
	public Usuario findById(int id);
	
	public void save(Usuario usuario);
	
	public void deleteById(int id);
}
