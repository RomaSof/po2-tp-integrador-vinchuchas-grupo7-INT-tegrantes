package criterio;

import java.util.Date;
import muestra.Muestra;

public class CriterioFechaCreacion implements CriterioBusqueda {
	private FiltroPorFecha filtroFecha;
	private Date fechaAFiltrar;
	
	public CriterioFechaCreacion(Date fechaAFiltrar, FiltroPorFecha filtroFecha) {
		this.fechaAFiltrar = fechaAFiltrar;
		this.filtroFecha = filtroFecha;
	}
	
	public FiltroPorFecha getFiltroFecha() {
		return this.filtroFecha;
	}
	
	public Date getFechaAFiltrar() {
		return this.fechaAFiltrar;
	}
	
	// Revisar de Muestra el tipo en fechaCreacion, cuando trabaje en CriterioFechaUltimaVotacion en el metodo getFechaUltimaVotacion()
	// tuve que usar Optional<Date> porque no me funcionaba con Date, entonces adapte todo con Optional<Date>
	// REVISAR!!
	// muestra.getFechaCreacion() = Date, necesito Optional<Date> buscar otras soluciones con Date.
	@Override
	public boolean cumple(Muestra muestra) {
		return this.getFiltroFecha().comparar(muestra.getFechaCreacion(), this.getFechaAFiltrar());
	}

}
