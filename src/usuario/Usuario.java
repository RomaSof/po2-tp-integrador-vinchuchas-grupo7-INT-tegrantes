package usuario;

import java.time.LocalDate;
//import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;
import opinion.Opinion;
import opinion.TipoOpinion;
import ubicacion.Ubicacion;
import usuarioEstado.EstadoUsuario;
import usuarioEstado.EstadoUsuarioBasico;

public class Usuario {

	private String nombre; 
	private List<Muestra> muestrasEnviadas = new ArrayList<Muestra>();
	private List<Opinion> opinionesEnviadas = new ArrayList<Opinion>();
	private EstadoUsuario estadoUsuario = new EstadoUsuarioBasico();
	
	//constructor
	public Usuario(String nombre) {	
		this.nombre = nombre;
	}
	
	//getters
	public String getNombre() {
		return nombre;
	}
	
	public List<Opinion> getOpiniones() {
		return opinionesEnviadas;
	}
	
	public List<Muestra> getMuestras(){
		return muestrasEnviadas;
	}
	
	public EstadoUsuario getEstadoUsuario() {
		return this.estadoUsuario;
	}
	
	public int getCantidadDeEnviosUltimos30Dias() {
        LocalDate hoy = LocalDate.now();
        LocalDate hace30Dias = hoy.minusDays(30);
		int cantidadDeEnvios = 0;
		for (Muestra muestra : muestrasEnviadas) {
            // Convertir Date a LocalDate
            LocalDate fechaCreacion = muestra.getFechaCreacion();
                //.toInstant() -> ya no son necesarios porque todo es LocalDate
                //.atZone(ZoneId.systemDefault())
                //.toLocalDate();
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
        for (Opinion opinion : opinionesEnviadas) {
        	// Convertir Date a LocalDate
            LocalDate fechaOpinion = opinion.getFechaOpinion();
                    //.toInstant() -> ya no son necesarios porque todo es LocalDate
                    //.atZone(ZoneId.systemDefault())
                    //.toLocalDate();
                
            if (!fechaOpinion.isBefore(hace30Dias) && 
                    !fechaOpinion.isAfter(hoy)) {
                    cantidadDeRevisiones++;
                }
        }
        return cantidadDeRevisiones;
	}
	
	//setters
	protected void setEstado(EstadoUsuario nuevoEstado) {
		this.estadoUsuario = nuevoEstado;
	}
	
	//methods
	public void enviarMuestra(Ubicacion ubicacion, String imagen, TipoOpinion tipo) { //no sé a qué se le envia la muestra
		LocalDate hoy = LocalDate.now();
		
		Muestra muestra = new Muestra(this, hoy, ubicacion, imagen, tipo);
		
		this.addMuestra(muestra);
	} 
	
	public void opinar(Muestra muestra, TipoOpinion tipo) {
		LocalDate hoy = LocalDate.now();
		Opinion opinion = new Opinion(this, tipo, hoy);
		muestra.agregarOpinion(opinion);
	}
	
	
	public void addOpinion(Opinion opinion) { 
		opinionesEnviadas.add(opinion);
	}
	
	protected void addMuestra(Muestra muestra) {
		muestrasEnviadas.add(muestra);
	}
	
	protected void actualizarEstado() {
		this.estadoUsuario.actualizarEstado(this);
	}
	
	public boolean esExperto() {
		return estadoUsuario.esExperto();
	}
	
	
	
}