package sistema;

import java.util.ArrayList;
import java.util.List;

import avisoOrganizaciones.ObservadorMuestra;
import criterio.BuscadorMuestra;
import criterio.CriterioBusqueda;
import muestra.Muestra;
import organizacion.Organizacion;
import usuario.Usuario;
import zonaCobertura.ZonaDeCobertura;

public class Sistema {
	private List<Usuario> usuariosEnSistema;
    private List<ZonaDeCobertura> zonasEnSistema;
    private List<Muestra> muestrasEnSistema;
    private List<Organizacion> organizacionesEnSistema;
    private ObservadorMuestra observador;
    private BuscadorMuestra buscador;
    
    public Sistema(ObservadorMuestra observador, BuscadorMuestra buscador) {
        this.usuariosEnSistema = new ArrayList<>();
        this.zonasEnSistema = new ArrayList<>();
        this.muestrasEnSistema = new ArrayList<>();
        this.organizacionesEnSistema = new ArrayList<>();
        this.observador = observador;
        this.buscador = buscador;
    }
    
    public List<Usuario> getUsuariosEnSistema() {
		return this.usuariosEnSistema;
	}

	public List<ZonaDeCobertura> getZonasEnSistema() {
		return this.zonasEnSistema;
	}

	public List<Muestra> getMuestrasEnSistema() {
		return this.muestrasEnSistema;
	}

	public List<Organizacion> getOrganizacionesEnSistema() {
		return this.organizacionesEnSistema;
	}

	public ObservadorMuestra getObservador() {
		return this.observador;
	}

	public BuscadorMuestra getBuscador() {
		return this.buscador;
	}

	public void agregarMuestra(Muestra muestra) {
	    if (!this.getMuestrasEnSistema().contains(muestra)) {
	        muestra.setObservador(this.getObservador()); 
	        this.getMuestrasEnSistema().add(muestra);
	        muestra.notificarMuestra(); // notifica al agregarse
	    }
	}
	
	public void registrarUsuario(Usuario usuario) {
	    if (!this.getUsuariosEnSistema().contains(usuario)) {
	        this.getUsuariosEnSistema().add(usuario);
	    }
	}

	public void registarOrganizacion(Organizacion organizacion) {
	    if (!this.getOrganizacionesEnSistema().contains(organizacion)) {
	        this.getOrganizacionesEnSistema().add(organizacion);
	    }
	}

	public void agregarZona(ZonaDeCobertura zona) {
	    if (!this.getZonasEnSistema().contains(zona)) {
	        this.getZonasEnSistema().add(zona);
	    }
	}

	public int cantUsuarios() {
	    return this.getUsuariosEnSistema().size();
	}

	public int cantMuestras() {
	    return this.getMuestrasEnSistema().size();
	}
	
	// Búsqueda de muestras en el sistema usando el criterio configurado
	public List<Muestra> buscarMuestrasFiltradas() {
	    return this.getBuscador().buscarMuestras(this.getMuestrasEnSistema());
	}
	
	// Cambiar el criterio de búsqueda
	public void setCriterioDeBusqueda(CriterioBusqueda nuevoCriterio) {
	    this.getBuscador().setCriterioBusqueda(nuevoCriterio);
	}
	
	public void actualizarEstadoUsuario(Usuario usuario) {
		if (this.getUsuariosEnSistema().contains(usuario)) {
	    usuario.actualizarEstado(this.getMuestrasEnSistema());
		}
	}
	
}
