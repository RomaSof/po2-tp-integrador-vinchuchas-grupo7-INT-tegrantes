package ubicacion; // Tengo un error con el package de ubicacion

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import ubicacion.Ubicacion;

public class UbicacionTest {

    private Ubicacion ubicacionCentral;
    private Ubicacion ubicacion;

    @BeforeEach
    public void setUp() {
        ubicacionCentral = mock(Ubicacion.class);
        ubicacion = new Ubicacion(30.00, 30.00);
    }

    @Test
    public void testUbicacionSeInicializaCorrectamenteConLatitudYLongitud() {
        Ubicacion ubicacion = mock(Ubicacion.class);
        when(ubicacion.getLatitud()).thenReturn(40.00);
        when(ubicacion.getLongitud()).thenReturn(41.00);

        assertEquals(40.00, ubicacion.getLatitud());
        assertEquals(41.00, ubicacion.getLongitud());
    }

    @Test
    public void testGetLatitudDevuelveLaLatitudCorrecta() {
        when(ubicacionCentral.getLatitud()).thenReturn(40.00);
        assertEquals(40.00, ubicacionCentral.getLatitud());
    }

    @Test
    public void testGetLongitudDevuelveLaLongitudCorrecta() {
        when(ubicacionCentral.getLongitud()).thenReturn(40.00);
        assertEquals(40.00, ubicacionCentral.getLongitud());
    }

    @Test
    public void testDistanciaEntreUbicacionYSiMismaEsCero() {
        when(ubicacionCentral.distanciaA(ubicacionCentral)).thenReturn(0.0);
        assertEquals(0.0, ubicacionCentral.distanciaA(ubicacionCentral));
    }

    @Test
    public void testListarUbicacionesDentroDeDevuelveSoloLasUbicacionesCercanas() {
    	
    	Ubicacion nuevaUbicacion = mock(Ubicacion.class);
    	
    	when(ubicacionCentral.getLatitud()).thenReturn(30.00);
		when(ubicacionCentral.getLongitud()).thenReturn(30.05); // aprox 5 km
		when(nuevaUbicacion.getLatitud()).thenReturn(40.00);
		when(nuevaUbicacion.getLongitud()).thenReturn(40.00); // aprox 1500 km
		
        List<Ubicacion> ubicaciones = List.of(ubicacionCentral, nuevaUbicacion);
        List<Ubicacion> resultado = ubicacion.listarUbicacionesDentroDe(6.0, ubicaciones); // dentro de 6 km

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(ubicacionCentral));
        assertFalse(resultado.contains(nuevaUbicacion));
    }
    
    @Test
    public void testListarMuestrasDentroDeIncluyeMuestrasJustoEnElLimite() {
    	
        // Mock muestra de referencia
        Muestra muestraReferencia = mock(Muestra.class);
        when(muestraReferencia.getUbicacion()).thenReturn(ubicacion);

        // Mock de muestra que está justo en el límite de la ubicación de muestra de referencia
        Ubicacion ubicacionEnElLimite = new Ubicacion(30.00, 30.045); // aprox 4.3 km
        Muestra muestraEnElLimite = mock(Muestra.class);
        when(muestraEnElLimite.getUbicacion()).thenReturn(ubicacionEnElLimite);

        // Lista de muestras a verificar
        List<Muestra> muestrasAVerificar = Arrays.asList(muestraEnElLimite);
        double distanciaMaxima = 5.0;

        List<Muestra> resultado = ubicacion.listarMuestrasDentroDe(muestraReferencia, distanciaMaxima, muestrasAVerificar);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(muestraEnElLimite));
    }
}
