package sistema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import avisoOrganizaciones.ObservadorMuestra;
import criterio.BuscadorMuestra;
import criterio.CriterioBusqueda;
import criterio.CriterioCombinado;
import criterio.CriterioFechaCreacion;
import criterio.CriterioFechaUltimaVotacion;
import criterio.CriterioNivelDeVerificacion;
import criterio.CriterioTipoInsecto;
import criterio.FiltroPorFechaIgual;
import criterio.FiltroPorFechaMayor;
import criterio.OperadorAnd;
import criterio.OperadorOr;
import muestra.EstadoMuestraVerificada;
import muestra.EstadoMuestraVerificandose;
import muestra.Muestra;
import opinion.TipoOpinion;

public class SistemaFiltroDeMuestrasTest {
	private Sistema sistema;
	private ObservadorMuestra observador;
	private BuscadorMuestra buscador;
	
	// Mocks de muestras para utilizar en los tests
	private Muestra muestra1;
	private Muestra muestra2;
	private Muestra muestra3;
	
	// Criterios para probar filtros
	private CriterioTipoInsecto criterioInsecto;
	private CriterioNivelDeVerificacion criterioVerificacion;
	private CriterioFechaCreacion criterioCreacion;
	private CriterioFechaUltimaVotacion criterioUltimaVotacion;
	private CriterioCombinado criterioCombinado;
	
	// Distintos estados de las muestras
	private EstadoMuestraVerificada estadoVerificada = new EstadoMuestraVerificada();
	private EstadoMuestraVerificandose estadoVerificandose = new EstadoMuestraVerificandose();
	
	// Distintos tipos de filtros para las fechas
	private FiltroPorFechaIgual filtroIgual = new FiltroPorFechaIgual();
	private FiltroPorFechaMayor filtroMayor = new FiltroPorFechaMayor();
	
	// Operadores logicos
	private OperadorAnd opAnd = new OperadorAnd();
	private OperadorOr opOr = new OperadorOr();
	
	// Ejemplos de Fechas
	
	// Ejemplo 1: 29 de junio de 2025
	Date fecha1 = Date.from(LocalDate.of(2025, 6, 29)
	                .atStartOfDay(ZoneId.systemDefault())
	                .toInstant());

	// Ejemplo 2: 10 de octubre de 2055
	Date fecha2 = Date.from(LocalDate.of(2055, 10, 10)
	                .atStartOfDay(ZoneId.systemDefault())
	                .toInstant());

	// Ejemplo 3: 10 de octubre de 2010
	Date fecha3 = Date.from(LocalDate.of(2010, 10, 10)
	                .atStartOfDay(ZoneId.systemDefault())
	                .toInstant());
	
	
	@BeforeEach
	public void setUp() {
		// Se crean 3 muestras mockeadas
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		
		// criterios de busquedas
		criterioInsecto =  new CriterioTipoInsecto(TipoOpinion.CHINCHE_FOLIADA);
		criterioVerificacion = new CriterioNivelDeVerificacion(estadoVerificada);
		criterioCreacion = new CriterioFechaCreacion(fecha1, filtroMayor);
		criterioUltimaVotacion = new CriterioFechaUltimaVotacion(fecha3, filtroIgual);
		
		// Lista de criterios para el CriterioCombinado
		List<CriterioBusqueda> criterios = List.of(criterioInsecto, criterioVerificacion);
		criterioCombinado = new CriterioCombinado(criterios, opAnd);

		// Observador y buscador de muestras (el buscador usa el criterio de tipo de insecto)
		observador = mock(ObservadorMuestra.class);
		buscador = new BuscadorMuestra(criterioInsecto);
		
		// Se inicializa el sistema con el observador y el buscador
		sistema = new Sistema(observador, buscador);
		
		// Se agregan las 3 muestras al sistema
		sistema.agregarMuestra(muestra1);
		sistema.agregarMuestra(muestra2);
		sistema.agregarMuestra(muestra3);

	}
	
	@Test
	public void testSistemaTieneTresMuestrasAgregadas() {
		assertEquals(3, sistema.cantMuestras());
	}
	
	@Test
	public void testSistemaTieneCriterioTipoInsecto() {
		assertEquals(criterioInsecto, sistema.getBuscador().getCriterioBusqueda());
	}
	
	@Test
	public void testSistemaFiltraMuestrasSimpleCorrectamente() {
		// Configuro los valores de las muestras mockeadas
		when(muestra1.getResultadoActual()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);		
		when(muestra2.getResultadoActual()).thenReturn(TipoOpinion.CHINCHE_PHTIA);
		when(muestra3.getResultadoActual()).thenReturn(TipoOpinion.VINCHUCA_GUASAYANA);
		
		// Verifico que se filtre correctamente solo la muestra1
		assertEquals(1, sistema.buscarMuestrasFiltradas().size());
		assertTrue(sistema.buscarMuestrasFiltradas().contains(muestra1));
		assertFalse(sistema.buscarMuestrasFiltradas().contains(muestra2));
		assertFalse(sistema.buscarMuestrasFiltradas().contains(muestra3));
	}
	
	@Test
	public void testSistemaActualizaCriterioYFiltraCorrectamente() {
		// Configuro los valores de las muestras mockeadas
		when(muestra1.getEstadoMuestra()).thenReturn(estadoVerificada);		
		when(muestra2.getEstadoMuestra()).thenReturn(estadoVerificandose);
		when(muestra3.getEstadoMuestra()).thenReturn(estadoVerificada);
		
		// Actualizo el criterio de busqueda por CriterioNivelDeVerificacion
		sistema.setCriterioDeBusqueda(criterioVerificacion);
		assertEquals(criterioVerificacion, sistema.getBuscador().getCriterioBusqueda());

		// Verifico que se filtre correctamente la muestra1 y muestra3
		assertEquals(2, sistema.buscarMuestrasFiltradas().size());
		assertTrue(sistema.buscarMuestrasFiltradas().contains(muestra1));
		assertFalse(sistema.buscarMuestrasFiltradas().contains(muestra2));
		assertTrue(sistema.buscarMuestrasFiltradas().contains(muestra3));
	}
	
	@Test
	public void testSistemaFiltraMuestrasPorFechasDeCreacionCorrectamente() {	
		// Actualizo el criterio de busqueda por CriterioFechaCreacion
		sistema.setCriterioDeBusqueda(criterioCreacion);
		assertEquals(criterioCreacion, sistema.getBuscador().getCriterioBusqueda());

		// Configuro los valores de las muestras mockeadas
		when(muestra1.getFechaCreacion()).thenReturn(fecha2);		
		when(muestra2.getFechaCreacion()).thenReturn(fecha2);
		when(muestra3.getFechaCreacion()).thenReturn(fecha3);
				
		// Verifico que se filtre correctamente la muestra1 y muestra2
		assertEquals(2, sistema.buscarMuestrasFiltradas().size());
		assertTrue(sistema.buscarMuestrasFiltradas().contains(muestra1));
		assertTrue(sistema.buscarMuestrasFiltradas().contains(muestra2));
		assertFalse(sistema.buscarMuestrasFiltradas().contains(muestra3));
	}
	
	@Test
	public void testSistemaFiltraMuestrasPorFechaDeUltimaVotacionCorrectamente() {
		// Actualizo el criterio de busqueda por CriterioFechaUltimaVotacion
		sistema.setCriterioDeBusqueda(criterioUltimaVotacion);
		assertEquals(criterioUltimaVotacion, sistema.getBuscador().getCriterioBusqueda());
				
		// Configuro los valores de las muestras mockeadas
		when(muestra1.getFechaUltimaVotacion()).thenReturn(fecha1);		
		when(muestra2.getFechaUltimaVotacion()).thenReturn(fecha2);
		when(muestra3.getFechaUltimaVotacion()).thenReturn(fecha3);
						
		// Verifico que se filtre correctamente la muestra3 (es la única que cumple)
		assertEquals(1, sistema.buscarMuestrasFiltradas().size());
		assertFalse(sistema.buscarMuestrasFiltradas().contains(muestra1));
		assertFalse(sistema.buscarMuestrasFiltradas().contains(muestra2));
		assertTrue(sistema.buscarMuestrasFiltradas().contains(muestra3));
	}
	
	@Test
	public void testSistemaFiltraPorCriterioCombinadoAnd() {
		// Actualizo el criterio de busqueda por CriterioCombinado
		// Inicialmente TipoInsecto y NivelVerificacion con operador logico AND
		sistema.setCriterioDeBusqueda(criterioCombinado);
		assertEquals(criterioCombinado, sistema.getBuscador().getCriterioBusqueda());
		
		// Configuro los valores de las muestras mockeadas
		when(muestra1.getResultadoActual()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(muestra1.getEstadoMuestra()).thenReturn(estadoVerificada);

		when(muestra3.getResultadoActual()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(muestra3.getEstadoMuestra()).thenReturn(estadoVerificandose);
		
		when(muestra2.getResultadoActual()).thenReturn(TipoOpinion.VINCHUCA_GUASAYANA);
		when(muestra2.getEstadoMuestra()).thenReturn(estadoVerificada);

		// Verifico que se filtre correctamente la muestra1
		assertEquals(1, sistema.buscarMuestrasFiltradas().size());
		assertTrue(sistema.buscarMuestrasFiltradas().contains(muestra1)); // True ^ True = True
		assertFalse(sistema.buscarMuestrasFiltradas().contains(muestra2)); // True ^ False = False
		assertFalse(sistema.buscarMuestrasFiltradas().contains(muestra3)); // False ^ True = False
	}
	
	@Test
	public void testSistemaFiltraPorCriterioCombinadoOr() {
		// Actualizo el criterio de busqueda por CriterioCombinado
		// Inicialmente TipoInsecto y NivelVerificacion con operador logico AND
		sistema.setCriterioDeBusqueda(criterioCombinado);
		assertEquals(criterioCombinado, sistema.getBuscador().getCriterioBusqueda());
		
		// Configuro los valores de las muestras mockeadas
		when(muestra1.getResultadoActual()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(muestra1.getEstadoMuestra()).thenReturn(estadoVerificada);

		when(muestra3.getResultadoActual()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(muestra3.getEstadoMuestra()).thenReturn(estadoVerificandose);
		
		when(muestra2.getResultadoActual()).thenReturn(TipoOpinion.VINCHUCA_GUASAYANA);
		when(muestra2.getEstadoMuestra()).thenReturn(estadoVerificada);
		
		// Actualizo el operador logico del criterio combinado por OR
		criterioCombinado.setOperador(opOr);
		assertEquals(opOr, criterioCombinado.getOperadorLogico());
		
		// Verifico que se filtre correctamente todas las muestras del sistema con el nuevo operador del criterio combinado
		assertEquals(3, sistema.buscarMuestrasFiltradas().size());
		assertTrue(sistema.buscarMuestrasFiltradas().contains(muestra1)); // True v True = True
		assertTrue(sistema.buscarMuestrasFiltradas().contains(muestra2)); // True v False = True
		assertTrue(sistema.buscarMuestrasFiltradas().contains(muestra3)); // False v True = True
	}
	
	@Test
	public void testSistemaFiltraPorCriterioCombinadoAnidado() {
		
		// -----------------------------------------------------------
			// I = CriterioInsecto
			// V = CriterioVerificacion
			// C = CriterioFechaCreacion
			// U = CriterioFechaUltimaVotacion
		// -----------------------------------------------------------
		
		// Creo un nuevo criterio combinado: (C v U)
		List<CriterioBusqueda> criterios2 = List.of(criterioCreacion, criterioUltimaVotacion);
		CriterioCombinado criterioCombinado2 = new CriterioCombinado(criterios2, opOr);
		
		// Se crea una lista con un único elemento (criterioCombinado2) para construir criterioCombinado3
		List<CriterioBusqueda> criterios3 = List.of(criterioCombinado2);
		CriterioCombinado criterioCombinado3 = new CriterioCombinado(criterios3, opOr);
		
		// Luego agrego un nuevo criterio a CriterioCombinado3: (I ∧ V)
		criterioCombinado3.agregarCriterio(criterioCombinado);
		assertEquals(2, criterioCombinado3.getCriteriosDeBusqueda().size());

				
		// Actualizo el criterio de busqueda en el sistema por CriterioCombinado3
		// Ahora la búsqueda sera: 
		// (FiltroCreacion OR FiltroUltimaVotacion) OR (filtroInsecto AND FiltroVerificacion)
		sistema.setCriterioDeBusqueda(criterioCombinado3);
		assertEquals(criterioCombinado3, sistema.getBuscador().getCriterioBusqueda());
		
		// Configuro los valores de las muestras mockeadas
		when(muestra1.getResultadoActual()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(muestra1.getEstadoMuestra()).thenReturn(estadoVerificada);
		when(muestra1.getFechaCreacion()).thenReturn(fecha1);
		when(muestra1.getFechaUltimaVotacion()).thenReturn(fecha1);

		when(muestra2.getResultadoActual()).thenReturn(TipoOpinion.VINCHUCA_GUASAYANA);
		when(muestra2.getEstadoMuestra()).thenReturn(estadoVerificada);
		when(muestra2.getFechaCreacion()).thenReturn(fecha3);
		when(muestra2.getFechaUltimaVotacion()).thenReturn(fecha2);
		
		when(muestra3.getResultadoActual()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(muestra3.getEstadoMuestra()).thenReturn(estadoVerificandose);
		when(muestra3.getFechaCreacion()).thenReturn(fecha1);
		when(muestra3.getFechaUltimaVotacion()).thenReturn(fecha3);
		
		// Verifico que se filtre correctamente las muestras
		assertEquals(2, sistema.buscarMuestrasFiltradas().size());
		
		// (C ∨ U) = FALSE ∨ FALSE = FALSE
		// (I ∧ V) = TRUE ∧ TRUE = TRUE
		// => FALSE ∨ TRUE = TRUE 

		assertTrue(sistema.buscarMuestrasFiltradas().contains(muestra1));
		
		// (C ∨ U) = FALSE ∨ FALSE = FALSE
		// (I ∧ V) = FALSE ∧ TRUE = FALSE
		// => FALSE ∨ FALSE = FALSE 

		assertFalse(sistema.buscarMuestrasFiltradas().contains(muestra2)); 
		
		// (C ∨ U) = FALSE ∨ TRUE = TRUE
		// (I ∧ V) = TRUE ∧ FALSE = FALSE
		// => TRUE ∨ FALSE = TRUE 

		assertTrue(sistema.buscarMuestrasFiltradas().contains(muestra3));
	}
}
