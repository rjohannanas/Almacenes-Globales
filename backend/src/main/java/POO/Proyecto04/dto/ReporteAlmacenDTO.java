package POO.Proyecto04.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class ReporteAlmacenDTO {
	private String nombre;
	private int stock_actual;
	private int costo_almacen;

}
