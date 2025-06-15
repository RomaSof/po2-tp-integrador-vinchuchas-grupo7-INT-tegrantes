package criterio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import opinion.TipoOpinion;

public class BuscadorMuestraTest {
	private BuscadorMuestra buscador;
	private CriterioCombinado criterioCombinado1;
	
	private Muestra muestra1;
	private Muestra muestra2;
	
	private TipoOpinion tipoOpinion;
	private CriterioBusqueda criterio1;
	private Date filtroDeFecha;
	private FiltroPorFechaIgual filtroFechaIgual;
	private CriterioBusqueda criterio2;	
	private List<CriterioBusqueda> criterios;
	private OperadorAnd operadorAnd;
	
	@BeforeEach
	public void setUp() {
		
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		
		tipoOpinion = TipoOpinion.VINCHUCA_INFESTAN;
		criterio1 = new CriterioTipoInsecto(tipoOpinion);
		
		filtroDeFecha = new Date(2000-12-31);
		filtroFechaIgual = new FiltroPorFechaIgual();
		criterio2 = new CriterioFechaCreacion(filtroDeFecha, filtroFechaIgual);
		
		criterios = List.of(criterio1, criterio2);
		operadorAnd = new OperadorAnd();
		
		criterioCombinado1 = new CriterioCombinado(criterios, operadorAnd);
		
		buscador = new BuscadorMuestra(criterioCombinado1);
	}
	
	@Test 
	public void testBuscadorDeMuestrasConUnCriterioCombinadoFuncionaCorrectamente() {
		
		// Configurar mocks para que la muestra1 pase el filtro combinado
		when(muestra1.getResultadoActual()).thenReturn(TipoOpinion.VINCHUCA_INFESTAN);
		when(muestra1.getFechaCreacion()).thenReturn(new Date(2000-12-31));
		
		// configurar mocks para que la muestra2 no pase el filtro combinado
		when(muestra2.getResultadoActual()).thenReturn(TipoOpinion.VINCHUCA_GUASAYANA);
		when(muestra2.getFechaCreacion()).thenReturn(new Date(2000-12-31));
		
		List<Muestra> muestras = List.of(muestra1, muestra2);
		
		// Verificación
		assertEquals(1, buscador.buscarMuestras(muestras).size()); // una muestra paso el filtro
		assertTrue(buscador.buscarMuestras(muestras).contains(muestra1)); // muestra1 paso el filtro por cumplir ambas condiciones
	}
	
	@Test
	public void testBuscadorDeMuestrasFiltraCorrectamenteYActualizaResultadosAlCambiarCriterio() {
		
		// Reutilizo el codigo anterior 
		
		// Configurar mocks para que la muestra1 pase el filtro combinado
		when(muestra1.getResultadoActual()).thenReturn(TipoOpinion.VINCHUCA_INFESTAN);
		when(muestra1.getFechaCreacion()).thenReturn(new Date(2000-12-31));
		
		// configurar mocks para que la muestra2 no pase el filtro combinado
		when(muestra2.getResultadoActual()).thenReturn(TipoOpinion.VINCHUCA_GUASAYANA);
		when(muestra2.getFechaCreacion()).thenReturn(new Date(2000-12-31));
		
		List<Muestra> muestras = List.of(muestra1, muestra2);
		
		// Verificación
		assertEquals(1, buscador.buscarMuestras(muestras).size()); // una muestra paso el filtro
		assertTrue(buscador.buscarMuestras(muestras).contains(muestra1)); // muestra1 paso el filtro por cumplir ambas condiciones
		
		// Cambio su criterio de filtro 
		
		buscador.setCriterioBusqueda(criterio2);
		
		assertEquals(2, buscador.buscarMuestras(muestras).size()); // Ahora las dos muestras pasaron el filtro!
		assertTrue(buscador.buscarMuestras(muestras).contains(muestra1));
		assertTrue(buscador.buscarMuestras(muestras).contains(muestra2));
	}
}
