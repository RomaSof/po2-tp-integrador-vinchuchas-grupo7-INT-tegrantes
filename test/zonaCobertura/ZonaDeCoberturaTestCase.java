package zonaCobertura;

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
	private ZonaDeCobertura zona;
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
	//ubicaciones
	u1 = new Ubicacion(0,0);
	u2 = new Ubicacion(5,0);
	u3 = new Ubicacion(30,0);
	
	//muestras
	m1 = mock(Muestra.class);
	m2 = mock(Muestra.class);
	m3 = mock(Muestra.class);
	
	//observadores
	o1 = mock(ObservadorZona.class);
	o2 = mock(ObservadorZona.class);
	o3 = mock(ObservadorZona.class);
	
	//lista muestras 
	List<Muestra> muestras = new ArrayList<Muestra>(Arrays.asList(m1,m2));
	
	//lista observadores
	List<ObservadorZona> observadores = new ArrayList<ObservadorZona>(Arrays.asList(o1,o2));
	
	//zonas
	zona = new ZonaDeCobertura("zona a", u1, 10, muestras, observadores);
	zona = new ZonaDeCobertura("zona b", u2, 7, muestras, observadores);
	zona = new ZonaDeCobertura("zona c", u3, 5, muestras, observadores);
	
}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
