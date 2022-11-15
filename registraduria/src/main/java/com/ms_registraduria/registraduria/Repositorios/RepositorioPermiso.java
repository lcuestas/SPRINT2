package com.ms_registraduria.registraduria.Repositorios;
import com.ms_registraduria.registraduria.Modelos.Permisos;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface RepositorioPermiso extends
        MongoRepository<Permisos,String> {
}