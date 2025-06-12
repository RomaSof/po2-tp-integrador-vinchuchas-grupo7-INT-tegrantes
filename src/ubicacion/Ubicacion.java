package ubicacion;

import java.util.ArrayList;
import java.util.List;
import muestra.Muestra;

public class Ubicacion {
	
	private double latitud;
	private double longitud;
	
	public Ubicacion(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	public double getLatitud() {
		return this.latitud;
	}
	
	public double getLongitud() {
		return this.longitud;
	}
	
	/*
     * Calcula la distancia entre esta ubicación y otra, usando la fórmula de Haversine.
     * Esta fórmula estima la distancia en línea recta sobre la superficie de la Tierra,
     * considerando su curvatura.
     */
    public double distanciaA(Ubicacion otraUbicacion) {
    	
    	double R = 6371.0; // Radio promedio de la Tierra en Km

        // Convertir las latitudes y longitudes de grados a radianes
        double latitudOrigenRad = Math.toRadians(this.getLatitud());
        double longitudOrigenRad = Math.toRadians(this.getLongitud());
        double latitudDestinoRad = Math.toRadians(otraUbicacion.getLatitud());
        double longitudDestinoRad = Math.toRadians(otraUbicacion.getLongitud());

        // Diferencias entre coordenadas
        double diferenciaLatitud = latitudDestinoRad - latitudOrigenRad;
        double diferenciaLongitud = longitudDestinoRad - longitudOrigenRad;

        // Aplicación de la fórmula de Haversine
        double a = Math.sin(diferenciaLatitud / 2) * Math.sin(diferenciaLatitud / 2)
                 + Math.cos(latitudOrigenRad) * Math.cos(latitudDestinoRad)
                 * Math.sin(diferenciaLongitud / 2) * Math.sin(diferenciaLongitud / 2);

        double anguloCentral = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distancia final en kilómetros
        return R * anguloCentral;
    }
    
    public List<Ubicacion> listarUbicacionesDentroDe(double unaDistancia, List<Ubicacion> ubicacionesAVerificar) {
    	
		List<Ubicacion> ubicacionesCercanas = new ArrayList<Ubicacion>();
		
		for(Ubicacion ubicacion : ubicacionesAVerificar) {
			if(this.distanciaA(ubicacion) < unaDistancia) {
				ubicacionesCercanas.add(ubicacion);
			}
		}
		return ubicacionesCercanas;
	}
    
    public List<Muestra> listarMuestrasDentroDe(Muestra muestra, double unaDistancia, List<Muestra> muestrasAVerificar) {
        
        List<Muestra> muestrasCercanas = new ArrayList<Muestra>();
        
        for (Muestra m : muestrasAVerificar) {
            if (m.getUbicacion().distanciaA(muestra.getUbicacion()) < unaDistancia) {
                muestrasCercanas.add(m);
            }
        }
        return muestrasCercanas;
    }
}