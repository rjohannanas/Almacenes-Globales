package POO.Proyecto04.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import POO.Proyecto04.dto.ProductoDTO;

@Service
public class ProductoService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional
	public String agregarProducto(ProductoDTO bean) {
		//Verificar que el producto no exista
		String existsql="SELECT COUNT(1) CONT FROM Producto WHERE nombre=? ";
		int cont=jdbcTemplate.queryForObject(existsql,Integer.class,bean.getNombre());
		if(cont==1) {
			return "El producto "+bean.getNombre()+" ya existe";
		}
		
		//Existencia de la categoria
		String categoria="select count(1) cont from Categoria where id=? ";
		int contctg=jdbcTemplate.queryForObject(categoria,Integer.class, bean.getIdcategoria());
		if(contctg!=1) {
			return "La categoria con id "+bean.getIdcategoria()+" no existe.";
		}
		
		// Agregar el producto y recuperar el ID generado
        String sqlproducto = "INSERT INTO Producto (nombre, descripcion, id_categoria, precio, costo_almacen) ";
        sqlproducto += "OUTPUT INSERTED.id_producto ";
        sqlproducto += "VALUES (?, ?, ?, ?, ?)";
        Integer idProducto = jdbcTemplate.queryForObject(sqlproducto, Integer.class, bean.getNombre(), bean.getDescripcion(), bean.getIdcategoria(), bean.getPrecio(), bean.getCostoalmacen());

        return "Se agregó el producto correctamente con ID " + idProducto;
	}
	
	public String actualizarProdcuto(ProductoDTO bean) {
		//Verificar que el idproducto exista
		String contador="select count(1) from Producto where id_producto=? ";
		int cont=jdbcTemplate.queryForObject(contador,Integer.class ,bean.getIdproducto());
		if(cont!=1) {
			return "El producto no exsite";
		}
		
		//Actualizando el Prodcuto
		String sql="UPDATE Producto SET nombre = ?,  descripcion = ?, id_categoria = ?, ";
		sql+="precio = ?, costo_almacen = ? WHERE id_producto = ? ";
		jdbcTemplate.update(sql,bean.getNombre(),bean.getDescripcion(),bean.getIdcategoria(),bean.getPrecio(),bean.getCostoalmacen(),bean.getIdproducto());
		
		return "Actualizacion correcta";
	}
	
	public String eliminarProdcuto(ProductoDTO bean) {
		//Verificar que el idproducto exista
		String contador="select count(1) from Producto where id_producto=? ";
		int cont=jdbcTemplate.queryForObject(contador,Integer.class ,bean.getIdproducto());
		if(cont!=1) {
			return "El producto no exsite";
		}
		
		//Eliminando Producto
		String eliminar="delete from Producto where id_producto=? ";
		jdbcTemplate.update(eliminar,bean.getIdproducto());
		
		return "Se elimino el prodcucto";
	}

}
