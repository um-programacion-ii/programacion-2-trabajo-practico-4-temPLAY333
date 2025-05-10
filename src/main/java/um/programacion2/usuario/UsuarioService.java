package um.programacion2.usuario;

import java.util.List;

public interface UsuarioService {
    void registrarUsuario(Usuario usuario);
    void eliminarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    Usuario buscarUsuarioPorId(int id);
    Usuario buscarUsuarioPorNombre(String nombre);
    List<Usuario> listarUsuariosActivos();
    List<Usuario> listarUsuariosInactivos();
    List<Usuario> listarUsuariosPorNombre(String nombre);
}
