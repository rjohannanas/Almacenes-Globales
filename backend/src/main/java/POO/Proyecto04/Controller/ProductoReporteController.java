package POO.Proyecto04.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import POO.Proyecto04.Service.ReporteProductoMasVentaService;
import POO.Proyecto04.dto.ProductoReporteMasVentasDTO;

@RestController
@RequestMapping("/Reporte")
public class ProductoReporteController {
	
	@Autowired
	private ReporteProductoMasVentaService service;
	@GetMapping("/producto")
	public List<ProductoReporteMasVentasDTO> getReporte(){
		return service.getReporte();
	}

}
