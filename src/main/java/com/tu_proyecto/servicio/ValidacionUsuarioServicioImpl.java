package main.java.com.tu_proyecto.servicio;

import com.tu_proyecto.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ValidacionUsuarioServicioImpl implements ValidacionUsuarioServicio {
    private final List<Usuario> usuariosRegistrados = new ArrayList<>();

    @Override
    public String validarYRegistrar(Usuario usuario) {
        if (!usuario.getNombre().matches("^[a-zA-Z]+$")) {
            return "El nombre debe contener solo letras.";
        }
        if (!usuario.getUsername().matches("^[a-zA-Z0-9]+$") || isUsernameRepetido(usuario.getUsername())) {
            return "El username no debe contener espacios ni repetirse.";
        }
        if (!validarPassword(usuario.getPassword())) {
            return "La contraseña debe tener al menos 10 caracteres, un símbolo, un número y una mayúscula.";
        }
        if (!usuario.getEmail().matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+$")) {
            return "El email debe tener formato válido.";
        }
        usuariosRegistrados.add(usuario);
        return "Usuario registrado";
    }

    private boolean isUsernameRepetido(String username) {
        return usuariosRegistrados.stream().anyMatch(u -> u.getUsername().equals(username));
    }

    private boolean validarPassword(String password) {
        return password.length() >= 10 &&
               Pattern.compile("[A-Z]").matcher(password).find() &&
               Pattern.compile("[0-9]").matcher(password).find() &&
               Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
    }
}
