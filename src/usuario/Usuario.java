package usuario;

import java.util.Date;
import java.util.List;

import muestra.Muestra;
import opinion.Opinion;
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

    public int getCantidadDeEnviosUltimos30Dias(List<Muestra> muestrasEnSistema) {
        // Calcular la fecha límite (30 días atrás)
        Date fechaActual = new Date();
        long milisegundos30Dias = 30L * 24 * 60 * 60 * 1000; // 30 días en milisegundos
        Date fechaLimite = new Date(fechaActual.getTime() - milisegundos30Dias);
        
        int cantidadDeEnvios = 0;
        
        for (Muestra muestra : muestrasEnSistema) {
            if (muestra.getRecolectorMuestra().equals(this) && 
                muestra.getFechaCreacion().after(fechaLimite)) {
                cantidadDeEnvios++;
            }
        }
        return cantidadDeEnvios;
    }
    
    public int getCantidadDeRevisionesUltimos30Dias(List<Muestra> muestrasEnSistema) {
        // Calcular la fecha límite (30 días atrás)
        Date fechaActual = new Date();
        long milisegundos30Dias = 30L * 24 * 60 * 60 * 1000; // 30 días en milisegundos
        Date fechaLimite = new Date(fechaActual.getTime() - milisegundos30Dias);
        
        int cantidadDeRevisiones = 0;
        
        for (Muestra muestra : muestrasEnSistema) {
            for (Opinion opinion : muestra.getHistorialDeOpiniones()) {
                if (opinion.getUsuario().equals(this) && 
                    opinion.getFechaOpinion().after(fechaLimite)) {
                    cantidadDeRevisiones++;
                }
            }
        }
        return cantidadDeRevisiones;
    }
	
	//setters
	public void setEstado(EstadoUsuario nuevoEstado) {
		this.estadoUsuario = nuevoEstado;
	}
	
    public void actualizarEstado(List<Muestra> muestrasEnSistema) {
        this.estadoUsuario.actualizarEstado(this, muestrasEnSistema);
    }
	
	
	public boolean esExperto() {
		return estadoUsuario.esExperto();
	}
	
	
	
}