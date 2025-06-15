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
		/*
		 * Antes estaba comparando los estados con "==", pero eso me traía problemas.
		 * Lo que hace "==" es comparar si los dos objetos son exactamente la misma instancia en memoria,
		 * o sea, si apuntan al mismo lugar.
		 *
		 * Lo que en realidad quiero es comparar si ambos objetos representan el mismo estado lógico.
		 * Para eso tengo que usar "equals()", que compara el contenido o el tipo del objeto,
		 * según cómo esté definido.
		 *
		 * Por eso cambié la línea y ahora uso:
		 */
		return muestra.getEstadoMuestra().equals(this.getNivelVerificacion());
	}
}
