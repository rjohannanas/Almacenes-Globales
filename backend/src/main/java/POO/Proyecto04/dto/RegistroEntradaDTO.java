package POO.Proyecto04.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class RegistroEntradaDTO {
	private int idproducto;
	private int cantidad;
	private int maxstock;
	private int minstock;
	private int idasociado;
	private int idusuario;
	

}
