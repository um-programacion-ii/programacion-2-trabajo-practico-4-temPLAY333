package um.programacion2.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import um.programacion2.exception.UsuarioNoEncontrado;
import um.programacion2.util.TestDataFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {
    private UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);

    @BeforeEach
    public void setUp() {
        usuarioService = new UsuarioServiceImpl(usuarioRepository);
    }

    static Stream<Usuario> providerUsuarios() {
        return TestDataFactory.createUsuarios().stream();
    }

    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testGuardarUsuario(Usuario usuario) {
        doNothing().when(usuarioRepository).save(usuario);

        usuarioService.guardar(usuario);

        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testBuscarPorId_ConUsuarioExistente(Usuario usuario) {
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = Optional.ofNullable(usuarioService.buscarPorId(usuario.getId()));

        assertTrue(resultado.isPresent());
        assertEquals(usuario.getId(), resultado.get().getId());
        verify(usuarioRepository, times(1)).findById(usuario.getId());
    }

    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testBuscarPorId_ConUsuarioInexistente(Usuario usuario) {
        long idInexistente = usuario.getId() + 1000;
        when(usuarioRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(
                UsuarioNoEncontrado.class,
                () -> usuarioService.buscarPorId(idInexistente));

        verify(usuarioRepository, times(1)).findById(idInexistente);
    }
    
    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testBuscarPorEmail_ConUsuarioExistente(Usuario usuario) {
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.buscarPorEmail(usuario.getEmail());

        assertNotNull(resultado);
        assertEquals(usuario.getEmail(), resultado.getEmail());
        verify(usuarioRepository, times(1)).findByEmail(usuario.getEmail());
    }

    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testBuscarPorEmail_ConUsuarioInexistente(Usuario usuario) {
        String emailInexistente = "noexiste@test.com";
        when(usuarioRepository.findByEmail(emailInexistente)).thenReturn(Optional.empty());

        assertThrows(
                UsuarioNoEncontrado.class,
                () -> usuarioService.buscarPorEmail(emailInexistente));

        verify(usuarioRepository, times(1)).findByEmail(emailInexistente);
    }

    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testListarUsuariosActivos(Usuario usuario) {
        ArrayList<Usuario> usuariosActivos = new ArrayList<>();
        if (usuario.isActivo()) {
            usuariosActivos.add(usuario);
        }
        when(usuarioRepository.findAll()).thenReturn(usuariosActivos);

        List<Usuario> resultado = usuarioService.listarUsuariosActivos();

        assertNotNull(resultado);
        assertEquals(usuariosActivos.size(), resultado.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testListarUsuariosInactivos(Usuario usuario) {
        ArrayList<Usuario> usuariosInactivos = new ArrayList<>();
        if (!usuario.isActivo()) {
            usuariosInactivos.add(usuario);
        }
        when(usuarioRepository.findAll()).thenReturn(usuariosInactivos);

        List<Usuario> resultado = usuarioService.listarUsuariosInactivos();

        assertNotNull(resultado);
        assertEquals(usuariosInactivos.size(), resultado.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testListarUsuariosPorNombre(Usuario usuario) {
        List<Usuario> usuariosPorNombre = List.of(usuario);
        when(usuarioRepository.findAll()).thenReturn(usuariosPorNombre);

        List<Usuario> resultado = usuarioService.listarUsuariosPorNombre(usuario.getNombre());

        assertNotNull(resultado);
        assertEquals(usuariosPorNombre.size(), resultado.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testEliminarUsuario_ConUsuarioExistente(Usuario usuario) {
        doNothing().when(usuarioRepository).deleteById(usuario.getId());
        when(usuarioRepository.existsById(usuario.getId())).thenReturn(true);

        usuarioService.eliminar(usuario.getId());

        verify(usuarioRepository, times(1)).deleteById(usuario.getId());
    }

    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testEliminarUsuario_ConUsuarioInexistente(Usuario usuario) {
        long idInexistente = usuario.getId() + 1000;
        when(usuarioRepository.existsById(idInexistente)).thenReturn(false);

        assertThrows(
                UsuarioNoEncontrado.class,
                () -> usuarioService.eliminar(idInexistente));
    }

    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testActualizarUsuario_ConUsuarioExistente(Usuario usuario) {
        doNothing().when(usuarioRepository).save(usuario);
        when(usuarioRepository.existsById(usuario.getId())).thenReturn(true);

        usuarioService.actualizar(usuario.getId(), usuario);

        verify(usuarioRepository, times(1)).save(usuario);
    }

    @ParameterizedTest
    @MethodSource("providerUsuarios")
    public void testActualizarUsuario_ConUsuarioInexistente(Usuario usuario) {
        long idInexistente = usuario.getId() + 1000;
        when(usuarioRepository.existsById(idInexistente)).thenReturn(false);

        assertThrows(
                UsuarioNoEncontrado.class,
                () -> usuarioService.actualizar(idInexistente, usuario));
    }
}
