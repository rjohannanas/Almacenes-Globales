package POO.Proyecto04.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import POO.Proyecto04.dto.StockDTO;

@Service
public class StockService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String actualizarMinMaxStock(StockDTO bean) {

		//Verificar si existe el producto
		String verificar="Select count(1) cont from Inventario where id_producto=? ";
		int cont=jdbcTemplate.queryForObject(verificar,Integer.class,bean.getIdproducto());
		if(cont!=1) {
			return "El producto con id "+bean.getIdproducto()+" no existe";
		}
		//Actualizando el producto
		String update="update Inventario set max_existencias=?, min_existencias=? where id_producto=? ";
		jdbcTemplate.update(update,bean.getMax(),bean.getMin(),bean.getIdproducto());
		
		return "Datos maximos y minimos actualizados correctamanete";
	}

}
