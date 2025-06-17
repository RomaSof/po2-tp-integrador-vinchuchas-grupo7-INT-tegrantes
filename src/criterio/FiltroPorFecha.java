package criterio;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public interface FiltroPorFecha {
	public boolean comparar(LocalDate fechaUltimaVotacion, LocalDate localDate);
}
