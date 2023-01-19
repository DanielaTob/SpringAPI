package com.project.marketapi.persistence.crud;

import com.project.marketapi.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {

    //2.Query-Method para buscar por idCliente
    Optional<List<Compra>> findByIdCliente(String idCliente);
}
