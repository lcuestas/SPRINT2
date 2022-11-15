package com.ms_registraduria.registraduria.Repositorios;
import com.ms_registraduria.registraduria.Modelos.PermisosRoles;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioPermisosRoles extends
        MongoRepository<PermisosRoles,String> {
}