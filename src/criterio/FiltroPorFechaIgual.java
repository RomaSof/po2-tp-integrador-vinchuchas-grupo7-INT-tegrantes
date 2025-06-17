package criterio;

import java.util.Date;

public class FiltroPorFechaIgual implements FiltroPorFecha {
 
	@Override
	public boolean comparar(Date fechaUltimaVotacion, Date fechaAFiltrar) {
		return fechaUltimaVotacion.equals(fechaAFiltrar);
	}
}
