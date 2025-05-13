package um.programacion2.libro;

import um.programacion2.util.TestDataFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LibroRepositoryTest {
    private LibroRepository libroRepository;

    @BeforeEach
    public void setUp() {
        libroRepository = new LibroRepositoryImpl();
        List<Libro> libros = TestDataFactory.createLibros();
        Map<Long, Libro> librosMap = new HashMap<>();
        for (Libro libro : libros) {
            librosMap.put(libro.getId(), libro);
        }
        libroRepository.setLibros(librosMap);
    }

    static Stream<Libro> providerLibros() {
        return TestDataFactory.createLibros().stream();
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testSave_ConLibroValido(Libro libro) {
        libroRepository.save(libro);

        assertTrue(libroRepository.existsById(libro.getId()));
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testFindById_ConLibroExistente(Libro libro) {
        Optional<Libro> encontrado = libroRepository.findById(libro.getId());

        assertNotNull(encontrado);
        assertEquals(libro.getId(), encontrado.map(Libro::getId).orElse(null));
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testFindById_ConLibroInexistente(Libro libro) {
        long idInexistente = libro.getId() + 1000;
        Optional<Libro> encontrado = libroRepository.findById(idInexistente);

        assertNull(encontrado.map(Libro::getId).orElse(null));
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testDeleteById_ConLibroExistente(Libro libro) {
        libroRepository.deleteById(libro.getId());

        assertFalse(libroRepository.existsById(libro.getId()));
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testExistsById_ConLibroExistente(Libro libro) {
        boolean existe = libroRepository.existsById(libro.getId());

        assertTrue(existe);
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testExistsById_ConLibroInexistente(Libro libro) {
        long idInexistente = libro.getId() + 1000; // ID que no existe en los mocks
        boolean existe = libroRepository.existsById(idInexistente);

        assertFalse(existe);
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testFindAll_DevuelveTodosLosLibros(Libro libro) {
        List<Libro> todosLosLibros = libroRepository.findAll();

        assertNotNull(todosLosLibros);
        assertTrue(todosLosLibros.contains(libro));
    }
}
