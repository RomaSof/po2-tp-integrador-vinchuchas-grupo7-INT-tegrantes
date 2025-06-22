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
import opinion.Opinion;
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
    
    private Opinion opinionExperta1;
    private Opinion opinionExperta2;

    
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
        
        
        // Para verificar las zonas con muestras validadas
        
        opinionExperta1 = mock(Opinion.class);
        opinionExperta2 = mock(Opinion.class);

        Usuario expertoA = mock(Usuario.class);
        Usuario expertoB = mock(Usuario.class);

        when(expertoA.esExperto()).thenReturn(true);
        when(expertoB.esExperto()).thenReturn(true);

        when(opinionExperta1.getUsuario()).thenReturn(expertoA);
        when(opinionExperta2.getUsuario()).thenReturn(expertoB);

        when(opinionExperta1.getTipoEspecie()).thenReturn(TipoOpinion.VINCHUCA_SORDIDA);
        when(opinionExperta2.getTipoEspecie()).thenReturn(TipoOpinion.VINCHUCA_SORDIDA);

        when(opinionExperta1.esOpinionVerificada()).thenReturn(true);
        when(opinionExperta2.esOpinionVerificada()).thenReturn(true);

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
        
        Muestra muestraPrueba = spy(new Muestra(usuarioMock, new Date(),
            new Ubicacion(-34.6083, -58.3712), "foto_valida.jpg", TipoOpinion.NO_DEFINIDA));
        
        // Una muestra tiene un ObservadorMuestra inicialmente null
        muestraPrueba.setObservador(obsLocal); // <-- Se setea el observador necesario para que la muestra pueda notificar su estado
        
        muestraPrueba.agregarOpinion(opinionExperta1);
        muestraPrueba.agregarOpinion(opinionExperta2); // <---- al cambiar de estado a Verificado notifica la muestra como validada
        
        verify(zonaMock).notificarMuestraValida(muestraPrueba);
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

        muestraDentroZona1.agregarOpinion(opinionExperta1); // <-- Con la opinion de un experto cambia su estado a Verificandose
        
        verify(zonaMock, never()).notificarNuevaMuestra(any());
        verify(zonaMock, never()).notificarMuestraValida(any());
        assertFalse(muestraDentroZona1.esVerificada());
    }
}