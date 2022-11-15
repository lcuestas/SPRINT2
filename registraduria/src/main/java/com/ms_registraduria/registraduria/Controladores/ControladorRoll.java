package com.ms_registraduria.registraduria.Controladores;

import com.ms_registraduria.registraduria.Modelos.Roll;
import com.ms_registraduria.registraduria.Modelos.Usuario;
import com.ms_registraduria.registraduria.Repositorios.RepositorioRoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roll")
public class ControladorRoll {
    @Autowired
    private RepositorioRoll miRepositorioRoll;

    @GetMapping("")
    public List<Roll> index() {
        return this.miRepositorioRoll.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Roll create(@RequestBody Roll infoRoll) {
        return this.miRepositorioRoll.save(infoRoll);
    }

    @GetMapping("{id}")
    public Roll show(@PathVariable String id) {
        Roll RollActual = this.miRepositorioRoll
                .findById(id)
                .orElse(null);
        return RollActual;
    }

    @PutMapping("{id}")
    public Roll update(@PathVariable String id, @RequestBody Roll
            infoRoll) {
        Roll RollActual = this.miRepositorioRoll
                .findById(id)
                .orElse(null);
        if (RollActual != null) {
            RollActual.setNombre(infoRoll.getNombre());
            RollActual.setDescripcion(infoRoll.getDescripcion());
            ;
            return this.miRepositorioRoll.save(RollActual);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Roll usuarioActual = this.miRepositorioRoll
                .findById(id)
                .orElse(null);
        if (usuarioActual != null) {
            this.miRepositorioRoll.delete(usuarioActual);
        }
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

