package POO.Proyecto04.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import POO.Proyecto04.Service.StockService;
import POO.Proyecto04.dto.StockDTO;

@RestController
@RequestMapping("/Stock")
public class StockController {
	
	@Autowired
	private StockService service;
	
	@PostMapping("/actualizar")
	public ResponseEntity<String> actualizarStock(@RequestBody StockDTO bean) {
		String resultado =service.actualizarMinMaxStock(bean);
        return ResponseEntity.ok(resultado);
       
	}

}
