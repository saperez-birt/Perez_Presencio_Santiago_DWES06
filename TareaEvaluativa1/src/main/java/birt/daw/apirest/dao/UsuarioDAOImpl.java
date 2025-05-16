package birt.daw.apirest.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import birt.daw.apirest.entity.Usuario;
import jakarta.persistence.EntityManager;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {
	@Autowired
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public List<Usuario> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Usuario> theQuery = currentSession.createQuery("from Usuario", Usuario.class);
		List<Usuario> usuarios = theQuery.getResultList();
		return usuarios;
	}

	@Override
	@Transactional
	public Usuario findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Usuario usuario = currentSession.get(Usuario.class, id);
		return usuario;
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		if (usuario.getIdUsuario() == 0) {
	        entityManager.persist(usuario);
	    } else {
	        entityManager.merge(usuario);
	    }
		
	}

	@Override
	@Transactional
	public void deleteById(int id) {
	    Usuario usuario = entityManager.find(Usuario.class, id);
	    if (usuario != null) {
	        entityManager.remove(usuario);
	    }
	}
}
