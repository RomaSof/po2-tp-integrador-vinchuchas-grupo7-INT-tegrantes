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
	
	//methods
	
	//|centroA - centroB| < radioA + radioB determina si intersectan, o sea se solapan, dos zonas (son círculos)
	public boolean seSolapaCon(ZonaDeCobertura zona) {
		return 
				this.getEpicentro().distanciaA(zona.getEpicentro()) < this.getDistanciaEnKm() + zona.getDistanciaEnKm(); 
	}
	
	public void agregarMuestra(Muestra m) {
		this.muestrasReportadas.add(m);
	}
	
	

}

/*
+ agregarMuestra(Muestra): void
+ notificarNuevaMuestra(Muestra): void
+ notificarMuestraValidada(Muestra): void
+ agregarObservador(ObservadorZona): void
+ quitarObservador(ObservadorZona): void*/