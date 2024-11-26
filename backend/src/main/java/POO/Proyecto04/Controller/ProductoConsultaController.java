package POO.Proyecto04.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import POO.Proyecto04.Service.ProductoConsultaService;
import POO.Proyecto04.dto.ProductoConsultaDTO;

@RestController
@RequestMapping("/Producto")
public class ProductoConsultaController {
	@Autowired
	private ProductoConsultaService service;
	
	@PostMapping("/consultaid")
	public ResponseEntity<?> getconsultaservice(@RequestParam int id) {
		try {
            ProductoConsultaDTO bean = service.consultaID(id);
            return ResponseEntity.ok(bean);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
	}
	
	@GetMapping("/consultalista")
	public ResponseEntity<List<ProductoConsultaDTO>> obtenerlista(){
		List<ProductoConsultaDTO> consultalista=service.getConsultaLista();
		return ResponseEntity.ok(consultalista);
	}



}
