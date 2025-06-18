package zonaCobertura;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import ubicacion.Ubicacion;

class ZonaDeCoberturaTestCase {
	//SUT
	private ZonaDeCobertura zona; //los tests se basan en esta zona
	private ZonaDeCobertura zona2;
	private ZonaDeCobertura zona3;
	
	//DOCS
	private Ubicacion u1;
	private Ubicacion u2;
	private Ubicacion u3;
	
	private Muestra m1;
	private Muestra m2;
	private Muestra m3;
	
	private ObservadorZona o1;
	private ObservadorZona o2;
	private ObservadorZona o3;
	
	List<Muestra> muestras;
	List<ObservadorZona> observadores;
	
@BeforeEach
public void seup() {
	// instancia ubicaciones
	u1 = new Ubicacion(-34.60, -58.40);
	u2 = new Ubicacion(-34.61, -58.41);
	u3 = new Ubicacion(-34.90,-58.40);
	
	//instancia mocks de muestras
	m1 = mock(Muestra.class);
	m2 = mock(Muestra.class);
	m3 = mock(Muestra.class);
	
	//instancia mocks de observadores
	o1 = mock(ObservadorZona.class);
	o2 = mock(ObservadorZona.class);
	o3 = mock(ObservadorZona.class);
	
	//lista muestras 
	muestras = new ArrayList<Muestra>(Arrays.asList(m1,m2));
	
	//lista observadores
	observadores = new ArrayList<ObservadorZona>(Arrays.asList(o1,o2));
	
	//instancia zonas
	zona = new ZonaDeCobertura("zona a", u1, 10, muestras, observadores);
	zona2 = new ZonaDeCobertura("zona b", u2, 8, muestras);
	zona3 = new ZonaDeCobertura("zona c", u3, 5, muestras);
	
}

	@Test
	void seColapSaConOtraZonaTest() {
		//verifica si las zonas colapsan entre si.
		assertTrue(zona.seSolapaCon(zona2)); //1.5 > 18 = solapan
		assertFalse(zona.seSolapaCon(zona3)); //33.4 > 15 = no solapan
	}
	
	//TEST OBSERVADORES
	@Test
	void agregarYQuitarObservadorTest() {
		//verifica que actualmente hay 2 observadores en esa zona
		assertEquals(2, zona.getObservadoresDeZona().size());
		
		zona.agregarObservador(o3); //agrega un nuevo observador y luego verifica que haya uno más entre los observadores
		
		assertEquals(3, zona.getObservadoresDeZona().size());
	}
	
	@Test
	void quitarObservadorTest() {
		//verifica que actualmente hay 2 observadores en esa zona
		assertEquals(2, zona.getObservadoresDeZona().size());
		
		zona.quitarObservador(o1); //quita un observador 
		assertEquals(1, zona.getObservadoresDeZona().size()); //verifica que la lista tenga un observador menos
		assertFalse(zona.getObservadoresDeZona().contains(o1)); //y verifica que no esté especificamente el que eliminó
	}
	
	//TEST NOTIFICACIONES
	@Test
	void nuevaMuestraTest() {
		zona.notificarNuevaMuestra(m1); //notifica a todos sus observadores ({o1,o2})
		
		verify(o1, times(1)).nuevaMuestra(zona, m1);
	    verify(o2, times(1)).nuevaMuestra(zona, m1);
	}
	
	@Test
	void muestraValidaTest() {	    
	    zona.agregarObservador(o3); //agrega un observador
	    zona.quitarObservador(o1); //elimina un observador
	    
	    zona.notificarMuestraValida(m2); //envia la notificacion
	    
	    //verifica que le llegue la notificacion a todos los observadores subscriptos
	    verify(o1, times(0)).muestraValidada(zona, m2); //no le debería llegar la notificacion
	    verify(o2, times(1)).muestraValidada(zona, m2);
	    verify(o3, times(1)).muestraValidada(zona, m2);
	}
	
	
	@Test
	void getNombreTest() {
		assertEquals("zona a", zona.getNombre()); 
	}
	
	//TEST MUESTRAS
	@Test
	void getMusetrasReportadasTest() {
		assertEquals(muestras, zona.getMuestrasReportadas()); 
		
		//agrega una nueva muetra y verifica que se haya agregado
		assertEquals(2, zona.getMuestrasReportadas().size());
		assertFalse(zona.getMuestrasReportadas().contains(m3));
		zona.agregarMuestra(m3);
		assertEquals(3, zona.getMuestrasReportadas().size());
		assertTrue(zona.getMuestrasReportadas().contains(m3));
	}
	
	@Test
	void agregarMuestraTest() {		
		//agrega una nueva muetra y verifica que se haya agregado
		
		assertEquals(2, zona.getMuestrasReportadas().size());
		assertFalse(zona.getMuestrasReportadas().contains(m3));
		
		zona.agregarMuestra(m3);
		
		assertEquals(3, zona.getMuestrasReportadas().size());
		assertTrue(zona.getMuestrasReportadas().contains(m3));
	}
	
	@Test
	void testMuestraEstaDentroDeLaZona() {
	    // Configurar ubicaciones para los mocks existentes
	    when(m1.getUbicacion()).thenReturn(u2); // u2 está dentro (a ~1.5km)
	    when(m3.getUbicacion()).thenReturn(u3); // u3 está fuera (a ~33km)
	    
	    // Verificaciones
	    assertTrue(zona.estaDentroDeLaZona(m1)); // m1 debería estar dentro
	    assertFalse(zona.estaDentroDeLaZona(m3)); // m3 debería estar fuera
	    
	    // Verificar que se llamó a getUbicacion()
	    verify(m1).getUbicacion();
	    verify(m3).getUbicacion();
	}
	

}
