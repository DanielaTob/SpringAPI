package com.project.marketapi.persistence.crud;

import com.project.marketapi.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    //2. Query-Method para recuperar todos los productos de una categoria en especifico
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    //3. Query-Method
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);



}
