package POO.Proyecto04.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import POO.Proyecto04.Service.ProductoService;
import POO.Proyecto04.dto.ProductoDTO;

@RestController
@RequestMapping("/Producto")
public class ProductoController {
	@Autowired
	private ProductoService service;
	@PostMapping("/agregar")
	public String agregar(@RequestBody ProductoDTO bean) {
		return service.agregarProducto(bean);
	}
	
	@PostMapping("/actualizar")
	public String actualizar(@RequestBody ProductoDTO bean) {
		return service.actualizarProdcuto(bean);
	}
	
	@PostMapping("/eliminar")
	public String eliminar(@RequestBody ProductoDTO bean) {
		return service.eliminarProdcuto(bean);
	}

}
