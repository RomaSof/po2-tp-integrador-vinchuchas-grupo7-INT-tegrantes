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
	
	@Override
	public boolean cumple(Muestra muestra) {
		return this.getFiltroFecha().comparar(muestra.getFechaCreacion(), this.getFechaAFiltrar());
	}

}
