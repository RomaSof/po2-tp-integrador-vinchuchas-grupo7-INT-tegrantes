package criterio;

import java.util.List;

import muestra.Muestra;

public class OperadorAnd implements OperadorLogico{
	
	// Evalúa si la muestra cumple con todos los criterios de búsqueda especificados.
	@Override
	public boolean comparar(Muestra muestra, List<CriterioBusqueda> criteriosDeBusquedas) {
		return criteriosDeBusquedas.stream().allMatch(criterio -> criterio.cumple(muestra));
	}

}
