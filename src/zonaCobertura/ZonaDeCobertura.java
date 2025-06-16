package zonaCobertura;

import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;
import ubicacion.Ubicacion;

public class ZonaDeCobertura implements ObservableZona {
	private String nombre;
	private Ubicacion epicentro;
	private double distanciaEnKm;
	private List<Muestra> muestrasReportadas = new ArrayList<Muestra>();
	private List<ObservadorZona> observadores = new ArrayList<ObservadorZona>();
	
	//constructor
	public ZonaDeCobertura(String nombre, Ubicacion ubicacion, double distancia, List<Muestra> muestras) {
		this.nombre = nombre;
		this.epicentro = ubicacion;
		this.distanciaEnKm= distancia;
		this.muestrasReportadas = muestras;
	}
	
	//constructor con observadores
	public ZonaDeCobertura(String nombre, Ubicacion ubicacion, double distancia, List<Muestra> muestras, List<ObservadorZona> observadores) {
		this(nombre, ubicacion, distancia, muestras);
		this.observadores = observadores;
	}
	
	//getters
	public String getNombre() {
		return this.nombre;
	}
	
	public Ubicacion getEpicentro() {
		return this.epicentro;
	}
	
	public double getDistanciaEnKm() {
		return this.distanciaEnKm;
	}
	
	public List<Muestra> getMuestrasReportadas(){
		return this.muestrasReportadas;
	}
	
	public List<ObservadorZona> getObservadoresDeZona() {
		return this.observadores;
	} 
	
	//methods
	//|centroA - centroB| < radioA + radioB determina si intersectan, o sea se solapan, dos zonas (son círculos)
	public boolean seSolapaCon(ZonaDeCobertura zona) {
		return 
				this.getEpicentro().distanciaA(zona.getEpicentro()) < this.getDistanciaEnKm() + zona.getDistanciaEnKm(); 
	}
	
	public void agregarMuestra(Muestra m) {
		this.muestrasReportadas.add(m);
	}

	@Override
	public void agregarObservador(ObservadorZona observador) {
		this.observadores.add(observador);
		
	}

	@Override
	public void quitarObservador(ObservadorZona observador) {
		this.observadores.remove(observador);
		
	}

	@Override
	public void notificarNuevaMuestra(Muestra muestra) {
		this.observadores.stream().forEach(observador -> observador.nuevaMuestra(this, muestra));
		
	}

	@Override
	public void notificarMuestraValida(Muestra muestra) {
		this.observadores.stream().forEach(observador -> observador.muestraValidada(this, muestra));
		
	}
	

}