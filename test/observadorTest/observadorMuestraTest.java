package observadorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import avisoOrganizaciones.ObservadorMuestra;
import muestra.*;
import opinion.TipoOpinion;
import organizacion.FuncionalidadExterna;
import organizacion.Organizacion;
import organizacion.TipoOrganicacion;
import ubicacion.Ubicacion;
import usuario.Usuario;
import zonaCobertura.ZonaDeCobertura;

class observadorMuestraTest {

    private ObservadorMuestra obsMuestra;
    private FuncionalidadExterna funcionalidadMock;
    
    @Spy private Muestra muestraDentroZona1;
    @Spy private Muestra muestraDentroZona2;
    @Spy private Muestra muestraDentroZona3;
    private Usuario usuarioMock;
    private ZonaDeCobertura zona1;
    private ZonaDeCobertura zona3;

    @BeforeEach
    void setUp() {
        funcionalidadMock = mock(FuncionalidadExterna.class);
        usuarioMock = mock(Usuario.class);
        obsMuestra = new ObservadorMuestra();

        Organizacion orga1 = new Organizacion(TipoOrganicacion.SALUD, 
            new Ubicacion(-34.6037, -58.3816), funcionalidadMock, funcionalidadMock);
        Organizacion orga3 = new Organizacion(TipoOrganicacion.CULTURAL, 
            new Ubicacion(-31.4167, -64.1833), funcionalidadMock, funcionalidadMock);

        zona1 = new ZonaDeCobertura("Zona CABA", new Ubicacion(-34.6037, -58.3816), 50, new ArrayList<>());
        zona3 = new ZonaDeCobertura("Zona Córdoba", new Ubicacion(-31.4167, -64.1833), 60, new ArrayList<>());
        
        obsMuestra.addZonaDeCobertura(zona1);
        obsMuestra.addZonaDeCobertura(zona3);
        
        zona1.agregarObservador(orga1);
        zona3.agregarObservador(orga1);
        zona3.agregarObservador(orga3);

        muestraDentroZona1 = spy(new Muestra(usuarioMock, new Date(), 
            new Ubicacion(-34.6083, -58.3712), "foto1.jpg", TipoOpinion.NO_DEFINIDA));
        muestraDentroZona2 = spy(new Muestra(usuarioMock, new Date(),
            new Ubicacion(-34.8591, -58.0137), "foto3.jpg", TipoOpinion.NO_DEFINIDA));
        muestraDentroZona3 = spy(new Muestra(usuarioMock, new Date(),
            new Ubicacion(-31.4208, -64.4992), "foto5.jpg", TipoOpinion.NO_DEFINIDA));
    }

    @Test
    void testCantidadDeZonasSubscritasAObserver() {
        assertEquals(2, obsMuestra.getZonasDeCoberturasSubscritas().size());
    }
    
    @Test
    void testAgregarZonaDeCoberturaAObserver(){
        ZonaDeCobertura zonaMock = mock(ZonaDeCobertura.class);
        obsMuestra.addZonaDeCobertura(zonaMock);
        assertEquals(3, obsMuestra.getZonasDeCoberturasSubscritas().size());
    }
    
    @Test
    void testNoSePuedeAgregar2VecesLaMismaZonaDeCobertura() {
        assertEquals(2, obsMuestra.getZonasDeCoberturasSubscritas().size());
        obsMuestra.addZonaDeCobertura(zona3);
        assertEquals(2, obsMuestra.getZonasDeCoberturasSubscritas().size());
    }
    
    @Test
    void testEliminarUnaZonaDeCovertura() {
        assertTrue(obsMuestra.getZonasDeCoberturasSubscritas().contains(zona3));
        obsMuestra.eliminarZonaDeCobertura(zona3);
        assertFalse(obsMuestra.getZonasDeCoberturasSubscritas().contains(zona3));
    }
    
    @Test
    void testNotificarMuestraRecienCreada() {
        ZonaDeCobertura zonaMock = mock(ZonaDeCobertura.class);
        when(zonaMock.estaDentroDeLaZona(any())).thenReturn(true);
        
        ObservadorMuestra obsLocal = new ObservadorMuestra();
        obsLocal.addZonaDeCobertura(zonaMock);
        
        muestraDentroZona1.setObservador(obsLocal); // <-- Agregado para que Muestra tenga un ObservadorMuestra (inicialmente es null)

        muestraDentroZona1.notificarMuestra();
        
        verify(zonaMock).notificarNuevaMuestra(muestraDentroZona1);
    }
    
    @Test
    void testNotificarMuestraValidada() {
        ZonaDeCobertura zonaMock = mock(ZonaDeCobertura.class);
        when(zonaMock.estaDentroDeLaZona(any())).thenReturn(true);
        
        ObservadorMuestra obsLocal = new ObservadorMuestra();
        obsLocal.addZonaDeCobertura(zonaMock);
        
        Muestra muestraValidada = spy(new Muestra(usuarioMock, new Date(),
            new Ubicacion(-34.6083, -58.3712), "foto_valida.jpg", TipoOpinion.NO_DEFINIDA));
        
        EstadoMuestraVerificada estadoVerificado = new EstadoMuestraVerificada();
        doReturn(estadoVerificado).when(muestraValidada).getEstadoMuestra();
        
        // Una muestra tiene un ObservadorMuestra inicialmente null
        muestraValidada.setObservador(obsLocal); // <-- Se setea el observador necesario para que la muestra pueda notificar su estado

        muestraValidada.notificarMuestra();
        
        verify(zonaMock).notificarMuestraValida(muestraValidada);
        verify(zonaMock, never()).notificarNuevaMuestra(any());
    }
    
    @Test
    void testMuestraNoNotificaCuandoEstaValidandose() {
        ZonaDeCobertura zonaMock = mock(ZonaDeCobertura.class);
        when(zonaMock.estaDentroDeLaZona(any())).thenReturn(true);
        
        ObservadorMuestra obsLocal = new ObservadorMuestra();
        obsLocal.addZonaDeCobertura(zonaMock);
        
        EstadoMuestraVerificandose estadoVerificandose = new EstadoMuestraVerificandose();
        doReturn(estadoVerificandose).when(muestraDentroZona1).getEstadoMuestra();
        
        // Se asigna el observador a la muestra (necesario para que pueda notificar)
        muestraDentroZona1.setObservador(obsLocal); 

        muestraDentroZona1.notificarMuestra();
        
        verify(zonaMock, never()).notificarNuevaMuestra(any());
        verify(zonaMock, never()).notificarMuestraValida(any());
        assertFalse(muestraDentroZona1.esVerificada());
    }
}