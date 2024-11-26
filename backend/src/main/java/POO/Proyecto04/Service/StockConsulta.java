package POO.Proyecto04.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import POO.Proyecto04.dto.StockConsultaDTO;

@Service
public class StockConsulta {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	public String consultaStock(StockConsultaDTO bean) {
		

	    // Verificar existencia del producto
	    String queryExistenciaProducto = "SELECT COUNT(1) FROM Inventario WHERE id_producto = ?";
	    int count = jdbcTemplate.queryForObject(queryExistenciaProducto, Integer.class, bean.getIdproducto());
	    if (count != 1) {
	        return "El producto con id " + bean.getIdproducto() + " no existe";
	    }
	    
	    // Consulta para obtener la cantidad actual de existencias
	    String consulta = "SELECT existencias FROM Inventario WHERE id_producto = ?";
	    int cantidad = jdbcTemplate.queryForObject(consulta, Integer.class, bean.getIdproducto());
	    
	    // Consultar los límites máximos y mínimos de existencias
	    String maxLimite = "SELECT max_existencias FROM Inventario WHERE id_producto = ?";
	    int max = jdbcTemplate.queryForObject(maxLimite, Integer.class, bean.getIdproducto());

	    String minLimite = "SELECT min_existencias FROM Inventario WHERE id_producto = ?";
	    int min = jdbcTemplate.queryForObject(minLimite, Integer.class, bean.getIdproducto());

	    // Verificar si la cantidad de existencias está dentro de los límites
	    if (cantidad > max) {
	        return "Hay un sobrestock del producto con id " + bean.getIdproducto() + ". El producto con cuenta con una cantidad de " + cantidad;
	    } else if (cantidad < min) {
	        return "Debe haber una reposición del producto con id " + bean.getIdproducto() + ". El producto con cuenta con una cantidad de " + cantidad;
	    }

	    // Si la cantidad está dentro de los límites establecidos
	    return "El producto con id " + bean.getIdproducto() + " cuenta con una cantidad de " + cantidad 
	           + " y está dentro de los límites establecidos.";

	    
	}
	


	
	
}



 	
