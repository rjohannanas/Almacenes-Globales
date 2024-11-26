package POO.Proyecto04.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class ReporteFechaDTO {
	private LocalDate fechaMovimiento;
	private String tipoMovimiento;
	private String producto;
	private int cantidad;
	
	@JsonIgnore
	private LocalDate fechaInicio;
	
	@JsonIgnore
	private LocalDate fechaFin;
	

}
