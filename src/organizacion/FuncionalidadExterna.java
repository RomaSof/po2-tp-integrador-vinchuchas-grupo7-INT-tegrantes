package organizacion;

import muestra.Muestra;
import zonaCobertura.ZonaDeCobertura;

public interface FuncionalidadExterna {
	public void nuevoEvento(Organizacion organizacion, ZonaDeCobertura zona, Muestra muestra);
}
