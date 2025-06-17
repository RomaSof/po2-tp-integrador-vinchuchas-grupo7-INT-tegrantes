package criterio;

import java.time.LocalDate;

import muestra.Muestra;

public class CriterioFechaUltimaVotacion implements CriterioBusqueda{
	private FiltroPorFecha filtroFecha;
	private LocalDate fechaAFiltrar;
	
	public CriterioFechaUltimaVotacion(LocalDate fechaAFiltrar, FiltroPorFecha filtroFecha) {
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
		LocalDate fechaUltimaVotacion = muestra.getFechaUltimaVotacion();
		return this.getFiltroFecha().comparar(fechaUltimaVotacion, this.getFechaAFiltrar());
	}
}
