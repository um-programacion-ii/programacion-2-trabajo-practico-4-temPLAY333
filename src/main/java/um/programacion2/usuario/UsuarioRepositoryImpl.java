package um.programacion2.usuario;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {
    private Map<Long, Usuario> usuarios = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public void save(Usuario usuario) {
        if (usuario.getId() == null) {
            usuario.setId(nextId++);
        }
        usuarios.put(usuario.getId(), usuario);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarios.values().stream()
                .filter(usuario -> usuario.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public List<Usuario> findAll() {
        return new ArrayList<>(usuarios.values());
    }

    @Override
    public void deleteById(Long id) {
        usuarios.remove(id);
    }

    @Override
    public void limpiarTodo() {
        usuarios.clear();
    }

    @Override
    public boolean existsById(Long id) {
        return usuarios.containsKey(id);
    }

    @Override
    public Map<Long, Usuario> getUsuarios() {
        return usuarios;
    }

    @Override
    public void setUsuarios(Map<Long, Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
