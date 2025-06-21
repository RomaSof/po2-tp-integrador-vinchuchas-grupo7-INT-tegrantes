package criterio;

import java.util.Date;

public class FiltroPorFechaMenor implements FiltroPorFecha {

	@Override
	 public boolean comparar(Date fecha, Date fechaAFiltrar) {
        return fecha.before(fechaAFiltrar);
    }
}
