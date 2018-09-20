package es.schooleando.cromos;

import java.beans.Transient;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class CromoForm {
	private String nombre;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date nacimiento;
	
	private Integer peso;
	
	private Integer altura;
	
	private String imagen_url;
	
	// this field does not persist!
	private MultipartFile imagen;

	public Date getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	public MultipartFile getImagen() {
		return imagen;
	}

	public void setImagen(MultipartFile imagen) throws IOException {
		this.imagen = imagen;
		
		byte[] bytes = imagen.getBytes();
        Path path = Paths.get("/tmp/upload/" + imagen.getOriginalFilename());
        Files.write(path, bytes);
        
		// TODO: save image and initialize imagen_url!
        this.setImagen_url("uploaded/" + imagen.getOriginalFilename());
	}

	public String getImagen_url() {
		return imagen_url;
	}

	public void setImagen_url(String imagen_url) {
		this.imagen_url = imagen_url;
	}
}
