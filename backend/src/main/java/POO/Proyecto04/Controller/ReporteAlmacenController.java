package POO.Proyecto04.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import POO.Proyecto04.Service.ReporteAlmacenService;
import POO.Proyecto04.dto.ReporteAlmacenDTO;

@RestController
@RequestMapping("/Almacen")
public class ReporteAlmacenController {
	@Autowired
	private ReporteAlmacenService service;
	@GetMapping("/reporte")
	public List<ReporteAlmacenDTO> obtenerReporteAlmacen(){
		return service.getReporteAlmacen();
	}
	
}
