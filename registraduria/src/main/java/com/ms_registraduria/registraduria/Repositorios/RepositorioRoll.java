package com.ms_registraduria.registraduria.Repositorios;
import com.ms_registraduria.registraduria.Modelos.Roll;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface RepositorioRoll extends
        MongoRepository<Roll,String> {
}