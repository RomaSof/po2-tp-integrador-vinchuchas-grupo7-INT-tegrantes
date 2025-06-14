package criterio;

import java.util.List;

import muestra.Muestra;

public class OperadorOr implements OperadorLogico {
	
	// Evalúa si la muestra cumple con al menos uno de los criterios de búsqueda especificados.
	@Override
	public boolean comparar(Muestra muestra, List<CriterioBusqueda> criteriosDeBusquedas) {
		return criteriosDeBusquedas.stream().anyMatch(criterio -> criterio.cumple(muestra));
	}


}
