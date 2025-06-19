package usuario;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import muestra.Muestra;
import opinion.Opinion;
import opinion.TipoOpinion;
import ubicacion.Ubicacion;
import usuarioEstado.EstadoUsuario;
import usuarioEstado.EstadoUsuarioBasico;

public class Usuario {

	private String nombre;
	private EstadoUsuario estadoUsuario = new EstadoUsuarioBasico();
	
	//constructor
	public Usuario(String nombre) {	
		this.nombre = nombre;
	}
	
	//getters
	public String getNombre() {
		return nombre;
	}
	
	public EstadoUsuario getEstadoUsuario() {
		return this.estadoUsuario;
	}
	//refactor
	public int getCantidadDeEnviosUltimos30Dias() {
		int cantidadDeEnvios = 0;
        return cantidadDeEnvios;
	}
	//refactor
	public int getCantidadDeRevisionesUltimos30Dias() {
		int cantidadDeRevisiones = 0;
        return cantidadDeRevisiones;
	}
	
	//setters
	public void setEstado(EstadoUsuario nuevoEstado) {
		this.estadoUsuario = nuevoEstado;
	}
	
	public void actualizarEstado() {
		this.estadoUsuario.actualizarEstado(this);
	}
	
	public boolean esExperto() {
		return estadoUsuario.esExperto();
	}
	
	
	
}