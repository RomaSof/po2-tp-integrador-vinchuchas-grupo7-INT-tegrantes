package criterio;

import java.time.LocalDate;
import muestra.Muestra;

public class CriterioFechaCreacion implements CriterioBusqueda {
	private FiltroPorFecha filtroFecha;
	private LocalDate fechaAFiltrar;
	
	public CriterioFechaCreacion(LocalDate fechaAFiltrar, FiltroPorFecha filtroFecha) {
		this.fechaAFiltrar = fechaAFiltrar;
		this.filtroFecha = filtroFecha;
	}
	
	public FiltroPorFecha getFiltroFecha() {
		return this.filtroFecha;
	}
	
	public LocalDate getFechaAFiltrar() {
		return this.fechaAFiltrar;
	}
	
	@Override
	public boolean cumple(Muestra muestra) {
		return this.getFiltroFecha().comparar(muestra.getFechaCreacion(), this.getFechaAFiltrar());
	}

}
