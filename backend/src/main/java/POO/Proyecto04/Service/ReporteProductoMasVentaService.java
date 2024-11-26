package POO.Proyecto04.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import POO.Proyecto04.dto.ProductoReporteMasVentasDTO;

@Service
public class ReporteProductoMasVentaService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<ProductoReporteMasVentasDTO> getReporte(){
		String sql="SELECT p.nombre AS producto, SUM(d.cantidad) AS total_vendido ";
		sql+="FROM Producto p ";
		sql+="JOIN Detalle_mov d ON p.id_producto = d.id_producto ";
		sql+="JOIN Movimiento m ON m.id_mov = d.id_mov ";
		sql+="WHERE m.tipoMovimiento = 'Venta' ";
		sql+="GROUP BY p.nombre ";
		sql+="ORDER BY total_vendido DESC; ";
		
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ProductoReporteMasVentasDTO.class));
		
	}

}
