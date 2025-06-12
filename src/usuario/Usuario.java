package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;
import opinion.Opinion;
import opinion.TipoOpinion;
import usuarioEstado.EstadoUsuario;
import usuarioEstado.EstadoUsuarioBasico;
import usuarioEstado.EstadoUsuarioVerificado;

public class Usuario {

	private String nombre;
	private List<Muestra> muestrasEnviadas = new ArrayList<Muestra>();
	private List<Opinion> opiniones = new ArrayList<Opinion>();
	private EstadoUsuario estadoUsuario;
	
	public List<Opinion> getOpiniones() {
		return opiniones;
	}
	
	public List<Muestra> getMuestras(){
		return muestrasEnviadas;
	}
	
	public void addOpinion(Opinion opinion) {
		opiniones.add(opinion);
	}
	
	public void addMuestra(Muestra muestra) {
		muestrasEnviadas.add(muestra);
	}
	
	public Usuario(String nombre, boolean esVerificado) {	
		this.nombre = nombre;
		if(esVerificado) {
			this.estadoUsuario = new EstadoUsuarioVerificado();
		}else {
			this.estadoUsuario = new EstadoUsuarioBasico();
		}
	}
	
	public void actualizarEstado() {
		this.estadoUsuario.actualizarEstado(this);
	}
	
	public boolean esExperto() {
		return estadoUsuario.esExperto();
	}
	public void setEstado(EstadoUsuario nuevoEstado) {
		this.estadoUsuario = nuevoEstado;
	}
	
	public int getCantidadDeEnviosUltimos30Dias() {
        LocalDate hoy = LocalDate.now();
        LocalDate hace30Dias = hoy.minusDays(30);
		int cantidadDeEnvios = 0;
        for (Muestra muestra : muestrasEnviadas) {
            if (!muestra.getFechaCreacion().isBefore(hace30Dias) &&
                !muestra.getFechaCreacion().isAfter(hoy)) {
                cantidadDeEnvios++;
            }
        }
	}
	
	public int getCantidadDeRevisionesUltimos30Dias() {
        LocalDate hoy = LocalDate.now();
        LocalDate hace30Dias = hoy.minusDays(30);
		int cantidadDeEnvios = 0;
        for (Opinion opinion : opiniones) {
            if (!opinion.getFechaOpinion().isBefore(hace30Dias) &&
                !opinion.getFechaOpinion().isAfter(hoy)) {
                cantidadDeEnvios+= 1;
            }
        }
        return cantidadDeEnvios;
	}
	
}
