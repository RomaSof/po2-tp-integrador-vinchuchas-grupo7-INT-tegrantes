package criterio;

import java.util.List;

import muestra.Muestra;

public interface OperadorLogico {
	public boolean comparar(Muestra muestra, List<CriterioBusqueda> criteriosDeBusquedas);
}
