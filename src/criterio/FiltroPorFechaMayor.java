package criterio;

import java.util.Date;

public class FiltroPorFechaMayor implements FiltroPorFecha {

	@Override
    public boolean comparar(Date fechaUltimaVotacion, Date fechaAFiltrar) {
        return fechaUltimaVotacion.after(fechaAFiltrar);
    }
}
