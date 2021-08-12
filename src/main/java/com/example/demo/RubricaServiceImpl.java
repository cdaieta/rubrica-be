package com.example.demo;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("rubricaServiceMock")
public class RubricaServiceImpl implements RubricaService {

	@Override
	public ArchivoRubrica generarRubrica(RubricaRequestDTO rubricaRequestDTO) {
		ArchivoRubrica archivoRubrica = new ArchivoRubrica();
		archivoRubrica.setNombreArchivo("ejemplo.pdf");
		
		try {
			InputStream resourceAsStream = getClass().getResourceAsStream("/ejemplo.pdf");
			byte[] byteArray = IOUtils.toByteArray(resourceAsStream);
			archivoRubrica.setContenido(byteArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return archivoRubrica;
	}

}
