package zonaCobertura;

import muestra.Muestra;

public interface ObservadorZona {
	
	public void nuevaMuestra(ZonaDeCobertura zona, Muestra muestra); 
	
	public void muestraValidada(ZonaDeCobertura zona, Muestra muestra);

}