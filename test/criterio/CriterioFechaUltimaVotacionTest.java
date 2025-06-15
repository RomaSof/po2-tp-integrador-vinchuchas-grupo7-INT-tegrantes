package criterio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;

public class CriterioFechaUltimaVotacionTest {
	private CriterioFechaUltimaVotacion criterioFechaCreacionMayor;
	private CriterioFechaUltimaVotacion criterioFechaCreacionMenor;
	private CriterioFechaUltimaVotacion criterioFechaCreacionIgual;
	
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
		
		criterioFechaCreacionMayor = new CriterioFechaUltimaVotacion(filtroDeFecha, filtroFechaMayor);
		criterioFechaCreacionMenor = new CriterioFechaUltimaVotacion(filtroDeFecha, filtroFechaMenor);
		criterioFechaCreacionIgual = new CriterioFechaUltimaVotacion(filtroDeFecha, filtroFechaIgual);
		
	}
	
	@Test
	public void testCriterioTipoFechaDeUltimaVotacionSeCumpleSiEsMayor() {
		when(muestra.getFechaUltimaVotacion()).thenReturn(new Date(2001-00-01));
		
		assertTrue(criterioFechaCreacionMayor.cumple(muestra));
	}
	
	@Test
	public void testCriterioTipoFechaDeUltimaVotacionNoSeCumpleSiNoEsMayor() {
		when(muestra.getFechaUltimaVotacion()).thenReturn(new Date(2000-12-31)); // Es la misma fecha entonces no se cumple.
		
		assertFalse(criterioFechaCreacionMayor.cumple(muestra));
	}
	
	@Test
	public void testCriterioTipoFechaDeUltimaVotacionSeCumpleSiEsMenor() {
	    when(muestra.getFechaUltimaVotacion()).thenReturn(new Date(1999-12-10));
	    assertFalse(criterioFechaCreacionMenor.cumple(muestra));
	}

	@Test
	public void testCriterioTipoFechaDeUltimaVotacionNoSeCumpleSiNoEsMenor() {
	    when(muestra.getFechaUltimaVotacion()).thenReturn(new Date(2000-12-31)); // Es la misma fecha entonces no se cumple.
	    assertFalse(criterioFechaCreacionMenor.cumple(muestra));
	}
	
	@Test
	public void testCriterioTipoFechaDeUltimaVotacionSeCumpleSiEsIgual() {
	    when(muestra.getFechaUltimaVotacion()).thenReturn(filtroDeFecha); // Es la misma fecha entonces se cumple.
	    assertTrue(criterioFechaCreacionIgual.cumple(muestra));
	}

	@Test
	public void testCriterioTipoFechaDeUltimaVotacionNoSeCumpleSiNoEsIgual() {
	    when(muestra.getFechaUltimaVotacion()).thenReturn(new Date(2000-12-30));
	    assertFalse(criterioFechaCreacionIgual.cumple(muestra));
	}

}

