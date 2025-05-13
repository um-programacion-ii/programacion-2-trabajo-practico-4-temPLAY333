package um.programacion2.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import um.programacion2.util.TestDataFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioRespositoryTest {
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
        usuarioRepository = new UsuarioRepositoryImpl();
        List<Usuario> usuarios = TestDataFactory.createUsuarios();
        Map<Long, Usuario> UsuariosMap = new HashMap<>();
        for (Usuario usuario : usuarios) {
            UsuariosMap.put(usuario.getId(), usuario);
        }
        usuarioRepository.setUsuarios(UsuariosMap);
    }

    private static Stream<Usuario> providerUsuarios() {
        return TestDataFactory.createUsuarios().stream();
    }

    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testGuardar(Usuario usuario) {
        usuarioRepository.save(usuario);

        assertTrue(usuarioRepository.existsById(usuario.getId()));
    }
    
    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testBuscarPorId(Usuario usuario) {
        usuarioRepository.save(usuario);
        
        Optional<Usuario> result = usuarioRepository.findById(usuario.getId());
        
        assertTrue(result.isPresent());
        assertEquals(usuario.getId(), result.get().getId());
        assertEquals(usuario.getNombre(), result.get().getNombre());
    }
    
    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testBuscarTodos(Usuario usuario) {
        usuarioRepository.save(usuario);
        
        List<Usuario> usuarios = usuarioRepository.findAll();
        
        assertFalse(usuarios.isEmpty());
        assertTrue(usuarios.stream().anyMatch(u -> 
            u.getNombre().equals(usuario.getNombre()) && 
            u.getEmail().equals(usuario.getEmail())
        ));
    }
    
    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testActualizar(Usuario usuario) {
        usuarioRepository.save(usuario);
        String nuevoNombre = "Nombre Actualizado";
        
        usuario.setNombre(nuevoNombre);
        usuarioRepository.save(usuario);
        
        Optional<Usuario> usuarioActualizado = usuarioRepository.findById(usuario.getId());
        assertTrue(usuarioActualizado.isPresent());
        assertEquals(nuevoNombre, usuarioActualizado.get().getNombre());
        assertEquals(usuario.getId(), usuarioActualizado.get().getId());
    }
    
    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testEliminar(Usuario usuario) {
        usuarioRepository.save(usuario);
        
        usuarioRepository.deleteById(usuario.getId());
        
        Optional<Usuario> resultado = usuarioRepository.findById(usuario.getId());
        assertFalse(resultado.isPresent());
    }
    
    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testBuscarPorEmail(Usuario usuario) {
        usuarioRepository.save(usuario);
        
        Optional<Usuario> resultado = usuarioRepository.findByEmail(usuario.getEmail());
        
        assertTrue(resultado.isPresent());
        assertEquals(usuario.getEmail(), resultado.get().getEmail());
    }
}
