package co.udea.ssmu.api.services.conductor;

import co.udea.ssmu.api.model.jpa.model.Conductor;
import co.udea.ssmu.api.model.jpa.repository.ConductorRepositorio;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ConductorImplementacionTest {

    @Mock
    private ConductorRepositorio conductorRepositorio;
    @InjectMocks
    private ConductorImplementacion conductorImplementacion;

    private Conductor conductor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        conductor = new Conductor();
        conductor.setNombre("Oswald Gutierrez");
        conductor.setCelular("3102580351");
        conductor.setConductorId(Long.valueOf(11));
    }

    @Test
    void findAllConductor() {
        when(conductorRepositorio.findAll()).thenReturn(Arrays.asList(conductor));
        assertNotNull(conductorImplementacion.findAllConductor()); //Traerá los valores que no sean nulos
        System.out.println(conductor);
    }

    @Test
    void saveConductor() {

        Conductor conductorGuardar = new Conductor();
        conductorGuardar.setNombre("Oswald Cortina ");
        conductorGuardar.setConductorId(Long.valueOf(109));

        when(conductorRepositorio.save(any(Conductor.class))).thenReturn(conductorGuardar);


        Conductor savedConductor = conductorImplementacion.saveConductor(conductorGuardar);


        assertNotNull(savedConductor, "El conductor guardado no debería ser nulo");
        assertEquals("Oswald Cortina ", savedConductor.getNombre(), "El nombre del conductor guardado no coincide");

        verify(conductorRepositorio, times(1)).save(eq(conductorGuardar));
        System.out.println("El conductor registrado es: "+conductorGuardar);
    }

    @Test
    void getConductor() {
        Conductor conductor2 = new Conductor();
        conductor2.setNombre("Daniel Gutierrez");
        conductor2.setConductorId(Long.valueOf(10));

        when(conductorRepositorio.findById(eq(10L))).thenReturn(Optional.of(conductor2));
        Conductor conductorObtenido = conductorImplementacion.getConductor(10L);

        verify(conductorRepositorio, times(1)).findById(eq(10L));
        assertNotNull(conductorObtenido);
        assertEquals("Daniel Gutierrez", conductorObtenido.getNombre());

        System.out.println(conductor2);
    }

    @Test
    void deleteConductor() {

        Long conductorId = 105L;

        when(conductorRepositorio.findById(eq(conductorId))).thenReturn(Optional.of(new Conductor()));
        conductorImplementacion.deleteConductor(conductorId);

        verify(conductorRepositorio, times(1)).deleteById(eq(conductorId));
        verify(conductorRepositorio, never()).findById(eq(conductorId));
        verify(conductorRepositorio, times(1)).deleteById(eq(conductorId));
        System.out.println("Id del conductor eliminado es:"+conductorId);

    }
}