package main.java.com.tu_proyecto.controlador;

import com.tu_proyecto.modelo.Usuario;
import com.tu_proyecto.servicio.ValidacionUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UsuarioControlador {
    @Autowired
    private ValidacionUsuarioServicio validacionUsuarioServicio;

    @GetMapping("/registro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        String mensaje = validacionUsuarioServicio.validarYRegistrar(usuario);
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("usuario", usuario);
        return "registro";
    }
}
