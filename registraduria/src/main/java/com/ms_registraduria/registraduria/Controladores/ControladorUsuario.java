package com.ms_registraduria.registraduria.Controladores;
import com.ms_registraduria.registraduria.Modelos.Usuario;
import com.ms_registraduria.registraduria.Modelos.Roll;
import com.ms_registraduria.registraduria.Repositorios.RepositorioUsuario;
import com.ms_registraduria.registraduria.Repositorios.RepositorioRoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {
    @Autowired
    private RepositorioUsuario miRepositorioUsuario;
    @Autowired
    private RepositorioRoll miRepositorioRoll;

    @GetMapping("")
    public List<Usuario> index() {
        return this.miRepositorioUsuario.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario create(@RequestBody Usuario infoUsuario) {
        infoUsuario.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
        return this.miRepositorioUsuario.save(infoUsuario);
    }

    @GetMapping("{id}")
    public Usuario show(@PathVariable String id) {
        Usuario usuarioActual = this.miRepositorioUsuario
                .findById(id)
                .orElse(null);
        return usuarioActual;
    }

    @PutMapping("{id}")
    public Usuario update(@PathVariable String id, @RequestBody Usuario
            infoUsuario) {
        Usuario usuarioActual = this.miRepositorioUsuario
                .findById(id)
                .orElse(null);
        if (usuarioActual != null) {
            usuarioActual.setNombreUsuario(infoUsuario.getNombreUsuario());
            usuarioActual.setCorreo(infoUsuario.getCorreo());
            usuarioActual.setContrasena(convertirSHA256(infoUsuario.getContrasena()))
            ;
            return this.miRepositorioUsuario.save(usuarioActual);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Usuario usuarioActual = this.miRepositorioUsuario
                .findById(id)
                .orElse(null);
        if (usuarioActual != null) {
            this.miRepositorioUsuario.delete(usuarioActual);
        }
    }

    /**
     * Relación (1 a n) entre rol y usuario
     *
     * @param id
     * @param id_rol
     * @return
     */
    @PutMapping("{id}/rol/{id_rol}")
    public Usuario asignarRolAUsuario(@PathVariable String id, @PathVariable
    String id_rol) {
        Usuario usuarioActual = this.miRepositorioUsuario.findById(id).orElse(null);
        Roll rolActual = this.miRepositorioRoll.findById(id_rol).orElse(null);
        usuarioActual.setRoll(rolActual);
        return this.miRepositorioUsuario.save(usuarioActual);
    }

    public String convertirSHA256(String password){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
            byte[] hash = md.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }
    }

