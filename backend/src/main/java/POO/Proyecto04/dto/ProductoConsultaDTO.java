package POO.Proyecto04.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class ProductoConsultaDTO {
	@JsonIgnore
	private int idproducto;
	
	private String nombre;
	private String descripcion;
	private String categoria;
	private int precio;
	private int costoalmacen;
	private int stock;

}
