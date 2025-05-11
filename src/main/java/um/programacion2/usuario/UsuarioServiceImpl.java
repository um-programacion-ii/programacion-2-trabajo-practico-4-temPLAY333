package um.programacion2.usuario;

import um.programacion2.exception.UsuarioNoEncontrado;

import java.util.List;

public class UsuarioServiceImpl implements UsuarioService{
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontrado(id));
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNoEncontrado(email));
    }

    @Override
    public List<Usuario> listarUsuariosActivos() {
        return usuarioRepository.findAll()
                .stream()
                .filter(Usuario::getEstado)
                .toList();
    }

    @Override
    public List<Usuario> listarUsuariosInactivos() {
        return usuarioRepository.findAll()
                .stream()
                .filter(usuario -> !usuario.getEstado())
                .toList();
    }

    @Override
    public List<Usuario> listarUsuariosPorNombre(String nombre) {
        return usuarioRepository.findAll()
                .stream()
                .filter(usuario -> usuario.getNombre().contains(nombre))
                .toList();
    }

    @Override
    public void guardar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new UsuarioNoEncontrado(id);
        }
    }

    @Override
    public void actualizar(Long id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.save(usuario);
        } else {
            throw new UsuarioNoEncontrado(id);
        }
    }
}
