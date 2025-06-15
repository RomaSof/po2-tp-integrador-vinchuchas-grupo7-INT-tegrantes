package criterio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import opinion.TipoOpinion;

public class CriterioCombinadoTest {
	private OperadorAnd operadorAnd;
	private OperadorOr operadorOr;
	private CriterioCombinado criterioCombinado1;
	private CriterioCombinado criterioCombinado2;
	
	private Muestra muestra;
	private List<CriterioBusqueda> criterios;
	
	private TipoOpinion tipoOpinion;
	private CriterioTipoInsecto criterio1;
	
	private CriterioFechaCreacion criterio2;
	private Date filtroDeFecha;
	private FiltroPorFechaMayor filtroFechaMayor;
	private FiltroPorFechaIgual filtroFechaIgual;
	
	@BeforeEach
	public void setUp() {
		muestra = mock(Muestra.class);
		
		tipoOpinion = TipoOpinion.VINCHUCA_INFESTAN;
		criterio1 = new CriterioTipoInsecto(tipoOpinion);
		
		filtroDeFecha = new Date(2000-12-31);
		
		filtroFechaMayor = new FiltroPorFechaMayor();
		filtroFechaIgual = new FiltroPorFechaIgual();
		
		criterio2 = new CriterioFechaCreacion(filtroDeFecha, filtroFechaMayor);
		
		criterios = List.of(criterio1, criterio2);
		operadorAnd = new OperadorAnd();
		operadorOr = new OperadorOr();
		
		criterioCombinado1 = new CriterioCombinado(criterios, operadorAnd);
		criterioCombinado2 = new CriterioCombinado(criterios, operadorOr);
	}
	
	@Test
	public void testCriterioCombinadoTieneUnaListaCon2Criterios() {
		assertEquals(2, criterioCombinado1.getCriteriosDeBusqueda().size());
		assertEquals(2, criterioCombinado2.getCriteriosDeBusqueda().size());
	}
	
	@Test
	public void testCriterioCombinadoComparaCorrectamenteConUnOperadorAnd() {
		when(muestra.getFechaCreacion()).thenReturn(new Date(2001-00-01));
		when(muestra.getResultadoActual()).thenReturn(TipoOpinion.VINCHUCA_INFESTAN);
		assertTrue(criterioCombinado1.cumple(muestra));
	}
	
	@Test
	public void testCriterioCombinadoComparaCorrectamenteConUnOperadorOr() {
		when(muestra.getFechaCreacion()).thenReturn(new Date(2001-00-01));
		when(muestra.getResultadoActual()).thenReturn(TipoOpinion.VINCHUCA_GUASAYANA);
		assertTrue(criterioCombinado2.cumple(muestra));
	}
	
	@Test
	public void testAgregaCorrectamenteUnNuevoCriterio() {
		// Criterio 3: Fecha de última votación mayor a una fecha
		CriterioFechaUltimaVotacion criterio3 = new CriterioFechaUltimaVotacion(filtroDeFecha, filtroFechaMayor);
		criterioCombinado1.agregarCriterio(criterio3);
		assertEquals(3, criterioCombinado1.getCriteriosDeBusqueda().size());
	}
	
	@Test
	public void testCriterioCombinadoAnidadoConOperadoresAnd() {
		// Criterio 3: Fecha de última votación mayor a una fecha
		CriterioFechaUltimaVotacion criterio3 = new CriterioFechaUltimaVotacion(filtroDeFecha, filtroFechaMayor);

		// (criterio1 AND criterio2)
		CriterioCombinado combinacionInterna = new CriterioCombinado(List.of(criterio1, criterio2), operadorAnd);

		// ((criterio1 AND criterio2) AND criterio3)
		CriterioCombinado combinacionFinal = new CriterioCombinado(List.of(combinacionInterna, criterio3), operadorAnd);

		// Configurar mocks para que cumplan todos los criterios
		when(muestra.getResultadoActual()).thenReturn(TipoOpinion.VINCHUCA_INFESTAN);
		when(muestra.getFechaCreacion()).thenReturn(new Date(2001-01-01));
		when(muestra.getFechaUltimaVotacion()).thenReturn(new Date(2001-01-01));

		assertTrue(combinacionFinal.cumple(muestra)); // ((criterio1 AND criterio2) AND criterio3) = true
		assertTrue(combinacionInterna.cumple(muestra)); // (criterio1 AND criterio2) = true
		assertTrue(criterio3.cumple(muestra)); // criterio3 = true
	}
	
	@Test
	public void testCriterioCombinadoAnidadoConOperadoresAndOr() {
	    // Criterio 3: Fecha de última votación mayor a una fecha
	    CriterioFechaUltimaVotacion criterio3 = new CriterioFechaUltimaVotacion(filtroDeFecha, filtroFechaIgual);

	    // (criterio1 AND criterio2)
	    CriterioCombinado combinacionInterna = new CriterioCombinado(List.of(criterio1, criterio2), operadorAnd);

	    // ((criterio1 AND criterio2) OR criterio3)
	 	CriterioCombinado combinacionFinal = new CriterioCombinado(List.of(combinacionInterna, criterio3), operadorOr);

	    // Configurar mocks: cumple solo criterio3
	    when(muestra.getResultadoActual()).thenReturn(TipoOpinion.VINCHUCA_INFESTAN);
	    when(muestra.getFechaCreacion()).thenReturn(new Date(2000-12-31));
	    when(muestra.getFechaUltimaVotacion()).thenReturn(new Date(2000-12-31));

	    assertTrue(criterio3.cumple(muestra));            // criterio3 = true
	    assertFalse(combinacionInterna.cumple(muestra));  // (criterio1 AND criterio2) = false
	    assertTrue(combinacionFinal.cumple(muestra));     // (criterio1 AND criterio2) OR criterio3) = true
	}




}
