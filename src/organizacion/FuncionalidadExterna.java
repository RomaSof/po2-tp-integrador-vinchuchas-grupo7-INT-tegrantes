package organizacion;

import muestra.Muestra;

/*
 * 
 * Cada plugin externo que una organización o cualquier otra entidad quiera utilizar debe de implementar 
 * esta interfaz y debe de definir algún comportamiento para reaccionar ante un evento;
 *  específicamente cambios en las muestras en el caso de las organizaciones,
 *  
 *   ejemplos:
 *   por ejemplo una funcionalidad externa que al recibir la noticia de que una muestra fue confirmada mande mails
 *   o una funcionalidad externa que al recibir la noticia de que una muestra nueva fue añadida genera alarmas o alertas
 * 
 *
 * */
import zonaCobertura.ZonaDeCobertura;

public interface FuncionalidadExterna {
	public void nuevoEvento(Organizacion organizacion, ZonaDeCobertura zona, Muestra muestra);
}
