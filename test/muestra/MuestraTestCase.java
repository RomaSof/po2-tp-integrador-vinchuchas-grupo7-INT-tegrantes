package muestra;

import org.junit.jupiter.api.BeforeEach;

import usuario.Usuario;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import avisoOrganizaciones.ObservadorMuestra;
import opinion.Opinion;
import opinion.TipoOpinion;
import ubicacion.Ubicacion;

class MuestraTestCase {
	//SUT
	public Muestra muestra; 
	
	//DOCS
	
	//opiniones
	public Opinion op;
	public Opinion op1;
	public Opinion op2;
	public Opinion op3;
	public Opinion op4;
	public Opinion op5;
	public Opinion op6;
	//usuarios
	public Usuario user;
	public Usuario normal1;
	public Usuario normal2;
	public Usuario normal3;
	public Usuario experto1;
	public Usuario experto2;
	public Usuario experto3;
	
	//ubicacion de la muestra
	Ubicacion ubicacion;
	
	//date 
	LocalDate localDate;
	Date date;
	
	//observador
	public ObservadorMuestra obs;
	
	@BeforeEach
	public void setUp() {
		//users
		user = mock(Usuario.class);
		normal1 = mock(Usuario.class);
		normal2 = mock(Usuario.class);
		normal3 = mock(Usuario.class);
		experto1 = mock(Usuario.class);
		experto2 = mock(Usuario.class);
		experto3 = mock(Usuario.class);
		//esExperto
		when(user.esExperto()).thenReturn(false);
		when(normal1.esExperto()).thenReturn(false);
		when(normal2.esExperto()).thenReturn(false);
		when(normal3.esExperto()).thenReturn(false);
		when(experto1.esExperto()).thenReturn(true);
		when(experto2.esExperto()).thenReturn(true);
		when(experto3.esExperto()).thenReturn(true);
		
		
		//opinions
		op = mock(Opinion.class);
		op1 = mock(Opinion.class);
		op2 = mock(Opinion.class);
		op3 = mock(Opinion.class);
		op4 = mock(Opinion.class);
		op5 = mock(Opinion.class);
		op6 = mock(Opinion.class);
		//getTipoOpinion
		when(op.getTipoEspecie()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA); 
		when(op1.getTipoEspecie()).thenReturn(TipoOpinion.VINCHUCA_SORDIDA);
		when(op2.getTipoEspecie()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(op3.getTipoEspecie()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(op4.getTipoEspecie()).thenReturn(TipoOpinion.VINCHUCA_SORDIDA);
		when(op5.getTipoEspecie()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(op6.getTipoEspecie()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		//esExperto
		when(op.esOpinionVerificada()).thenReturn(false);
		when(op1.esOpinionVerificada()).thenReturn(false);
		when(op2.esOpinionVerificada()).thenReturn(false);
		when(op3.esOpinionVerificada()).thenReturn(false);
		when(op4.esOpinionVerificada()).thenReturn(true);
		when(op5.esOpinionVerificada()).thenReturn(true);
		when(op6.esOpinionVerificada()).thenReturn(true);
		//getUsuario
		when(op.getUsuario()).thenReturn(user);
		when(op1.getUsuario()).thenReturn(normal1);
		when(op2.getUsuario()).thenReturn(normal2);
		when(op3.getUsuario()).thenReturn(normal3);
		when(op4.getUsuario()).thenReturn(experto1);
		when(op5.getUsuario()).thenReturn(experto2);
		when(op6.getUsuario()).thenReturn(experto3);
		
		//ubicacion 
		ubicacion = mock(Ubicacion.class);
		
		//date
		localDate = LocalDate.now();
	    date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    
	    //observador
	    //obs = mock(ObservadorMuestra.class);
	    obs = Mockito.spy(new ObservadorMuestra());
		
		//sujeto de prueba muestra
		muestra = new Muestra(user, date, ubicacion, "image Vinchukis", TipoOpinion.IMAGEN_POCO_CLARA);
		muestra.setObservador(obs);
	}

	//GETTERS TESTS
	@Test
	void getEspecieTest() {
		assertEquals(TipoOpinion.IMAGEN_POCO_CLARA.getEspecie(), muestra.getEspecie());
	}
	
	@Test
	void getFotoMuestraTest() {
		assertEquals("image Vinchukis", muestra.getFotoMuestra());
	}
	
	@Test
	void getUbicacionTest() {
		assertEquals(ubicacion, muestra.getUbicacion());
	}
	
	@Test
	void getFechaCreacionTest() {
		assertEquals(date, muestra.getFechaCreacion());
	}
	
	@Test
	void getRecolectorMuestraTest() {
		assertEquals(user, muestra.getRecolectorMuestra());
	}
	
	@Test
	void getResultadoActualTest() {
		assertEquals(TipoOpinion.IMAGEN_POCO_CLARA, muestra.getResultadoActual());
	}
	
	@Test
	void getEstadoMuestra() {
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoVerificacionMuestra);
	}
	
	@Test
	void getHistorialDeOpinionesTest() {
		muestra.agregarOpinion(op1);
		muestra.agregarOpinion(op2);
		muestra.agregarOpinion(op3);
		
		List<Opinion> historialEsperado = new ArrayList<Opinion>(Arrays.asList(op1,op2,op3));
		
		assertEquals(historialEsperado, muestra.getHistorialDeOpiniones());
	}
	
	@Test
	void getOpinionesDeExpertosTest() {
		assertTrue(muestra.getHistorialDeOpiniones().isEmpty());
		
		//agrega solo 2 opiniones de expertos
		muestra.agregarOpinion(op1); //opinion comun
		muestra.agregarOpinion(op2); //opinion comun
		muestra.agregarOpinion(op4); //opinion de experto
		muestra.agregarOpinion(op5); //opinion de experto
		
		assertEquals(4, muestra.getHistorialDeOpiniones().size());
		
		List<Opinion> opsExpertasEsperadas = new ArrayList<Opinion>(Arrays.asList(op4,op5));
		
		assertEquals(opsExpertasEsperadas, muestra.getOpinionesDeExpertos());
	}
	
	@Test
	void getObservadorTest() {
		assertEquals(obs, muestra.getObservador());
	}
	 
	@Test
	void getOpinionQueCoincidenExpertosTest() {
		muestra.agregarOpinion(op1); //opinion común -> TipoOpinion.VINCHUCA_SORDIDA
		muestra.agregarOpinion(op4); //opinion de experto -> TipoOpinion.VINCHUCA_SORDIDA
		muestra.agregarOpinion(op5); //opinion de experto -> TipoOpinion.CHINCHE_FOLIADA
		muestra.agregarOpinion(op6); //opinion de experto -> TipoOpinion.CHINCHE_FOLIADA
		
		assertEquals(TipoOpinion.CHINCHE_FOLIADA, muestra.getOpinionQueCoincidenExpertos());
	}
	
	@Test
	void getOpinionMasPopularTest() {
		muestra.agregarOpinion(op1); //opinion común -> TipoOpinion.VINCHUCA_SORDIDA
		muestra.agregarOpinion(op2); //opinion común -> TipoOpinion.CHINCHE_FOLIADA
		muestra.agregarOpinion(op3); //opinion común -> TipoOpinion.CHINCHE_FOLIADA

		assertEquals(TipoOpinion.CHINCHE_FOLIADA, muestra.getOpinionMasPopular());
	}
	
	@Test
	void getFechaUltimaVotacionTest() { 
		//de una muestra sin opiniones es igual a la fecha de creacions
		assertTrue(muestra.getHistorialDeOpiniones().isEmpty()); 
		assertEquals(date, muestra.getFechaUltimaVotacion());
		
		//setteo 2 fechas diferentes de agegar una opinion a la muestra
		Date date1;
		Date date2;
		
		LocalDate localDate = LocalDate.parse("2026-12-03"); // <-- yyyy-MM-dd
	    date1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    
	    LocalDate localDate2 = LocalDate.parse("2026-12-05"); // <-- yyyy-MM-dd
	    date2 = Date.from(localDate2.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		when(op1.getFechaOpinion()).thenReturn(date1); //esta fecha esta antes
		when(op2.getFechaOpinion()).thenReturn(date2); //esta fecha esta después, utlima fecha
		
		muestra.agregarOpinion(op1);
		muestra.agregarOpinion(op2); //esta tiene la ultima fecha
		
		assertEquals(date2, muestra.getFechaUltimaVotacion()); 
		
	}
	
	//SETTERS TESTS
	@Test
	void setEstadoMuestraTest() {
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoVerificacionMuestra);
		
		muestra.setEstadoMuestra(new EstadoMuestraVerificada());
		
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoMuestraVerificada);
	}
	
	//METHODS TESTS
	@Test
	void puedeOpinarTest() {
		
		assertTrue(muestra.puedeOpinar(normal1));
		
		muestra.agregarOpinion(op1);
		
		assertFalse(muestra.puedeOpinar(normal1));
		
	}
	
	@Test
	void agregarOpinionTest() {
		assertTrue(muestra.getHistorialDeOpiniones().isEmpty());
		
		muestra.agregarOpinion(op2);
		
		assertTrue(muestra.getHistorialDeOpiniones().contains(op2));
	}
	
	@Test
	void esVerificadaTest() {
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoVerificacionMuestra);
		muestra.setEstadoMuestra(new EstadoMuestraVerificada());
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoMuestraVerificada);
		
		assertTrue(muestra.esVerificada());
	} 
	
	@Test
	void coincidenExpertosTest() {
		muestra.agregarOpinion(op1); //que no es hecha por un experto -> TipoOpinion.VINCHUCA_SORDIDA
		muestra.agregarOpinion(op5); //es experto pero solo es 1 experto que coincide -> TipoOpinion.CHINCHE_FOLIADA
		
		assertFalse(muestra.coincidenExpertos());
		
		muestra.agregarOpinion(op6); //ahora coinciden 2 expertos -> TipoOpinion.CHINCHE_FOLIADA
		
		assertTrue(muestra.coincidenExpertos());
		
	}
	
	//TEST COMPORTAMIENTO DE LOS ESTADOS DE LA MUESTRA
	@Test
	void TestEstadoVerificacionMuestra() {
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoVerificacionMuestra);
		
		//agrega opiniones de usuarios comunes a la mustra
		
		muestra.agregarOpinion(op1); // -> TipoOpinion.VINCHUCA_SORDIDA
		muestra.agregarOpinion(op2); // -> TipoOpinion.CHINCHE_FOLIADA
		muestra.agregarOpinion(op3); // -> TipoOpinion.CHINCHE_FOLIADA
		
		//se agregaron las opiniones de los usuarios comunes porque se puede en el estado inicial de la muestra
		
		assertEquals(3, muestra.getHistorialDeOpiniones().size());
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoVerificacionMuestra);
		
		//la muestra no es verificada porque todavía no votaron expertos que coincidan
		
		assertFalse(muestra.esVerificada());
		assertFalse(muestra.coincidenExpertos());
		assertTrue(muestra.getOpinionesDeExpertos().isEmpty());
		
		//la especie es la pupular entre las opiniones totales en este estado de muestra
		
		assertEquals(TipoOpinion.CHINCHE_FOLIADA.getEspecie(), muestra.getEspecie());
		
	}
	
	@Test
	void TestEstadoMuestraVerificandose() {
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoVerificacionMuestra);
		
		//agrega opiniones de usuarios comunes porque el estado inicial lo permite
		muestra.agregarOpinion(op1);
		muestra.agregarOpinion(op2);
		
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoVerificacionMuestra);
		
		//agrega un usuario esperto por lo que cambia el estado
		muestra.agregarOpinion(op4);
		
		//cambia el estado, hay 1 muestra de experto 
		assertEquals(3, muestra.getHistorialDeOpiniones().size());
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoMuestraVerificandose);
		assertFalse(muestra.esVerificada());
		assertFalse(muestra.coincidenExpertos());
		assertEquals(1, muestra.getOpinionesDeExpertos().size());
		
		//como el estado ahora es Verificandose la especie es no definida
		assertEquals(TipoOpinion.NO_DEFINIDA.getEspecie(), muestra.getEspecie());
		
	}
	
	@Test
	public void TestEstadoMuestraVerificada() {
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoVerificacionMuestra);
		//agrega opiniones comunes, es estado inicial
		
		muestra.agregarOpinion(op1); // -> TipoOpinion.VINCHUCA_SORDIDA
		muestra.agregarOpinion(op2); // -> -> TipoOpinion.CHINCHE_FOLIADA
		
		
		//agrega una opinion experta, cambia de estado
		
		muestra.agregarOpinion(op4); // -> TipoOpinion.VINCHUCA_SORDIDA
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoVerificacionMuestra);
		assertEquals(TipoOpinion.NO_DEFINIDA.getEspecie(), muestra.getEspecie());
		
		//agrega una segunda opinion experta y cambia de estado
		
		muestra.agregarOpinion(op5); // -> TipoOpinion.CHINCHE_FOLIADA
		
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoVerificacionMuestra);
		assertFalse(muestra.esVerificada());
		assertFalse(muestra.coincidenExpertos());
		assertEquals(2, muestra.getOpinionesDeExpertos().size());
		
		//estado verificandose
		assertEquals(TipoOpinion.NO_DEFINIDA.getEspecie(), muestra.getEspecie());
		
		//agrega una segunda opinion experta y coinciden 2 expertos con esa opinion
		
		muestra.agregarOpinion(op6); // ->TipoOpinion.CHINCHE_FOLIADA
		
		assertEquals(5, muestra.getHistorialDeOpiniones().size());
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoMuestraVerificada);
		
		//ahora es una muestra verificada porque coincidieron 2 expertos y ya no admite más opiniones
		
		assertTrue(muestra.esVerificada());
		assertTrue(muestra.coincidenExpertos());
		assertEquals(3, muestra.getOpinionesDeExpertos().size());
		assertEquals(TipoOpinion.CHINCHE_FOLIADA.getEspecie(), muestra.getEspecie());
		
		muestra.agregarOpinion(op3); //no cambia el tamaño de opiniones porque no se agregan más al estar verificada
		assertEquals(5, muestra.getHistorialDeOpiniones().size());
	} 
	
	@Test
	void notificarMuestraTest() {
		muestra.notificarMuestra();
		
		verify(obs,times(1)).notificarMuestra(muestra);
	}
	
	@Test
	void notificarMuestraValidadaTest() {
		//se inicia con una muestra con estado básico, no es verificada ni esta en verificacion
		
		assertFalse(muestra.esVerificada());
		
		//se agregan 2 opiniones de expertos que coinciden por lo que pasa a ser una muestra derificada
		
		muestra.agregarOpinion(op5); //opinion de experto -> TipoOpinion.CHINCHE_FOLIADA
		muestra.agregarOpinion(op6); //opinion de experto -> TipoOpinion.CHINCHE_FOLIADA
		
		assertTrue(muestra.esVerificada());
		
		//entonces al ser verificada le avisa al observer
		verify(obs,times(1)).notificarMuestraValidada(muestra);
	}
  
}
