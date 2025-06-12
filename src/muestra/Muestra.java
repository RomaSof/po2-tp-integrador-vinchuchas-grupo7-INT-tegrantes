package muestra;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
	public String getEspecie() { 
		return this.getResultadoActual().getEspecie();
	}
	
	public String getFotoMuestra() {
		return this.imagenMuestra;
	}
	
	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}
	
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}
	
	public Usuario getRecolectorMuestra() {
		return this.usuario;
	}
	
	public TipoOpinion getResultadoActual() { 
		return this.estado.getResultadoActual(this);
	}
	
	public EstadoVerificacionMuestra getEstadoMuestra() {
		return this.estado;
	}
	
	public List<Opinion> getHistorialDeOpiniones() {
		return this.opiniones;
	}
	
	public boolean coincidenExpertos() {
		return
				this.getOpinionesDeExpertos()
				.stream()
				.collect(Collectors.groupingBy(opinion -> opinion.getTipoOpinion())) //groups them by type (type, [opinions])
				.values() //collection of [opinion]
				.stream()
				.anyMatch(tipoOp -> tipoOp.size() > 1); //returns if at least 2 opinions have the same type 
				
				/*.entrySet() //(type, appearances)
				.stream()
				.filter(tipoOp -> tipoOp.getValue().size() > 1) //filters the ones with no match
				.toList()
				.isEmpty(); //sees if none match -> no one agrees*/
	}
	
	//NEEDS OPINION CLASS
	//public Opinion getOpinionQueCoicidenExpertos(){
		//return 
	//this.getOpinionesDeExpertos().stream().groupingBy(o -> o.getTipo()).stream().filter(g -> g.size()>1);
	//}
	
	public List<Opinion> getOpinionesDeExpertos(){
		return this.opiniones
				.stream()
				.filter(opinion -> opinion.esExperto())
				.toList();
	}
	 
	public TipoOpinion getOpinionMasPopular() {
		TipoOpinion opUser = this.opiniones.get(0).getTipoOpinion();
		return 
			this.opiniones
				.stream()
				.collect(Collectors
						.groupingBy( opinion -> opinion.getTipoOpinion(), Collectors.counting() ) //groups by type of opinion
						)
				.entrySet() //set (Value, appearances)
				.stream()
				.max(Map.Entry.comparingByValue()) //finds the most voted one 
				.map(Map.Entry::getKey) //returns the value 
				.orElse(opUser); //if there're no votes returns the users vote (but it wont happen, there's always at least 1 vote)
					
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

}
