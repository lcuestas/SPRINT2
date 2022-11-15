package com.ms_registraduria.registraduria.Repositorios;
import com.ms_registraduria.registraduria.Modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface RepositorioUsuario extends
        MongoRepository<Usuario,String> {
}