package POO.Proyecto04.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import POO.Proyecto04.dto.InicioDTO;

@Service
public class InicioService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int obtenerIdrol (InicioDTO bean) {
		
		String sql="SELECT id_rol FROM Usuario where logUsuario=? and pass=? ";
		try {
			return jdbcTemplate.queryForObject(sql,Integer.class,bean.getUsuario(),bean.getPass());
		} catch (EmptyResultDataAccessException e) {
			throw new IllegalArgumentException("Usuario o contraseña inválidos");
		}
	}

}
