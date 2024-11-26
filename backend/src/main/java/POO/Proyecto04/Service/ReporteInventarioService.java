package POO.Proyecto04.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import POO.Proyecto04.dto.ReporteInventarioDTO;

@Service
public class ReporteInventarioService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<ReporteInventarioDTO> getReporteInventario(){
		String sql="SELECT p.nombre AS nombre, i.existencias AS stock_actual, ";
		sql+="COALESCE(COUNT(CASE WHEN m.tipoMovimiento = 'Venta' THEN 1 END), 0) AS nro_mov_venta, ";
		sql+="COALESCE(COUNT(CASE WHEN m.tipoMovimiento = 'Compra' THEN 1 END), 0) AS nro_mov_compra, ";
		sql+="COALESCE(COUNT(CASE WHEN m.tipoMovimiento = 'DevolucionCliente' THEN 1 END), 0) AS nro_mov_dev_cliente, ";
		sql+="COALESCE(COUNT(CASE WHEN m.tipoMovimiento = 'DevolucionProveedor' THEN 1 END), 0) AS nro_mov_dev_proveedor ";
		sql+="FROM Producto p ";
		sql+="JOIN Inventario i ON p.id_producto = i.id_producto ";
		sql+="LEFT JOIN Detalle_mov d ON d.id_producto = p.id_producto ";
		sql+="LEFT JOIN Movimiento m ON m.id_mov = d.id_mov ";
		sql+="GROUP BY p.nombre, i.existencias; ";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ReporteInventarioDTO.class));
		
		
	}

}
