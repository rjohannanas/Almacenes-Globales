package POO.Proyecto04.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import POO.Proyecto04.dto.ReporteAlmacenDTO;

@Service
public class ReporteAlmacenService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<ReporteAlmacenDTO> getReporteAlmacen(){
		String sql="";
		sql+="select p.nombre as nombre, i.existencias as stock_actual, ";
		sql+="(i.existencias*p.costo_almacen) as costo_almacen ";
		sql+="from Producto p ";
		sql+="join Inventario i on p.id_producto=i.id_producto ";
		
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ReporteAlmacenDTO.class));
		
	}

}
