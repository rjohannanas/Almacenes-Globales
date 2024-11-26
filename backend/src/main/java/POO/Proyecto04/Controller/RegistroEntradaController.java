package POO.Proyecto04.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import POO.Proyecto04.Service.RegistroEntradaService;
import POO.Proyecto04.dto.RegistroEntradaDTO;

@RestController
@RequestMapping("/Entrada")
public class RegistroEntradaController {
	@Autowired
	private RegistroEntradaService service;
	@PostMapping("/compra")
	public ResponseEntity<String> entrada1(@RequestBody RegistroEntradaDTO bean) {
		String entrada=service.compraproducto(bean);
		return ResponseEntity.ok(entrada);
	}
	@PostMapping("/devolucioncliente")
	public ResponseEntity<String> entrada2(@RequestBody RegistroEntradaDTO bean) {
		String entrada=service.devolucionCliente(bean);
		return ResponseEntity.ok(entrada);
	}
	
}
