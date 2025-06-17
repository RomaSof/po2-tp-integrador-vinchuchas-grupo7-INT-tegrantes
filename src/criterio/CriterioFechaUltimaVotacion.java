package criterio;

import java.util.Date;

import muestra.Muestra;

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
	
	@Override
	public boolean cumple(Muestra muestra) {
		Date fechaUltimaVotacion = muestra.getFechaUltimaVotacion();
		return this.getFiltroFecha().comparar(fechaUltimaVotacion, this.getFechaAFiltrar());
	}
}
