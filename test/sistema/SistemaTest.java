package sistema;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import avisoOrganizaciones.ObservadorMuestra;
import criterio.BuscadorMuestra;
import muestra.Muestra;
import organizacion.Organizacion;
import usuario.Usuario;
import usuarioEstado.EstadoUsuarioExperto;
import zonaCobertura.ZonaDeCobertura;

class SistemaTest {
	
    private Sistema sistema;
    private ObservadorMuestra observadorMock;
    private BuscadorMuestra buscadorMock;

    @BeforeEach
    void setup() {
    	observadorMock = mock(ObservadorMuestra.class);
    	buscadorMock = mock(BuscadorMuestra.class);
        sistema = new Sistema(observadorMock, buscadorMock);
    }

    @Test
    void testRegistrarUsuarioYContar() {
        Usuario usuario = mock(Usuario.class);

        assertEquals(0, sistema.cantUsuarios());
        sistema.registrarUsuario(usuario);
        assertEquals(1, sistema.cantUsuarios());

        // No debe agregar dos veces el mismo usuario
        sistema.registrarUsuario(usuario);
        assertEquals(1, sistema.cantUsuarios());
    }

    @Test
    void testAgregarMuestraYNotificar() {
        Muestra muestra = mock(Muestra.class);

        assertEquals(0, sistema.cantMuestras());

        sistema.agregarMuestra(muestra);
        assertEquals(1, sistema.cantMuestras());

        // Verificar que se seteo el observador
        verify(muestra).setObservador(observadorMock);
        // Verificar que se notificó al agregar
        verify(muestra).notificarMuestra();

        // No debe agregar dos veces la misma muestra
        sistema.agregarMuestra(muestra);
        assertEquals(1, sistema.cantMuestras());
    }

    @Test
    void testRegistrarOrganizacion() {
        Organizacion organizacion = mock(Organizacion.class);

        assertEquals(0, sistema.getOrganizacionesEnSistema().size());
        sistema.registarOrganizacion(organizacion);
        assertEquals(1, sistema.getOrganizacionesEnSistema().size());

        // No debe agregar dos veces
        sistema.registarOrganizacion(organizacion);
        assertEquals(1, sistema.getOrganizacionesEnSistema().size());
    }

    @Test
    void testAgregarZona() {
        ZonaDeCobertura zona = mock(ZonaDeCobertura.class);

        assertEquals(0, sistema.getZonasEnSistema().size());
        sistema.agregarZona(zona);
        assertEquals(1, sistema.getZonasEnSistema().size());

        // No debe agregar dos veces
        sistema.agregarZona(zona);
        assertEquals(1, sistema.getZonasEnSistema().size());
    }
    
    @Test
    void testActualizarEstadoUsuarioEnSistema() {
        // 1. Crear instancia real del sistema (o mock parcial)
        Sistema sistema = new Sistema(mock(ObservadorMuestra.class), mock(BuscadorMuestra.class));
        
        // 2. Configurar spy del usuario
        Usuario usuario = spy(new Usuario("Juan Test"));
        
        // 3. Configurar comportamiento del spy
        doReturn(50).when(usuario).getCantidadDeEnviosUltimos30Dias(any());
        doReturn(70).when(usuario).getCantidadDeRevisionesUltimos30Dias(any());
        
        // 4. Registrar usuario en el sistema
        sistema.registrarUsuario(usuario);
        
        // 5. Ejecutar
        sistema.actualizarEstadoUsuario(usuario);
        
        // 6. Verificaciones
        verify(usuario).actualizarEstado(sistema.getMuestrasEnSistema());
        verify(usuario).setEstado(any(EstadoUsuarioExperto.class));
        assertTrue(usuario.esExperto());
    }

}
