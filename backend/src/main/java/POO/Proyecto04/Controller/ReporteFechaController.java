package POO.Proyecto04.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import POO.Proyecto04.Service.ReporteFechaService;
import POO.Proyecto04.dto.ReporteFechaDTO;

@RestController
@RequestMapping("/Reporte")
public class ReporteFechaController {

    @Autowired
    private ReporteFechaService service;

    @PostMapping("/fecha")
    public List<ReporteFechaDTO> getReportePorFecha(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFin) {
    	
        return service.getReportFecha(fechaInicio, fechaFin);
    }
}
