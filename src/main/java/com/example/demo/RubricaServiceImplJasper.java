package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
@Qualifier("rubricaService")
public class RubricaServiceImplJasper implements RubricaService {

	private String userDirectory = System.getProperty("user.dir");

	@Override
	public ArchivoRubrica generarRubrica(RubricaRequestDTO rubricaRequestDTO) {
		ArchivoRubrica archivoRubrica = new ArchivoRubrica();

		String userDirectory = System.getProperty("user.dir");
		System.out.println("userDirectory=" + userDirectory);

		String sourceFileName = null;
		sourceFileName = obtenerArchivoExterno("jasper/logos_A4.jasper").getAbsolutePath();

		JRBeanCollectionDataSource beanColDataSource = llenarDataSource(rubricaRequestDTO);

		Map<String, Object> parameters = llenarParametros(rubricaRequestDTO);

		try {
			ByteArrayOutputStream output = generarPDF(sourceFileName, beanColDataSource, parameters);
			archivoRubrica.setContenido(output.toByteArray());
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		archivoRubrica.setNombreArchivo("ejemplo.pdf");

		return archivoRubrica;
	}

	private File obtenerArchivoExterno(String relativePathFile) {
		try {
			// Si el archivo existe en working directory, se devuelve ese, sino, se devuelve
			// el de src/main/resources
//			File fileUserDirectory = ResourceUtils.getFile(userDirectory + "/" + relativePathFile);
//			if (fileUserDirectory.exists()) {
//				return fileUserDirectory;
//			}
			
			InputStream inputStream = new ClassPathResource("resources/" + relativePathFile).getInputStream();

			File file =  java.io.File.createTempFile(relativePathFile.split("\\.")[0], relativePathFile.split("\\.")[1]);
			
			FileUtils.copyInputStreamToFile(inputStream, file);
			return file;
			
//			File fileResources = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "resources/" + relativePathFile);
//			if (fileResources.exists()) {
//				return fileResources;
//			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private JRBeanCollectionDataSource llenarDataSource(RubricaRequestDTO rubricaRequestDTO) {
		List<InformacionDePagina> dataList = new ArrayList<InformacionDePagina>();
		for (int i = rubricaRequestDTO.getDesde(); i <= rubricaRequestDTO.getHasta(); i++) {
			dataList.add(new InformacionDePagina(String.valueOf(i)));
		}
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
		return beanColDataSource;
	}

	private Map<String, Object> llenarParametros(RubricaRequestDTO rubricaRequestDTO) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("P_EMPRESA", rubricaRequestDTO.getEmpresa());
		parameters.put("P_RUC", rubricaRequestDTO.getRUC());
		parameters.put("P_LIBRO", rubricaRequestDTO.getLibro());
		this.agregarParametroImagen(parameters, rubricaRequestDTO);
		return parameters;
	}

	private ByteArrayOutputStream generarPDF(String sourceFileName, JRBeanCollectionDataSource beanColDataSource,
			Map<String, Object> parameters) throws JRException {
		JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, parameters, beanColDataSource);
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
		exporter.exportReport();
		return output;
	}

	private void agregarParametroImagen(Map<String, Object> parameters, RubricaRequestDTO rubricaRequestDTO) {
		// Leer la imagen desde el archivo de properties
		String pathImagen = null;
		HashMap<String, String> mapeoImagenes = new HashMap<String, String>();
		// Lee de archivo de properties
		try {
			File propertiesFile = obtenerArchivoExterno("imagenes.txt");
			List<String> lines = FileUtils.readLines(propertiesFile, Charset.defaultCharset());
			for (String linea : lines) {
				mapeoImagenes.put(linea.split("=")[0], linea.split("=")[1]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String nombreDeArchivoDeImagen = mapeoImagenes.get(rubricaRequestDTO.getTipoRubrica());

		// TODO: Leer de application.properties
		String pathCarpetaDeImagenes = "images";
		pathImagen = obtenerArchivoExterno(pathCarpetaDeImagenes + "/" + nombreDeArchivoDeImagen).getAbsolutePath();

		// Convertir la imagen a Base64
		byte[] inFileBytes = null;
		try {
			inFileBytes = Files.readAllBytes(Paths.get(pathImagen));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] encoded = Base64.getEncoder().encode(inFileBytes);

		String encodedAsString = new String(encoded);

		parameters.put("P_LOGO", encodedAsString);
	}

}
