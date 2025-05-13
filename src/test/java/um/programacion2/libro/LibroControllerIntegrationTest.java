package um.programacion2.libro;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import um.programacion2.TestConfig;
import um.programacion2.util.TestDataFactory;
import um.programacion2.exception.LibroNoEncontrado;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestConfig.class)
public class LibroControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LibroService libroService;

    @Autowired
    private LibroRepository libroRepository;

    static Stream<Libro> providerLibros() {
        return TestDataFactory.createLibros().stream();
    }

    @BeforeEach
    public void setup() {
        // Limpiar datos existentes
        ((LibroRepositoryImpl)libroRepository).limpiarTodo();

        // Cargar datos de prueba
        List<Libro> libros = TestDataFactory.createLibros();
        for (Libro libro : libros) {
            libroService.guardar(libro);
        }
    }

    @Test
    public void contextLoads() {
        // Solo verifica que el contexto cargue correctamente
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testCrearLibro(Libro libro) {
        // Act
        ResponseEntity<Void> response = restTemplate.postForEntity(
                "/api/libros", libro, Void.class);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getHeaders().getLocation().toString().contains("/api/libros/"));

        // Verificación adicional con el servicio real
        Long id = libro.getId();
        Libro guardado = libroService.buscarPorId(id);
        assertNotNull(guardado);
        assertEquals(id, guardado.getId());
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testBuscarPorId_ConLibroExistente(Libro libro) {
        // Primero guardamos el libro para asegurarnos de que exista
        libroService.guardar(libro);
        
        // Act
        ResponseEntity<Libro> response = restTemplate.getForEntity(
                "/api/libros/" + libro.getId(), Libro.class);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(libro.getId(), response.getBody().getId());
        assertEquals(libro.getTitulo(), response.getBody().getTitulo());
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testBuscarPorId_ConLibroInexistente(Libro libro) {
        // Act
        long idInexistente = 99999L;
        ResponseEntity<String> response = restTemplate.getForEntity(
                "/api/libros/" + idInexistente, String.class);
        
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().contains("No se encontro un libro con el ID de: " + idInexistente));
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testEliminarLibro_ConLibroExistente(Libro libro) {
        // Primero guardamos el libro para asegurarnos de que exista
        libroService.guardar(libro);
        
        // Act
        ResponseEntity<Void> response = restTemplate.exchange(
                "/api/libros/" + libro.getId(), 
                HttpMethod.DELETE,
                null,
                Void.class);
        
        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        
        // Verificar que el libro ha sido eliminado
        try {
            libroService.buscarPorId(libro.getId());
            fail("El libro debería haber sido eliminado");
        } catch (LibroNoEncontrado e) {
            // Excepción esperada
        }
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testEliminarLibro_ConLibroInexistente(Libro libro) {
        // Act
        long idInexistente = 99999L;
        ResponseEntity<String> response = restTemplate.exchange(
                "/api/libros/" + idInexistente,
                HttpMethod.DELETE,
                null,
                String.class);
        
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().contains("No se encontro un libro con el ID de: " + idInexistente));
    }

    @Test
    public void testListarTodosLosLibros() {
        // Preparar datos - crear algunos libros
        List<Libro> libros = TestDataFactory.createLibros();
        for (Libro libro : libros) {
            libroService.guardar(libro);
        }
        
        // Act
        ResponseEntity<Libro[]> response = restTemplate.getForEntity(
                "/api/libros", Libro[].class);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= libros.size(), 
                   "Deberían haber al menos " + libros.size() + " libros");
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testActualizarLibro_ConLibroExistente(Libro libro) {
        // Primero guardamos el libro para asegurarnos de que exista
        libroService.guardar(libro);
        
        // Modificar libro
        String nuevoTitulo = "Título actualizado - " + System.currentTimeMillis();
        libro.setTitulo(nuevoTitulo);
        
        // Act
        ResponseEntity<Void> response = restTemplate.exchange(
                "/api/libros/" + libro.getId(),
                HttpMethod.PUT,
                new HttpEntity<>(libro),
                Void.class);
        
        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        
        // Verificar que se actualizó
        Libro actualizado = libroService.buscarPorId(libro.getId());
        assertEquals(nuevoTitulo, actualizado.getTitulo());
    }

    @ParameterizedTest
    @MethodSource("providerLibros")
    public void testActualizarLibro_ConLibroInexistente(Libro libro) {
        // Act
        long idInexistente = 99999L;
        ResponseEntity<String> response = restTemplate.exchange(
                "/api/libros/" + idInexistente,
                HttpMethod.PUT,
                new HttpEntity<>(libro),
                String.class);
        
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().contains("No se encontro un libro con el ID de: " + idInexistente));
    }
}
