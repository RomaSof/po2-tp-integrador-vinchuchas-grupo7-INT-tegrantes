package criterio;

import java.util.List;
import java.util.stream.Collectors;

import muestra.Muestra;

/*
 * Dos opciones:
 * 1) lista como parámetro del método: es útil si cada búsqueda puede trabajar con una lista diferente. (Opcion elegida)
 * 2) lista como variable de instancia: si siempre uso la misma lista para todas las búsquedas.
 * 
 */

// BuscadorMuestra existe para filtrar una lista específica con un criterio
public class BuscadorMuestra {
	private CriterioBusqueda criterio; // Criterio de búsqueda elegido para filtrar las muestras que son pasadas como parametro en buscarMuestras(...).
	
	public BuscadorMuestra(CriterioBusqueda criterio) {
		this.criterio = criterio;
	}
	
	public CriterioBusqueda getCriterioBusqueda() {
		return this.criterio;
	}
	
	public List<Muestra> buscarMuestras(List<Muestra> muestrasAFiltrar) {
		return muestrasAFiltrar.stream()
				.filter(muestra -> this.getCriterioBusqueda().cumple(muestra))
				.collect(Collectors.toList());
	}
	
	// Me permite cambiar el criterio de búsqueda
	public void setCriterioBusqueda(CriterioBusqueda nuevoCriterio) {
		this.criterio = nuevoCriterio;
	}
	
}
