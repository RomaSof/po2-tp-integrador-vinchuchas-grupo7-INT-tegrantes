package usuario;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;
import opinion.Opinion;
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
            // Convertir Date a LocalDate
            LocalDate fechaCreacion = muestra.getFechaCreacion()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
            if (!fechaCreacion.isBefore(hace30Dias) && 
                    !fechaCreacion.isAfter(hoy)) {
                cantidadDeEnvios++;
            }
        }
        return cantidadDeEnvios;
	}
	
	public int getCantidadDeRevisionesUltimos30Dias() {
        LocalDate hoy = LocalDate.now();
        LocalDate hace30Dias = hoy.minusDays(30);
		int cantidadDeRevisiones = 0;
        for (Opinion opinion : opiniones) {
        	// Convertir Date a LocalDate
            LocalDate fechaOpinion = opinion.getFechaOpinion()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
                
            if (!fechaOpinion.isBefore(hace30Dias) && 
                    !fechaOpinion.isAfter(hoy)) {
                    cantidadDeRevisiones++;
                }
        }
        return cantidadDeRevisiones;
	}

	public String getNombre() {
		return nombre;
 }
}