package es.schooleando.cromos;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Equipos extends HashMap<Integer,String> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Equipos() {
		this.put(1, "Godella C.F.");
	}
}
