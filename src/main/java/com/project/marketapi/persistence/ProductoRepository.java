package com.project.marketapi.persistence;

import com.project.marketapi.persistence.crud.ProductoCrudRepository;
import com.project.marketapi.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //Le indicamos que esta clase es la que va a interactuar con la base de datos
public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    //1. Metodo que recupera toda la lista de productos de la DB
    public List<Producto> getAll() {
        return (List<Producto>) productoCrudRepository.findAll();
    }

    //2. Metodo que retorna una lista de productos de una categoria
    public List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    //3. Metodo para consultar productos escasos
    public Optional<List<Producto>> getEscasos(int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    //4. Metodo para consultar un producto en particular
    public Optional<Producto> getProducto(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    //Metodo para guardar un producto
    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }

    //Metodo para eliminar un producto
    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
