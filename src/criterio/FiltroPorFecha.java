package criterio;

import java.util.Date;
import java.util.Optional;

public interface FiltroPorFecha {
	public boolean comparar(Optional<Date> fechaUltimaVotacion, Date fechaAFiltrar);
}
