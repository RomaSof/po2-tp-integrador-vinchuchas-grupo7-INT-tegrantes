package criterio;

import muestra.EstadoMuestraVerificada;
import muestra.Muestra;

/*
 * La muestra tiene distintos estados según su nivel de verificacion, necesito obtener el estado a partir de algo como 
 * getNombreEstado() return "Verificada" o "En proceso"
 * 
 * 
 */
public class CriterioNivelDeVerificacion implements CriterioBusqueda{
	private String estadoVerificacion; 
	
	public CriterioNivelDeVerificacion(String estadoVerificacion) {
		this.estadoVerificacion = estadoVerificacion;
	}
	
	public String getEstadoMuestra() {
		return this.estadoVerificacion;
	}

	@Override
	public boolean cumple(Muestra muestra) {
	// cada estado debe tener un getNombre() para poder filtrar el criterio de verificacion correctamente.
		return (muestra.getEstadoMuestra().getNombreEstado() == this.getEstadoMuestra()); 
	}
}
