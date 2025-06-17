package muestra;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ubicacion.Ubicacion;
import usuario.Usuario;
import opinion.*;

public class Muestra {
    private Usuario usuario;
	private LocalDate fechaCreacion;
	private Ubicacion ubicacion;
	private String imagenMuestra;
	private TipoOpinion opinionInicial;
	private EstadoVerificacionMuestra estado = new EstadoVerificacionMuestra();
	private List<Opinion> opiniones = new ArrayList<Opinion>();
	
	//constructor
	public Muestra(Usuario user, LocalDate date, Ubicacion ubicacion, String image, TipoOpinion opinioninicial) {
		this.usuario = user;
		this.fechaCreacion = date;
		this.ubicacion = ubicacion; 
		this.imagenMuestra = image;
		this.opinionInicial = opinioninicial;
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
	
	public LocalDate getFechaCreacion() {
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
	
	//filtra y devuelve solo las opiniones hechas por expertos
	public List<Opinion> getOpinionesDeExpertos(){
		return this.opiniones
				.stream()
				.filter(opinion -> opinion.esOpinionVerificada())
				.toList();
	}
	
	//devuelve la opinion entre la de los expertos que más se repite
	//si llegase a haber empate devuelve cualquiera entre las empatadas y si no hay experto devuelve la opinion del usuario que publicó la muestra
	public TipoOpinion getOpinionQueCoincidenExpertos(){
		TipoOpinion opUser = this.opiniones.get(0).getTipoEspecie(); //caso base, la lista de opiniones nunca esta vacia
		return 
				this.getOpinionesDeExpertos() //filtra solo la de los expertos
				.stream()
				.collect(Collectors
						.groupingBy(opinion->opinion.getTipoEspecie(), Collectors.counting())) //los grupa por tipo de opinion 
				.entrySet()
				.stream() //los convierte en un set de tipo de opinion y las veces que se repiten
				.max(Map.Entry.comparingByValue()) //busca el que más se repite
				.map(Map.Entry::getKey) //los convierte en map otra vez y devuelve el value, la opinion
				.orElse(opUser); //si no hay ninguna devuelve la opinion del usuario que subió la muestra
			//pero es un caso que nunca va a sucedes porque este método solo se llama cuando al menos 2 expertos opinan
				
	}
	 
	//devuelve la opinion que más se repite entre todas las opiniones de la muestra
	//si no hay opiniones que se repiten devuelve una cualquiera
	public TipoOpinion getOpinionMasPopular() {
		TipoOpinion opUser = this.opiniones.get(0).getTipoEspecie();
		return 
			this.opiniones
				.stream()
				.collect(Collectors
						.groupingBy( opinion -> opinion.getTipoEspecie(), Collectors.counting() ) //los agrupa según el tipo de opinion
						)
				.entrySet() //set (Value, appearances) los pone en un set con el tipo y la cantidad de veces que se repite
				.stream()
				.max(Map.Entry.comparingByValue()) //saca el más repetido 
				.map(Map.Entry::getKey) //devuelve el tipo
				.orElse(opUser); //si no hay votos, entonces devuelve el del usuario que postea la muestra
		//este caso nunca va a pasar porque siempre por lo menos hay 1 voto que es la del usuario que publica dicha muestra
					
	}
	
	// Una muestra siempre tiene al menos una opinion porque cuando se crea la muestra siempre tiene la opinion de quien crea la muestra.
	// Así que nunca puede tener una lista de opiniones vacia.
	public LocalDate getFechaUltimaVotacion() {

		List<Opinion> opiniones = this.getHistorialDeOpiniones();
		LocalDate fechaMasReciente = opiniones.get(0).getFechaOpinion();
	    
	    for (Opinion opinion : opiniones) {
	    	LocalDate fechaActual = opinion.getFechaOpinion();
	        if (fechaActual.isAfter(fechaMasReciente)) {
	            fechaMasReciente = fechaActual;
	        }
	    }
	    
	    return fechaMasReciente;
	}
	
	//setters
	protected void setEstadoMuestra(EstadoVerificacionMuestra estado) {
		this.estado = estado;
	}
	
	
	//methods
	
	//revisa todos los usuarios de las opiniones que tiene la muestra para ver si el usuario que esta queriendo opinar ya opinó
	public boolean puedeOpinar(Usuario usuario) {
	    for (Opinion opinion : this.getHistorialDeOpiniones()) {
	        if (opinion.getUsuario().equals(usuario)) {
	            return true;
	        }
	    }
	    return false;
	}
		
	public void agregarOpinion(Opinion opinion) {
			this.estado.agregarOpinion(this, opinion);
	} 
	
	protected void addOpinion(Opinion opinion) {
		this.opiniones.add(opinion);
	}
	
	public boolean esVerificada(){
		return estado.esVerificada();
	}
	
	//indica si hay al menos 2 expertos que coincidan en una opinion,
	//que digan que es el mismo tipo en su opinion
	public boolean coincidenExpertos() {
		return
				this.getOpinionesDeExpertos() //trabaja con solo las opiniones de los expertos
				.stream()
				.collect(Collectors.groupingBy(opinion -> opinion.getTipoEspecie())) //los agrupo por el tipo de opinion
				.values() //collection of [opinion]
				.stream()
				.anyMatch(tipoOp -> tipoOp.size() > 1); //indica si hay al menos 2 opiniones de que son del mismo tipo 
	}
	

}
