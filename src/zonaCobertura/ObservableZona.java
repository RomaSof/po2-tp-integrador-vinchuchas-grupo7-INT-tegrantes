package zonaCobertura;

import muestra.Muestra;

public interface ObservableZona {
	
	public void agregarObservador(ObservadorZona observador);
	
	public void quitarObservador(ObservadorZona observador);
	
	public void notificarNuevaMuestra(Muestra muestra);
	
	public void notificarMuestraValida(Muestra muestra);
	
	
}