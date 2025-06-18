package avisoOrganizaciones;
import java.util.ArrayList;
import java.util.List;

import muestra.*;
import zonaCobertura.*;

public class ObservadorMuestra {
	private List<ZonaDeCobertura> zonasDeCobertura = new ArrayList<ZonaDeCobertura>();
	
	public List<ZonaDeCobertura> getZonasDeCoberturasSubscritas(){
		return zonasDeCobertura;
	}
	
	public void addZonaDeCobertura(ZonaDeCobertura zonaDeCobertura) {
		if(! this.getZonasDeCoberturasSubscritas().contains(zonaDeCobertura))
		this.zonasDeCobertura.add(zonaDeCobertura);
	}
	
	public void eliminarZonaDeCobertura(ZonaDeCobertura zonaDeCobertura) {
		this.zonasDeCobertura.remove(zonaDeCobertura);
	}
	
	public void notificarMuestra(Muestra muestra) {
		for (ZonaDeCobertura zonaDeCobertura : zonasDeCobertura) {
			if(zonaDeCobertura.estaDentroDeLaZona(muestra)){ 
				zonaDeCobertura.notificarNuevaMuestra(muestra);
			}	
		}
	}
	
	public void notificarMuestraValidada(Muestra muestra) {
		for (ZonaDeCobertura zonaDeCobertura : zonasDeCobertura) {
			if(zonaDeCobertura.estaDentroDeLaZona(muestra)){ 
				zonaDeCobertura.notificarMuestraValida(muestra);
			}	
		}		
	}
	
}
