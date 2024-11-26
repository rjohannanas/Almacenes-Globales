package POO.Proyecto04.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import POO.Proyecto04.dto.ProductoConsultaDTO;

@Service
public class ProductoConsultaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProductoConsultaDTO consultaID(int idproducto) {
        // Verificar que el idproducto exista
        String contador = "select count(1) from Producto where id_producto=?";
        int cont = jdbcTemplate.queryForObject(contador, Integer.class, idproducto);
        if (cont != 1) {
            throw new IllegalArgumentException("El producto con ID " + idproducto + " no existe");
        }

        // Consulta para obtener detalles del producto
        String sql = "select P.nombre, P.descripcion, c.descripcion as categoria, "
                   + "p.precio, p.costo_almacen, i.existencias "
                   + "from Producto p "
                   + "join Categoria c on p.id_categoria = c.id "
                   + "join Inventario i on p.id_producto = i.id_producto "
                   + "where p.id_producto = ?";

        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> {
                    ProductoConsultaDTO producto = new ProductoConsultaDTO();
                    producto.setNombre(rs.getString("nombre"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setCategoria(rs.getString("categoria"));
                    producto.setPrecio(rs.getInt("precio"));
                    producto.setCostoalmacen(rs.getInt("costo_almacen"));
                    producto.setStock(rs.getInt("existencias"));
                    return producto;
                },
                idproducto
        );
    }

    public List<ProductoConsultaDTO> getConsultaLista() {
        // Consulta para obtener todos los productos
        String sql = "select P.nombre, P.descripcion, c.descripcion as categoria, "
                   + "p.precio, p.costo_almacen, i.existencias "
                   + "from Producto p "
                   + "join Categoria c on p.id_categoria = c.id "
                   + "join Inventario i on p.id_producto = i.id_producto";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProductoConsultaDTO dto = new ProductoConsultaDTO();
            dto.setNombre(rs.getString("nombre"));
            dto.setDescripcion(rs.getString("descripcion"));
            dto.setCategoria(rs.getString("categoria"));
            dto.setPrecio(rs.getInt("precio"));
            dto.setCostoalmacen(rs.getInt("costo_almacen"));
            dto.setStock(rs.getInt("existencias"));
            return dto;
        });
    }
}
