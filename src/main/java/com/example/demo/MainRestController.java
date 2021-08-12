package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {

	@Autowired
	@Qualifier("rubricaService")
	RubricaService rubricaService;

//	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@RequestMapping(value = "/rubrica", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
	public @ResponseBody byte[] generateRubrica(@RequestBody RubricaRequestDTO rubricaRequestDTO) {
		ArchivoRubrica archivoRubrica = rubricaService.generarRubrica(rubricaRequestDTO);
		System.out.println("END");
		return archivoRubrica.getContenido();
	}
}
