package com.project.marketapi.domain.repository;

import com.project.marketapi.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    //Metodos que se tienen  que implementar para cualquier repositorio que vaya a trabajar con productos - REGLAS
    //Esto nos ayuda a no acoplar nuestra informacion en una sola base de datos especifica}
    //Si no que siempre estemos hablando en terminos del DOMINIO(PRODUCT)
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<Product> getProduct(int productId);

    //Guardar y Borrar
    Product save(Product product);
    void delete(int productId);
}
