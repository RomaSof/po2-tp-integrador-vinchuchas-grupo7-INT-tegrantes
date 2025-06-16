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
	List<Muestra> muestras = new ArrayList<Muestra>(Arrays.asList(m1,m2));
	
	//lista observadores
	List<ObservadorZona> observadores = new ArrayList<ObservadorZona>(Arrays.asList(o1,o2));
	
	//instancia zonas
	zona = new ZonaDeCobertura("zona a", u1, 10, muestras, observadores);
	zona2 = new ZonaDeCobertura("zona b", u2, 8, muestras);
	zona3 = new ZonaDeCobertura("zona c", u3, 5, muestras);
	
}

	@Test
	void seColapSaConOtraZonaTest() {
		//verifica si las zonas colapsan entre si.
		assertTrue(zona.seSolapaCon(zona2)); //1.5 > 18
		assertFalse(zona.seSolapaCon(zona3)); //33.4 > 15
	}
	
	@Test
	void agregarYQuitarObservadorTest() {
		//verifica que actualmente hay 2 observadores en esa zona
		assertEquals(2, zona.getObservadoresDeZona().size());
		
		zona.agregarObservador(o3); //agrega un nuevo observador y luego verifica que haya uno más entre los observadores
		
		assertEquals(3, zona.getObservadoresDeZona().size());
		
		zona.quitarObservador(o1); //quita un observador 
		assertEquals(2, zona.getObservadoresDeZona().size()); //verifica que la lista tenga un observador menos
		assertFalse(zona.getObservadoresDeZona().contains(o1)); //y verifica que no esté especificamente el que eliminó
	}

}
