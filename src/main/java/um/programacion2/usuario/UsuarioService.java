package um.programacion2.usuario;

import java.util.List;

public interface UsuarioService {
    Usuario buscarPorId(Long id);
    Usuario buscarPorEmail(String email);

    List<Usuario> listarUsuariosActivos();
    List<Usuario> listarUsuariosInactivos();
    List<Usuario> listarUsuariosPorNombre(String nombre);

    void guardar(Usuario usuario);
    void actualizar(Long id, Usuario usuario);
    void eliminar(Long id);
}
