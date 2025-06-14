package criterio;

import java.util.Date;
import java.util.Optional;

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
	
	// Este metodo deberia estar en Muestra porque creo que no esta bien que esta clase trabaje sobre una muestra
	public Optional<Date> getFechaUltimaVotacion(Muestra muestra) {
	    return muestra.getHistorialDeOpiniones().stream()
	        .map(opinion -> opinion.getFechaOpinion())
	        .max((fecha1, fecha2) -> fecha1.compareTo(fecha2));
	}

	@Override
	public boolean cumple(Muestra muestra) {
		Optional<Date> fechaUltimaVotacion = this.getFechaUltimaVotacion(muestra);
		return this.getFiltroFecha().comparar(fechaUltimaVotacion, this.getFechaAFiltrar());
	}
}
