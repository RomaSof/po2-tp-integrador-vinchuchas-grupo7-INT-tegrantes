package usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import muestra.Muestra;
import opinion.Opinion;
import opinion.TipoOpinion;
import ubicacion.Ubicacion;
import usuarioEstado.EstadoUsuarioExperto;


class UsuarioTest {
	
	private Usuario usuario1;
	private Usuario usuario2; // los tests se basan en este usuario, el primero es para instanciar una muestra
	private Usuario usuario3;// Mock de usuario
	private Usuario usuario4;// usuario spy verificado
	private Muestra muestra;
	private Date date;
	private TipoOpinion vinchuca;
	private Ubicacion ubicacion;
	private List<Muestra> listaDeMuestras;

	@BeforeEach
	public void setup() {
		date = new Date();
	    vinchuca = TipoOpinion.VINCHUCA_INFESTAN; 
		usuario1 = new Usuario("Juan");
		usuario2 = new Usuario("Pepe");
		ubicacion = mock(Ubicacion.class);
		
		//instancia de un mock espia de usuario llamado Jose(usuario basico)
		usuario3 = Mockito.spy(new Usuario("Jose"));
		
		//instancia espia Verificado
		usuario4 = Mockito.spy(new UsuarioVerificado("Alberto"));
	    
		new Opinion(usuario1, vinchuca, date);
	   
		muestra = new Muestra(usuario1, date , ubicacion , "image", vinchuca);
	    
		listaDeMuestras = new ArrayList<Muestra>();
		listaDeMuestras.add(muestra);
		
	}
	
	//test n°1
	@Test
	void testCrearMuestra() {
		assertTrue(muestra.getRecolectorMuestra().equals(usuario1));
		assertEquals(muestra.getEspecie(), TipoOpinion.VINCHUCA_INFESTAN.getEspecie());
		
		// Verifica que la muestra fue registrada en el usuario
	    assertEquals(1, usuario1.getCantidadDeEnviosUltimos30Dias(listaDeMuestras));
	    
	}
	//test n°2
	@Test
	void testGetterNombre() {
		assertTrue(usuario1.getNombre().equals("Juan"));
	}
	
	
	//test n°3
	@Test
	void testEstadoUsuarioBasico() {
		assertFalse(usuario2.esExperto());
		Opinion op = new Opinion(usuario2 , TipoOpinion.CHINCHE_FOLIADA, date);
		muestra.agregarOpinion(op);
		assertEquals(usuario2.getCantidadDeRevisionesUltimos30Dias(listaDeMuestras) , (1));
		assertEquals(muestra.getHistorialDeOpiniones().size() , (1)); // se agregó correctamente la opinion.
		usuario2.actualizarEstado(listaDeMuestras);// no debe actualizar el estado porque no cumple con requisitos
		assertFalse(usuario2.esExperto());
	}
	
	//test n°4
	@Test
	void testPasajeDeUsuarioBasicoAExperto() {
		assertFalse(usuario3.esExperto()); //inicia como usuario basico
		when(usuario3.getCantidadDeEnviosUltimos30Dias(listaDeMuestras)).thenReturn(50);
		when(usuario3.getCantidadDeRevisionesUltimos30Dias(listaDeMuestras)).thenReturn(70);
		//si se consulta su estado debe de estar en basico ya que no se llamo al metodo para actualizar su estado
		assertFalse(usuario3.esExperto());// sigue en estado de UsuarioBasico
		usuario3.actualizarEstado(listaDeMuestras);
		assertTrue(usuario3.esExperto());// se actualiza su estado a experto ya que cumple con los requisitos (10 envios, 20 revisiones)
	}
	//test n°5
	@Test
	void testPasajeDeUsuarioExpertoABasico() {
		when(usuario3.getCantidadDeEnviosUltimos30Dias(listaDeMuestras)).thenReturn(50); //
		when(usuario3.getCantidadDeRevisionesUltimos30Dias(listaDeMuestras)).thenReturn(70); //
		usuario3.actualizarEstado(listaDeMuestras); // agregamos los datos necesarios para hacerlo experto
		assertTrue(usuario3.esExperto()); // inicia como usuario experto
		when(usuario3.getCantidadDeEnviosUltimos30Dias(listaDeMuestras)).thenReturn(8);
		when(usuario3.getCantidadDeRevisionesUltimos30Dias(listaDeMuestras)).thenReturn(11);
		usuario3.actualizarEstado(listaDeMuestras);
		assertFalse(usuario3.esExperto());// estado actualizado a basico ya que no cumple con los requisitos para seguir siendo experto
	}
	
	//test n°6
	@Test
	void testActualizarEstadoDeExpertoQueSigueCumpliendoLosRequisitos() {
		when(usuario3.getCantidadDeEnviosUltimos30Dias(listaDeMuestras)).thenReturn(50); //
		when(usuario3.getCantidadDeRevisionesUltimos30Dias(listaDeMuestras)).thenReturn(70); //
		usuario3.actualizarEstado(listaDeMuestras); // agregamos los datos necesarios para hacerlo experto
		assertTrue(usuario3.esExperto());
		when(usuario3.getCantidadDeEnviosUltimos30Dias(listaDeMuestras)).thenReturn(20); // sigue cumpliendo
		when(usuario3.getCantidadDeRevisionesUltimos30Dias(listaDeMuestras)).thenReturn(30); // sigue cumpliendo
		usuario3.actualizarEstado(listaDeMuestras);
		assertTrue(usuario3.esExperto());// sigue siendo experto aunque haya bajado la cantidad de revisiones y envios
		
	}
	//test n°7
	@Test
	void testearEstadoDeUsuarioVerificado() {
		assertTrue( usuario4.esExperto() ); //es expertoDesdeElPrincipio
		when(usuario4.getCantidadDeEnviosUltimos30Dias(listaDeMuestras)).thenReturn(2);
		when(usuario4.getCantidadDeRevisionesUltimos30Dias(listaDeMuestras)).thenReturn(2);
		usuario4.actualizarEstado(listaDeMuestras);
		//no debe actualizar el estado a basico
		assertTrue(usuario4.esExperto());
		assertFalse(usuario4.getEstadoUsuario().verificarCambioDeEstado(usuario4 , listaDeMuestras)); // siempre devuelve false
	}
	//test n°8
	@Test
	void testOpinionFueraDePeriodo30Dias() {
	    Usuario usuarioTest = new Usuario("UsuarioTest");
	    
	    LocalDate fechaAntigua = LocalDate.now().minusDays(31);
	    Date fechaOpinionAntigua = Date.from(fechaAntigua.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    
	    Opinion opinionAntigua = new Opinion(usuarioTest, TipoOpinion.VINCHUCA_INFESTAN, fechaOpinionAntigua);
	    muestra.agregarOpinion(opinionAntigua);
	    
	    assertEquals(1, muestra.getHistorialDeOpiniones().size()); //
	    assertEquals(0, usuarioTest.getCantidadDeRevisionesUltimos30Dias(listaDeMuestras));
	}
	
	//test n°9
	@Test
	void testMuestraFueraDePeriodo30Dias() {
	    Usuario usuarioTest = new Usuario("UsuarioMuestraTest");
	    
	    LocalDate fechaAntigua = LocalDate.now().minusDays(35);
	    Date fechaMuestraAntigua = Date.from(fechaAntigua.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    
	    Muestra muestraAntigua = new Muestra(usuarioTest, fechaMuestraAntigua, ubicacion, "imagen_antigua",	 vinchuca);
	    listaDeMuestras.add(muestraAntigua);
	    
	    assertEquals(2, listaDeMuestras.size()); // 2 muestras creadas , una solo de usuario test
	    assertEquals(0, usuarioTest.getCantidadDeEnviosUltimos30Dias(listaDeMuestras));//no guarda ninguna en los ultimos 30 dias
	}
	//test n°10
	@Test
	void testOpinionVerificadaALahoraDeOpinarPeroDespuesElUsuarioPasaAEstadoBasico() {
	    // 1. Configurar usuario como experto inicialmente
	    Usuario usuarioExperto = new Usuario("Carlos");
	    usuarioExperto.setEstado(new EstadoUsuarioExperto());
	    assertTrue(usuarioExperto.esExperto());
	    
	    // 2. Crear opinión (debería ser verificada automáticamente por ser experto)
	    
	    LocalDate now = LocalDate.now();
	    Date ahora = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    
	    Opinion opinion = new Opinion(usuarioExperto, TipoOpinion.VINCHUCA_INFESTAN, ahora);
	    assertTrue(opinion.esOpinionVerificada());
	    
	    // 3. Cambiar el usuario a básico (ya que no cumple requisitos)
	    usuarioExperto.actualizarEstado(listaDeMuestras);
	    assertFalse(usuarioExperto.esExperto());
	    
	    // 4. Verificar que la opinión sigue siendo verificada
	    assertTrue(opinion.esOpinionVerificada(), 
	        "La opinión debe mantenerse verificada aunque el usuario ya no sea experto");
	    
	    // 5. Verificación adicional: crear nueva opinión (ahora debería ser no verificada)
	    Opinion nuevaOpinion = new Opinion(usuarioExperto, TipoOpinion.CHINCHE_FOLIADA, ahora);
	    assertFalse(nuevaOpinion.esOpinionVerificada(),
	        "Las nuevas opiniones no deberían ser verificadas si el usuario es básico");
	}
}
