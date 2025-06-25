package zonaCobertura;

import muestra.Muestra;

/*
 * Cualquier organización o entidad que desee recibir notificaciones cuando se agregue una nueva muestra o se valide una muestra 
 * dentro de una zona de cobertura deberá implementar esta interfaz, y definir el cómo reacción a las notificaciones de los 
 * cambios.
 */

public interface ObservadorZona {
	
	public void nuevaMuestra(ZonaDeCobertura zona, Muestra muestra); 
	
	public void muestraValidada(ZonaDeCobertura zona, Muestra muestra);

}