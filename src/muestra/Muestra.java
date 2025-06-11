package muestra;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ubicacion.Ubicacion;
import usuario.Usuario;

public class Muestra {
    private Usuario usuario;
	private Date fechaCreacion;
	private Ubicacion ubicacion;
	private String imagenMuestra;
	private EstadoVerificacionMuestra estado;
	private List<Opinion> opiniones = new ArrayList<Opinion>();
	
	
	//constructor
	public Muestra(Usuario user, Date date, String image, Opinion opinion) {
		this.usuario = user;
		this.fechaCreacion = date;
		//this.ubicacion = user.getUbicacion(); -> NEED "ORGANIZACION" package
		this.imagenMuestra = image;
		this.opiniones.add(opinion);
	}
	
	//getters
	
	//NEEDS ENUM SPECIES
	//public void getEspecieVinchuca() {this.getResultadoActual().getEspecie();}
	
	public String getFotoMuestra() {
		return this.imagenMuestra;
	}
	
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}
	
	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}
	
	public Usuario getRecolectorMuestra() {
		return this.usuario;
	}
	
	public EstadoVerificacionMuestra getEstadoMuestra() {
		return this.estado;
	}
	
	public List<Opinion> getHistorialDeOpiniones() {
		return this.opiniones;
	}
	
	public boolean coincidenExpertos() {
		return true; //placeholder
		//ver si hay alguna opinion repetida del mismo tipo significa que hay 2 expertos que coincides
		//en la lista de expertos
	}
	
	//public Opinion getOpinionQueCoicidenExpertos(){}
	
	//NEEDS OPINION CLASS
	//public List<Opinion> getOpinionesDeExpertos(){
		//return this.opiniones.stream().filter(p -> o.esExperto())
	//}
	
	//NEEDS OPINION CLASS
	//public Opinion getResultadoActual() {
	//	return this.estado.getResultadoActual(this);
	//}
	
	//public Opinion getOpinionMasPopular() {
		//return this.opiniones.stream().max((o1, o2) -> Integer.compare(o1.valor(), o2.valor()));
	//}
	
	//NEEDS OPINION CLASS
	//public Opinion getEspecie() {return this.getResultadoActual.getEspecie;}
	
	
	//setters
	public void setEstadoMuestra(EstadoVerificacionMuestra estado) {
		this.estado = estado;
	}
	
	
	//methods
	public void agregarOpinion(Opinion opinion) {
		this.estado.agregarOpinion(this, opinion);
	} 
	
	public void addOpinion(Opinion opinion) {
		this.opiniones.add(opinion);
		//this.actualizarEstado();
	}
	
	public boolean esVerificada(){
		return estado.esVerificada();
	}
	
	/*	
	 
	+ getFechaUltimaVotacion(): Date  -> para qué?
	
	--NEED LOCATIONS "UBICACIONES"
	+ ubicacionesDeMuestrasCercanas(List<Muestra>, double): List<Muestra>
	
	*/
}
