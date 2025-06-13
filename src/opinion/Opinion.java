package opinion;
import java.util.Date;

import muestra.Muestra;
import usuario.Usuario;


public class Opinion {
	private Usuario usuario;
	private TipoOpinion tipoOpinion;
	private Date fechaOpinion;
	//private Muestra muestra;
	//public Muestra getMuestra() {
	//	return muestra;
	//}

	private boolean esOpinionVerificada;
	
	//test en espa;ol
	
	public Opinion(Usuario usuario, TipoOpinion tipoOpinion, Date fechaOpinion) {
	    this.usuario = usuario;
	    this.tipoOpinion = tipoOpinion;
	    this.fechaOpinion = fechaOpinion; // Usa la fecha proporcionada
	    this.esOpinionVerificada = usuario.esExperto();
	}
	
	
	public void enviarOpinion(Muestra muestra) {
	    if (!muestra.getHistorialDeOpiniones().contains(this)) {
	        muestra.agregarOpinion(this);
	    }
	    if(!this.getUsuario().getOpiniones().contains(this)) {
	    this.getUsuario().addOpinion(this);
	    }
	   }
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public TipoOpinion getTipoEspecie() {
		return this.tipoOpinion;
	}
	
	public boolean esOpinionVerificada() {
		return this.esOpinionVerificada;
	}
	
	public Date getFechaOpinion() {
		return fechaOpinion;
	}
}
