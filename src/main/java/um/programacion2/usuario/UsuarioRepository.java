package um.programacion2.usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    void save(Usuario usuario);
    void deleteById(Long id);
    boolean existsById(Long id);

    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByEmail(String email);

    List<Usuario> findAll();
}
