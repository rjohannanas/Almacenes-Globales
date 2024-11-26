package POO.Proyecto04.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class ProductoDTO {
	private int idproducto;
	private String nombre;
	private String descripcion;
	private int idcategoria;
	private int precio;
	private int costoalmacen;

}
