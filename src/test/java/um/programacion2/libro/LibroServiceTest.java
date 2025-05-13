package um.programacion2.libro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import um.programacion2.exception.LibroNoEncontrado;
import um.programacion2.util.TestDataFactory;
import um.programacion2.validacion.LibroValidador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibroServiceTest {
    private LibroService libroService;
    private final LibroRepository libroRepository = Mockito.mock(LibroRepository.class);
    private final LibroValidador libroValidador = Mockito.mock(LibroValidador.class);

    @BeforeEach
    public void setUp() {
        libroService = new LibroServiceImpl(libroRepository);
    }

    static Stream<Libro> providerLibros() {
        return TestDataFactory.createLibros().stream();
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testGuardarLibro(Libro libro) {
        doNothing().when(libroRepository).save(libro);
        doNothing().when(libroValidador).verifyIsbn(libro.getIsbn());

        libroService.guardar(libro);

        verify(libroRepository, times(1)).save(any(Libro.class));
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testBuscarPorId_ConLibroExistente(Libro libro) {
        when(libroRepository.findById(libro.getId())).thenReturn(Optional.of(libro));

        Optional<Libro> resultado = Optional.ofNullable(libroService.buscarPorId(libro.getId()));

        assertTrue(resultado.isPresent());
        assertEquals(libro.getId(), resultado.get().getId());
        verify(libroRepository, times(1)).findById(libro.getId());
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testBuscarPorId_ConLibroInexistente(Libro libro) {
        long idInexistente = libro.getId() + 1000;
        when(libroRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(
                LibroNoEncontrado.class,
                () -> libroService.buscarPorId(idInexistente));

        verify(libroRepository, times(1)).findById(idInexistente);
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testEliminarLibro_ConLibroExistente(Libro libro) {
        doNothing().when(libroRepository).deleteById(libro.getId());
        when(libroRepository.existsById(libro.getId())).thenReturn(true);

        libroService.eliminar(libro.getId());

        verify(libroRepository, times(1)).deleteById(libro.getId());
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testEliminarLibro_ConLibroInexistente(Libro libro) {
        long idInexistente = libro.getId() + 1000;
        when(libroRepository.existsById(idInexistente)).thenReturn(false);

        assertThrows(
                LibroNoEncontrado.class,
                () -> libroService.eliminar(idInexistente));
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testListarTodosLosLibros(Libro libro) {
        List<Libro> librosMock = TestDataFactory.createLibros();
        when(libroRepository.findAll()).thenReturn(librosMock);

        List<Libro> resultado = libroService.listarLibros();

        assertNotNull(resultado);
        assertEquals(librosMock.size(), resultado.size());
        assertTrue(resultado.contains(libro));
        verify(libroRepository, times(1)).findAll();
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testActualizarLibro_ConLibroExistente(Libro libro) {
        doNothing().when(libroRepository).save(libro);
        when(libroRepository.existsById(libro.getId())).thenReturn(true);

        libroService.actualizar(libro.getId(), libro);

        verify(libroRepository, times(1)).save(libro);
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testActualizarLibro_ConLibroInexistente(Libro libro) {
        long idInexistente = libro.getId() + 1000;
        when(libroRepository.existsById(idInexistente)).thenReturn(false);

        assertThrows(
                LibroNoEncontrado.class,
                () -> libroService.actualizar(idInexistente, libro));
    }
}
