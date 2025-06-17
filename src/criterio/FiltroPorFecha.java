package criterio;

import java.util.Date;

public interface FiltroPorFecha {
	public boolean comparar(Date fechaUltimaVotacion, Date fechaAFiltrar);
}
