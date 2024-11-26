package POO.Proyecto04.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class ReporteInventarioDTO {
	private String nombre;
	private int stock_actual;
	private int nro_mov_venta;
	private int nro_mov_compra;
	private int nro_mov_dev_cliente;
	private int nro_mov_dev_proveedor;
}
