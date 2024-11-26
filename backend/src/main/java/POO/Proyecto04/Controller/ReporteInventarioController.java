package POO.Proyecto04.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import POO.Proyecto04.Service.ReporteInventarioService;
import POO.Proyecto04.dto.ReporteInventarioDTO;

@RestController
@RequestMapping("/Reporte")
public class ReporteInventarioController {
	
	@Autowired
	private ReporteInventarioService service;
	@GetMapping("/inventario")
	public List<ReporteInventarioDTO> obtenerReporte(){
		return service.getReporteInventario();
	}

}
