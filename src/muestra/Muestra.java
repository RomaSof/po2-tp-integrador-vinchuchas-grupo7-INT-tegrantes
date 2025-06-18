package muestra;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import avisoOrganizaciones.ObservadorMuestra;
import ubicacion.Ubicacion;
import usuario.Usuario;
import opinion.*;

public class Muestra {
    private Usuario usuario;
	private Date fechaCreacion;
	private Ubicacion ubicacion;
	private String imagenMuestra;
	private TipoOpinion opinionInicial;
	private EstadoVerificacionMuestra estado = new EstadoVerificacionMuestra();
	private List<Opinion> opiniones = new ArrayList<Opinion>();
	
	//constructor
	public Muestra(Usuario user, Date date, Ubicacion ubicacion, String image, TipoOpinion opinioninicial) {
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
	
	//filtra y devuelve solo las opiniones hechas por expertos
	public List<Opinion> getOpinionesDeExpertos(){
		return this.opiniones
				.stream()
				.filter(opinion -> opinion.esOpinionVerificada())
				.toList();
	}
	
	//devuelve la opinion entre la de los expertos que más se repite
	//si llegase a haber empate devuelve cualquiera entre las empatadas
	//si no hay experto devuelve la opinion del usuario que publicó la muestra, 
	//pero es un caso que nunca va a sucedes porque este método solo se llama cuando al menos 2 expertos opinan
	public TipoOpinion getOpinionQueCoincidenExpertos(){
		return 
				this.getOpinionesDeExpertos() //filtra solo la de los expertos
				.stream()
				.collect(Collectors
						.groupingBy(opinion->opinion.getTipoEspecie(), Collectors.counting())) //los grupa por tipo de opinion 
				.entrySet()
				.stream() //los convierte en un set de tipo de opinion y las veces que se repiten
				.max(Map.Entry.comparingByValue()) //busca el que más se repite
				.map(Map.Entry::getKey) //los convierte en map otra vez y devuelve el value, la opinion
				.orElse(this.opinionInicial); //si no hay ninguna devuelve la opinion del tipo del usuario que subió la muestra
			//pero es un caso que nunca va a sucedes porque este método solo se llama cuando al menos 2 expertos opinan
				
	}
	 
	//devuelve la opinion que más se repite entre todas las opiniones de la muestra
	//si no hay opiniones que se repiten devuelve una cualquiera entre las opiniones
	//si no hay opiniones devuelve el tipo de opinion de la muestra
	public TipoOpinion getOpinionMasPopular() {
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
				.orElse(this.opinionInicial); //si no hay votos, entonces devuelve el del usuario que publicó la muestra
					
	}
	
	//devuelve la fehca de la ultima votacion sobre la muestra, si no hay ninguna, devuelve la fecha de creacion de la muestra
	public Date getFechaUltimaVotacion() {

		//List<Opinion> opiniones = this.getHistorialDeOpiniones();
		Date fechaMasReciente = this.fechaCreacion; //opiniones.get(0).getFechaOpinion();
	    
	    for (Opinion opinion : opiniones) {
	    	Date fechaActual = opinion.getFechaOpinion();
	        if (fechaActual.after(fechaMasReciente)) {
	            fechaMasReciente = fechaActual; 
	        }
	    }
	    return fechaMasReciente;  //this.opiniones.isEmpty() ? this.fechaCreacion : fechaMasReciente;
	}
	
	//setters
	protected void setEstadoMuestra(EstadoVerificacionMuestra estado) {
		this.estado = estado;
	}
	
	
	//methods
	
	//revisa todos los usuarios de las opiniones que tiene la muestra para ver si el usuario que esta queriendo opinar ya opinó
	//si el usuario ya opinó sobre la muestra no puede volver a opinar en la misma muestra
	public boolean puedeOpinar(Usuario usuario) {
	    for (Opinion opinion : this.getHistorialDeOpiniones()) {
	        if (opinion.getUsuario().equals(usuario)) {
	            return false;
	        }
	    }
	    return true;
	}
	//valido el agregar la opinion de un usuario y que no se repitan	
	public void agregarOpinion(Opinion opinion) {
		if(this.puedeOpinar (opinion.getUsuario())) {
			this.estado.agregarOpinion(this, opinion);
		}
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
	
	//Implementacion del observer
	//
	public void notificarMuestra(ObservadorMuestra obsMuestra){
		this.getEstadoMuestra().notify(this , obsMuestra);
	}
	
}
