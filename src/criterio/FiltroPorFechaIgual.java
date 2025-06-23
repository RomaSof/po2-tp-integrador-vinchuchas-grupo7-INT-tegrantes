package criterio;

import java.util.Date;

public class FiltroPorFechaIgual implements FiltroPorFecha {
 
	@Override
	public boolean comparar(Date fecha, Date fechaAFiltrar) {
		return fecha.equals(fechaAFiltrar);
	}
}
