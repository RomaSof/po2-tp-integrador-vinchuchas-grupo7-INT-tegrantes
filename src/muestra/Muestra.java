package muestra;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	//NEEDS ENUM TIPO OPINION
	//public tipoOpinion getEspecieVinchuca() { 
		//return this.getResultadoActual() //.getTipoOpinion(); //getEspecie();
	//}
	
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
	
	//NEEDS OPINION CLASS
	public boolean coincidenExpertos() {
		return true; //placeholder
		//this.getOpinionesDeExpertos().stream().groupingBy(o -> o.getTipo()).stream().filter(g -> g.size()>1).get(0);
		//creo??
		//ver si hay alguna opinion repetida del mismo tipo significa que hay 2 expertos que coincides
		//en la lista de expertos
	}
	
	//NEEDS OPINION CLASS
	//public Opinion getOpinionQueCoicidenExpertos(){
		//return 
	//this.getOpinionesDeExpertos().stream().groupingBy(o -> o.getTipo()).stream().filter(g -> g.size()>1);
	//}
	
	@SuppressWarnings("unchecked")
	public List<Opinion> getOpinionesDeExpertos(){
		return (List<Opinion>) this.opiniones.stream().filter(p -> p.esExperto());
	}
	
	public Optional<Opinion> getResultadoActual() {
		return this.estado.getResultadoActual(this);
	}
	
	//may be none 
	public Optional<Opinion> getOpinionMasPopular() {
		return this.opiniones.stream().max((o1, o2) -> Integer.compare(o1.getValor(), o2.getValor()));
	}
	
	
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
