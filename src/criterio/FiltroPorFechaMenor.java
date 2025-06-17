package criterio;

import java.util.Date;

public abstract class FiltroPorFechaMenor implements FiltroPorFecha {

	@Override
	 public boolean comparar(Date fechaUltimaVotacion, Date fechaAFiltrar) {
        return fechaUltimaVotacion.before(fechaAFiltrar);
    }
}
