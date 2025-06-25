package criterio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;

public class CriterioFechaCreacionTest {
	private CriterioFechaCreacion criterioFechaCreacionMayor;
	private CriterioFechaCreacion criterioFechaCreacionMenor;
	private CriterioFechaCreacion criterioFechaCreacionIgual;
	
	private Date filtroDeFecha;
	private FiltroPorFechaMayor filtroFechaMayor;
	private FiltroPorFechaMenor filtroFechaMenor;
	private FiltroPorFechaIgual filtroFechaIgual;
	private Muestra muestra;
	
	@BeforeEach
	public void setUp() {
		filtroDeFecha = new Date(2000-12-31);
		filtroFechaMayor = new FiltroPorFechaMayor();
		filtroFechaMenor = new FiltroPorFechaMenor();
		filtroFechaIgual = new FiltroPorFechaIgual();
		muestra = mock(Muestra.class);
		
		criterioFechaCreacionMayor = new CriterioFechaCreacion(filtroDeFecha, filtroFechaMayor);
		criterioFechaCreacionMenor = new CriterioFechaCreacion(filtroDeFecha, filtroFechaMenor);
		criterioFechaCreacionIgual = new CriterioFechaCreacion(filtroDeFecha, filtroFechaIgual);
		
	}
	
	@Test
	public void testCriterioTipoFechaCreacionSeCumpleSiEsMayor() {
		when(muestra.getFechaCreacion()).thenReturn(new Date(2001-00-01));
		
		assertTrue(criterioFechaCreacionMayor.cumple(muestra));
	}
	
	@Test
	public void testCriterioTipoFechaCreacionNoSeCumpleSiNoEsMayor() {
		when(muestra.getFechaCreacion()).thenReturn(new Date(2000-12-31)); // Es la misma fecha entonces no se cumple.
		
		assertFalse(criterioFechaCreacionMayor.cumple(muestra));
	}
	
	@Test
	public void testCriterioTipoFechaCreacionSeCumpleSiEsMenor() {
	    when(muestra.getFechaCreacion()).thenReturn(new Date(1999-12-10));
	    assertFalse(criterioFechaCreacionMenor.cumple(muestra));
	}

	@Test
	public void testCriterioTipoFechaCreacionNoSeCumpleSiNoEsMenor() {
	    when(muestra.getFechaCreacion()).thenReturn(new Date(2000-12-31)); // Es la misma fecha entonces no se cumple.
	    assertFalse(criterioFechaCreacionMenor.cumple(muestra));
	}
	
	@Test
	public void testCriterioTipoFechaCreacionSeCumpleSiEsIgual() {
	    when(muestra.getFechaCreacion()).thenReturn(filtroDeFecha); // Es la misma fecha entonces se cumple.
	    assertTrue(criterioFechaCreacionIgual.cumple(muestra));
	}

	@Test
	public void testCriterioTipoFechaCreacionNoSeCumpleSiNoEsIgual() {
	    when(muestra.getFechaCreacion()).thenReturn(new Date(2000-12-30));
	    assertFalse(criterioFechaCreacionIgual.cumple(muestra));
	}


}
