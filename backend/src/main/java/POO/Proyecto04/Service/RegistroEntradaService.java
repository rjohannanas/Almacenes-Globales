package POO.Proyecto04.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import POO.Proyecto04.dto.RegistroEntradaDTO;

@Service
public class RegistroEntradaService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional
	public String compraproducto(RegistroEntradaDTO bean) {
		//Verificar que exista el usuario
		String usuario="SELECT r.id_rol FROM Usuario u ";
		usuario+="join Rol r on u.id_usuario=r.id_rol ";
		usuario+="where id_usuario=? ";
		try {
			int idrol=jdbcTemplate.queryForObject(usuario, Integer.class,bean.getIdusuario());
			if(idrol!=4) {
				return "El usuario no tiene permiso para realizar esta operacion";
			}
			
		} catch (Exception e) {
			return "El usario con id " + bean.getIdusuario() + " no existe.";
		}
		
		
		// Verificar que el idproducto exista
        String contador = "select count(1) from Producto where id_producto=?";
        int cont = jdbcTemplate.queryForObject(contador, Integer.class, bean.getIdproducto());
        if (cont != 1) {
            return "El producto con ID " + bean.getIdproducto() + " no existe";
        }
		
        
		//Verificar que exista el Asociado
		String asociado = "SELECT tipoAsociado FROM Asociado WHERE id_asoc=? ";
		String tipoaso = null;

		try {
		    tipoaso = jdbcTemplate.queryForObject(asociado, String.class, bean.getIdasociado());
		} catch (EmptyResultDataAccessException e) {
		    
		    return "El asociado con id " + bean.getIdasociado() + " no existe.";
		}

		if (tipoaso != null && !tipoaso.equalsIgnoreCase("Proveedor")) {
		    return "El proveedor con id " + bean.getIdasociado() + " no es un proveedor válido.";
		}
		
		
		//Agregando el Inventario
		String ei="select count(1) from Inventario where id_producto=? ";
		int exi=jdbcTemplate.queryForObject(ei, Integer.class, bean.getIdproducto());
		if(exi!=1) {
			String inventario1="insert into Inventario(id_producto,existencias,max_existencias,min_existencias) ";
			inventario1+="values (?,?,?,?) ";
			jdbcTemplate.update(inventario1,bean.getIdproducto(),bean.getCantidad(),bean.getMaxstock(),bean.getMinstock());
		}
		
		String inventario2="UPDATE Inventario SET existencias = existencias + ? WHERE id_producto = ?";
		jdbcTemplate.update(inventario2,bean.getCantidad(),bean.getIdproducto());
		
		
		//Movimiento
		String movimiento = "INSERT INTO Movimiento (tipoMovimiento, id_asociado, id_usuario, fecha_reg) ";
		movimiento += "VALUES ('Compra', ?, ?, CURRENT_TIMESTAMP);";
		jdbcTemplate.update(movimiento,bean.getIdasociado(),bean.getIdusuario());
		
		//Detalle de Movimiento
		String mov="SELECT MAX(id_mov) FROM Movimiento ";
		int idmov=jdbcTemplate.queryForObject(mov,Integer.class);
		
		String costo="select costo_almacen from Producto where id_producto=? ";
		int cost=jdbcTemplate.queryForObject(costo,Integer.class,bean.getIdproducto());
		int cant=bean.getCantidad();
		int costototal=cost*cant;
		
		String detl="insert into Detalle_mov(id_mov,id_producto,cantidad,costo_total) values(?,?,?,?) ";
		jdbcTemplate.update(detl,idmov,bean.getIdproducto(),bean.getCantidad(),costototal);
				
		
		//Registro en Auditoria
		String auditoria="insert into Auditoria_reg (descripcion,id_usuario,fecha) values ('Se registró una compra',?,CURRENT_TIMESTAMP) ";
		jdbcTemplate.update(auditoria,bean.getIdusuario());
		
		return"Se realizó una compra exitosa del producto";
	}
	
	
	@Transactional
	public String devolucionCliente(RegistroEntradaDTO bean) {
		//Verificar que exista el usuario
		String usuario="SELECT r.id_rol FROM Usuario u ";
		usuario+="join Rol r on u.id_usuario=r.id_rol ";
		usuario+="where id_usuario=? ";
		try {
			int idrol=jdbcTemplate.queryForObject(usuario, Integer.class,bean.getIdusuario());
			if(idrol!=4) {
				return "El usuario no tiene permiso para realizar esta operacion";
			}
			
		} catch (Exception e) {
			return "El usario con id " + bean.getIdusuario() + " no existe.";
		}
		
		
		// Verificar que el idproducto exista
        String contador = "select count(1) from Producto where id_producto=?";
        int cont = jdbcTemplate.queryForObject(contador, Integer.class, bean.getIdproducto());
        if (cont != 1) {
            return "El producto con ID " + bean.getIdproducto() + " no existe";
        }
		
        
		//Verificar que exista el Asociado
		String asociado = "SELECT tipoAsociado FROM Asociado WHERE id_asoc=? ";
		String tipoaso = null;

		try {
		    tipoaso = jdbcTemplate.queryForObject(asociado, String.class, bean.getIdasociado());
		} catch (EmptyResultDataAccessException e) {
		    
		    return "El asociado con id " + bean.getIdasociado() + " no existe.";
		}

		if (tipoaso != null && !tipoaso.equalsIgnoreCase("Proveedor")) {
		    return "El proveedor con id " + bean.getIdasociado() + " no es un proveedor válido.";
		}
		
		
		//Agregando el Inventario
		String inventario2="UPDATE Inventario SET existencias = existencias+? WHERE id_producto = ?";
		jdbcTemplate.update(inventario2,bean.getCantidad(),bean.getIdproducto());
		
		
		//Movimiento
		String movimiento = "INSERT INTO Movimiento (tipoMovimiento, id_asociado, id_usuario, fecha_reg) ";
		movimiento += "VALUES ('DevolucionCliente', ?, ?, CURRENT_TIMESTAMP);";
		jdbcTemplate.update(movimiento,bean.getIdasociado(),bean.getIdusuario());
		
		//Detalle de Movimiento
		String mov="SELECT MAX(id_mov) FROM Movimiento ";
		int idmov=jdbcTemplate.queryForObject(mov,Integer.class);
		
		String costo="select costo_almacen from Producto where id_producto=? ";
		int cost=jdbcTemplate.queryForObject(costo,Integer.class,bean.getIdproducto());
		int cant=bean.getCantidad();
		int costototal=cost*cant;
		
		String detl="insert into Detalle_mov(id_mov,id_producto,cantidad,costo_total) values(?,?,?,?) ";
		jdbcTemplate.update(detl,idmov,bean.getIdproducto(),bean.getCantidad(),costototal);
				
		
		//Registro en Auditoria
		String auditoria="insert into Auditoria_reg (descripcion,id_usuario,fecha) values ('Se devolvió un producto',?,CURRENT_TIMESTAMP) ";
		jdbcTemplate.update(auditoria,bean.getIdusuario());
		
		return"Se realizó una devolucion por parte del cliente";
	}
	
}
