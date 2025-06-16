package muestra;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ubicacion.Ubicacion;
import usuario.Usuario;
import opinion.*;

public class Muestra {
    private Usuario usuario;
	private Date fechaCreacion;
	private Ubicacion ubicacion;
	private String imagenMuestra;
	private EstadoVerificacionMuestra estado = new EstadoVerificacionMuestra();
	private List<Opinion> opiniones = new ArrayList<Opinion>();
	
	//constructor
	public Muestra(Usuario user, Date date, String image, Opinion opinion) {
		this.usuario = user;
		this.fechaCreacion = date;
		//this.ubicacion = user.getUbicacion(); -> NEED "ORGANIZACION" package
		this.imagenMuestra = image;
		this.opiniones.add(opinion);
		user.addMuestra(this);
		//duda sobre mandarle tambien la opinion al usuario
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
	
	public List<Opinion> getOpinionesDeExpertos(){
		return this.opiniones
				.stream()
				.filter(opinion -> opinion.esOpinionVerificada())
				.toList();
	}
	
	public TipoOpinion getOpinionQueCoincidenExpertos(){
		TipoOpinion opUser = this.opiniones.get(0).getTipoEspecie(); //base case?
		return 
				this.getOpinionesDeExpertos()
				.stream()
				.collect(Collectors
						.groupingBy(opinion->opinion.getTipoEspecie(), Collectors.counting()))
				.entrySet()
				.stream()
				.max(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey) //returns the value 
				.orElse(opUser); //change?
				
	}
	 
	public TipoOpinion getOpinionMasPopular() {
		TipoOpinion opUser = this.opiniones.get(0).getTipoEspecie();
		return 
			this.opiniones
				.stream()
				.collect(Collectors
						.groupingBy( opinion -> opinion.getTipoEspecie(), Collectors.counting() ) //groups by type of opinion
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
	
	public boolean coincidenExpertos() {
		return
				this.getOpinionesDeExpertos()
				.stream()
				.collect(Collectors.groupingBy(opinion -> opinion.getTipoEspecie())) //groups them by type (type, [opinions])
				.values() //collection of [opinion]
				.stream()
				.anyMatch(tipoOp -> tipoOp.size() > 1); //returns if at least 2 opinions have the same type 
	}
	
	// Una muestra siempre tiene al menos una opinion porque cuando se crea la muestra siempre tiene la opinion de quien crea la muestra.
	// Así que nunca puede tener una lista de opiniones vacia.
	public Date getFechaUltimaVotacion() {
	    List<Opinion> opiniones = this.getHistorialDeOpiniones();

	    Date fechaMasReciente = opiniones.get(0).getFechaOpinion();

	    for (Opinion opinion : opiniones) {
	        Date fechaActual = opinion.getFechaOpinion();
	        if (fechaActual.after(fechaMasReciente)) {
	            fechaMasReciente = fechaActual;
	        }
	    }

	    return fechaMasReciente;
	}

}
