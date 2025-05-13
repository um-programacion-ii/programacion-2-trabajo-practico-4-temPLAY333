package um.programacion2.prestamo;

import org.springframework.beans.factory.annotation.Autowired;
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

public class PrestamoRepositoryTest {
    private PrestamoRepository prestamoRepository;

    @BeforeEach
    public void setUp() {
        prestamoRepository = new PrestamoRepositoryImpl();
        List<Prestamo> prestamos = TestDataFactory.createPrestamos();
        Map<Long, Prestamo> prestamosMap = new HashMap<>();
        for (Prestamo prestamo : prestamos) {
            prestamosMap.put(prestamo.getId(), prestamo);
        }
        prestamoRepository.setPrestamos(prestamosMap);
    }

    static Stream<Prestamo> providerPrestamos() {
        return TestDataFactory.createPrestamos().stream();
    }

    @ParameterizedTest
    @MethodSource("providerPrestamos")
    public void testSave_ConPrestamoValido(Prestamo prestamo) {
        prestamoRepository.save(prestamo);

        assertTrue(prestamoRepository.existsById(prestamo.getId()));
    }

    @ParameterizedTest
    @MethodSource("providerPrestamos")
    public void testFindById_ConPrestamoExistente(Prestamo prestamo) {
        Optional<Prestamo> encontrado = prestamoRepository.findById(prestamo.getId());

        assertNotNull(encontrado);
        assertEquals(prestamo.getId(), encontrado.map(Prestamo::getId).orElse(null));
    }

    @ParameterizedTest
    @MethodSource("providerPrestamos")
    public void testFindById_ConPrestamoInexistente(Prestamo prestamo) {
        long idInexistente = prestamo.getId() + 1000;
        Optional<Prestamo> encontrado = prestamoRepository.findById(idInexistente);

        assertNull(encontrado.map(Prestamo::getId).orElse(null));
    }

    @ParameterizedTest
    @MethodSource("providerPrestamos")
    public void testDeleteById_ConPrestamoExistente(Prestamo prestamo) {
        prestamoRepository.deleteById(prestamo.getId());

        assertFalse(prestamoRepository.existsById(prestamo.getId()));
    }

    @ParameterizedTest
    @MethodSource("providerPrestamos")
    public void testExistsById_ConPrestamoExistente(Prestamo prestamo) {
        boolean existe = prestamoRepository.existsById(prestamo.getId());

        assertTrue(existe);
    }

    @ParameterizedTest
    @MethodSource("providerPrestamos")
    public void testExistsById_ConPrestamoInexistente(Prestamo prestamo) {
        long idInexistente = prestamo.getId() + 1000; // ID que no existe en los mocks
        boolean existe = prestamoRepository.existsById(idInexistente);

        assertFalse(existe);
    }

    @ParameterizedTest
    @MethodSource("providerPrestamos")
    public void testFindAll_DevuelveTodosLosPrestamos(Prestamo prestamo) {
        List<Prestamo> todosLosPrestamos = prestamoRepository.findAll();

        assertNotNull(todosLosPrestamos);
        assertTrue(todosLosPrestamos.contains(prestamo));
    }
    
    @ParameterizedTest
    @MethodSource("providerPrestamos")
    public void testFindByLibroId_ConLibroExistente(Prestamo prestamo) {
        Optional<Prestamo> encontrado = prestamoRepository.findByLibroId(prestamo.getLibro().getId());
        
        assertTrue(encontrado.isPresent());
        assertEquals(prestamo.getLibro().getId(), encontrado.get().getLibro().getId());
    }
    
    @ParameterizedTest
    @MethodSource("providerPrestamos")
    public void testFindByLibroId_ConLibroInexistente(Prestamo prestamo) {
        long idLibroInexistente = prestamo.getLibro().getId() + 1000;
        Optional<Prestamo> encontrado = prestamoRepository.findByLibroId(idLibroInexistente);
        
        assertFalse(encontrado.isPresent());
    }
}
