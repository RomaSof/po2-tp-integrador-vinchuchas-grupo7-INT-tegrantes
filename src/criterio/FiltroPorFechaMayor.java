package criterio;

import java.util.Date;

public class FiltroPorFechaMayor implements FiltroPorFecha {

	@Override
	public boolean comparar(Date fecha, Date fechaAFiltrar) {
        return fecha.after(fechaAFiltrar);
    }
}
