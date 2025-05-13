package um.programacion2.prestamo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import um.programacion2.exception.PrestamoNoEncontrado;
import um.programacion2.util.TestDataFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PrestamoServiceTest {
    private PrestamoService prestamoService;
    private final PrestamoRepository prestamoRepository = Mockito.mock(PrestamoRepository.class);

    @BeforeEach
    public void setUp() {
        prestamoService = new PrestamoServiceImpl(prestamoRepository);
    }

    static Stream<Prestamo> providerPrestamos() {
        return TestDataFactory.createPrestamos().stream();
    }

    @ParameterizedTest
    @MethodSource("providerPrestamos")
    public void testGuardarPrestamo(Prestamo prestamo) {
        doNothing().when(prestamoRepository).save(prestamo);

        prestamoService.guardar(prestamo);

        verify(prestamoRepository, times(1)).save(any(Prestamo.class));
    }

    @ParameterizedTest
    @MethodSource("providerPrestamos")
    public void testBuscarPorId_ConPrestamoExistente(Prestamo prestamo) {
        when(prestamoRepository.findById(prestamo.getId())).thenReturn(Optional.of(prestamo));

        Optional<Prestamo> resultado = Optional.ofNullable(prestamoService.buscarPorId(prestamo.getId()));

        assertTrue(resultado.isPresent());
        assertEquals(prestamo.getId(), resultado.get().getId());
        verify(prestamoRepository, times(1)).findById(prestamo.getId());
    }

    @ParameterizedTest
    @MethodSource("providerPrestamos")
    public void testBuscarPorId_ConPrestamoInexistente(Prestamo prestamo) {
        long idInexistente = prestamo.getId() + 1000;
        when(prestamoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(
                PrestamoNoEncontrado.class,
                () -> prestamoService.buscarPorId(idInexistente));

        verify(prestamoRepository, times(1)).findById(idInexistente);
    }

    @ParameterizedTest
    @MethodSource("providerPrestamos")
    public void testEliminarPrestamo_ConPrestamoExistente(Prestamo prestamo) {
        doNothing().when(prestamoRepository).deleteById(prestamo.getId());
        when(prestamoRepository.existsById(prestamo.getId())).thenReturn(true);

        prestamoService.eliminar(prestamo.getId());

        verify(prestamoRepository, times(1)).deleteById(prestamo.getId());
    }

    @ParameterizedTest
    @MethodSource("providerPrestamos")
    public void testEliminarPrestamo_ConPrestamoInexistente(Prestamo prestamo) {
        long idInexistente = prestamo.getId() + 1000;
        when(prestamoRepository.existsById(idInexistente)).thenReturn(false);

        assertThrows(
                PrestamoNoEncontrado.class,
                () -> prestamoService.eliminar(idInexistente));
    }

    @ParameterizedTest
    @MethodSource("providerPrestamos")
    public void testListarTodosLosPrestamos(Prestamo prestamo) {
        List<Prestamo> prestamosMock = TestDataFactory.createPrestamos();
        when(prestamoRepository.findAll()).thenReturn(prestamosMock);

        List<Prestamo> resultado = prestamoService.listarPrestamos();

        assertNotNull(resultado);
        assertEquals(prestamosMock.size(), resultado.size());
        assertTrue(resultado.contains(prestamo));
        verify(prestamoRepository, times(1)).findAll();
    }

    @ParameterizedTest
    @MethodSource("providerPrestamos")
    public void testActualizarPrestamo_ConPrestamoExistente(Prestamo prestamo) {
        doNothing().when(prestamoRepository).save(prestamo);
        when(prestamoRepository.existsById(prestamo.getId())).thenReturn(true);

        prestamoService.actualizar(prestamo.getId(), prestamo);

        verify(prestamoRepository, times(1)).save(prestamo);
    }

    @ParameterizedTest
    @MethodSource("providerPrestamos")
    public void testActualizarPrestamo_ConPrestamoInexistente(Prestamo prestamo) {
        long idInexistente = prestamo.getId() + 1000;
        when(prestamoRepository.existsById(idInexistente)).thenReturn(false);

        assertThrows(
                PrestamoNoEncontrado.class,
                () -> prestamoService.actualizar(idInexistente, prestamo));
    }
}
