package POO.Proyecto04.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import POO.Proyecto04.Service.StockConsulta;
import POO.Proyecto04.dto.StockConsultaDTO;

@RestController
@RequestMapping("/Stock")
public class StockConsultaController {
	@Autowired
	private StockConsulta service;
	
	@PostMapping("/consulta")
	public  ResponseEntity<String> controlStock(@RequestBody StockConsultaDTO bean) {
		String control=service.consultaStock(bean);
		return ResponseEntity.ok(control);
	}

}
