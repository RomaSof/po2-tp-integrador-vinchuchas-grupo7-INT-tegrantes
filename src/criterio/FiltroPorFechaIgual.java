package criterio;

import java.util.Date;
import java.util.Optional;

public class FiltroPorFechaIgual implements FiltroPorFecha {

	@Override
	public boolean comparar(Optional<Date> fechaUltimaVotacion, Date fechaAFiltrar) {
		// Como fechaUltimaVotacion es un Optional, uso map para aplicar la comparación solo si tiene valor.
	    // Si hay una fecha, se compara con fechaAFiltrar (devuelve true si es igual).
	    // Si no hay fecha (Optional vacío), se devuelve false.
	    return fechaUltimaVotacion
	            .map(fecha -> fecha.equals(fechaAFiltrar))
	            .orElse(false);
	}
}
