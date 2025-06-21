package criterio;

import java.util.List;
import java.util.stream.Collectors;

import muestra.Muestra;

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
