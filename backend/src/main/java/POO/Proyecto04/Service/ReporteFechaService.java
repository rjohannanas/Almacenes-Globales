package POO.Proyecto04.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import POO.Proyecto04.dto.ReporteFechaDTO;

@Service
public class ReporteFechaService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<ReporteFechaDTO> getReportFecha(Date fechaInicio, Date fechaFin){
		String sql="SELECT m.fecha_reg as fechaMovimiento, m.tipoMovimiento AS tipo_movimiento, p.nombre AS producto, d.cantidad ";
		sql+="FROM Movimiento m ";
		sql+="JOIN Detalle_mov d ON m.id_mov = d.id_mov ";
		sql+="JOIN Producto p ON d.id_producto = p.id_producto ";
		sql+="WHERE m.fecha_reg BETWEEN ? AND ? ";
		sql+="ORDER BY m.fecha_reg; ";
		
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ReporteFechaDTO.class),fechaInicio, fechaFin);
		
	}

}
