package criterio;

import muestra.EstadoVerificacionMuestra;
import muestra.Muestra;

public class CriterioNivelDeVerificacion implements CriterioBusqueda{
	private EstadoVerificacionMuestra estadoVerificacion; // EstadoVerificacionMuestra clase abstracta
	
	public CriterioNivelDeVerificacion(EstadoVerificacionMuestra estadoVerificacion) {
		this.estadoVerificacion = estadoVerificacion;
	}
	
	public EstadoVerificacionMuestra getNivelVerificacion() {
		return this.estadoVerificacion;
	}

	@Override
	public boolean cumple(Muestra muestra) {
		return muestra.getEstadoMuestra().equals(this.getNivelVerificacion());
	}
}
