package criterio;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import muestra.Muestra;
import opinion.Opinion;

public class CriterioFechaUltimaVotacion implements CriterioBusqueda{
	private FiltroPorFecha filtroFecha;
	private Date fechaAFiltrar;
	
	public CriterioFechaUltimaVotacion(Date fechaAFiltrar, FiltroPorFecha filtroFecha) {
		this.fechaAFiltrar = fechaAFiltrar;
		this.filtroFecha = filtroFecha; 
	}
	
	public FiltroPorFecha getFiltroFecha() {
		return this.filtroFecha;
	}
	
	public Date getFechaAFiltrar() {
		return this.fechaAFiltrar;
	}
	
	// Adaptado con Date correctamente!
	// Este metodo deberia estar en Muestra porque creo que no esta bien que esta clase trabaje sobre una muestra.
	
	// El propósito del método es obtener la fecha de la opinión más reciente asociada a una muestra.
	// Primero verifica si la lista de opiniones está vacía; si lo está, retorna null.
	// Luego inicializa la variable `fechaMasReciente` con la fecha de la primera opinión.
	// Después recorre cada opinión y compara su fecha con la fecha actualmente almacenada en `fechaMasReciente`.
	// Si encuentra una fecha más reciente, la actualiza.
	// Finalmente, devuelve la fecha más actual.
	public Date getFechaUltimaVotacion(Muestra muestra) {
	    List<Opinion> opiniones = muestra.getHistorialDeOpiniones();
	    
	    if (opiniones.isEmpty()) {
	        return null;
	    }
	    
	    Date fechaMasReciente = opiniones.get(0).getFechaOpinion();
	    
	    for (Opinion opinion : opiniones) { 	
	    	Date fechaActual = opinion.getFechaOpinion();
	    	if(fechaActual.after(fechaMasReciente)) {
	    		fechaMasReciente = fechaActual;
	    	}
	    }
	    return fechaMasReciente;
	}

	@Override
	public boolean cumple(Muestra muestra) {
		Date fechaUltimaVotacion = this.getFechaUltimaVotacion(muestra);
		return this.getFiltroFecha().comparar(fechaUltimaVotacion, this.getFechaAFiltrar());
	}
}
