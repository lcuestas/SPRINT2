package com.ms_registraduria.registraduria.Controladores;

import com.ms_registraduria.registraduria.Modelos.*;
import com.ms_registraduria.registraduria.Repositorios.RepositorioPermiso;
import com.ms_registraduria.registraduria.Repositorios.RepositorioRoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permisos")
public class ControladorPermisos {
    @Autowired
    private RepositorioPermiso miRepositorioPermisos;

    @GetMapping("")
    public List<Permisos> index() {
        return this.miRepositorioPermisos.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Permisos create(@RequestBody Permisos infoPermisos) {
        return this.miRepositorioPermisos.save(infoPermisos);
    }

    @GetMapping("{id}")
    public Permisos show(@PathVariable String id) {
        Permisos PermisosActual = this.miRepositorioPermisos
                .findById(id)
                .orElse(null);
        return PermisosActual;
    }

    @PutMapping("{id}")
    public Permisos update(@PathVariable String id, @RequestBody Permisos
            infoPermisos) {
        Permisos PermisosActual = this.miRepositorioPermisos
                .findById(id)
                .orElse(null);
        if (PermisosActual != null) {
            PermisosActual.setURL(infoPermisos.getURL());
            PermisosActual.setMetodo(infoPermisos.getMetodo());
            ;
            return this.miRepositorioPermisos.save(PermisosActual);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Permisos PermisosActual = this.miRepositorioPermisos
                .findById(id)
                .orElse(null);
        if (PermisosActual != null) {
            this.miRepositorioPermisos.delete(PermisosActual);
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

