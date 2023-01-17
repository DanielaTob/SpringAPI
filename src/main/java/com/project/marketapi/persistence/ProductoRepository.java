package com.project.marketapi.persistence;

import com.project.marketapi.domain.Product;
import com.project.marketapi.domain.repository.ProductRepository;
import com.project.marketapi.persistence.crud.ProductoCrudRepository;
import com.project.marketapi.persistence.entity.Producto;
import com.project.marketapi.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //Le indicamos que esta clase es la que va a interactuar con la base de datos
public class ProductoRepository  implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;

    //1. Metodo que recupera toda la lista de productos de la DB
    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    //2. Metodo que retorna una lista de productos de una categoria
    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    //3. Metodo para consultar productos escasos
    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
        //Funcion lambda para mappear.
        // Recibe los productos que tiene adentro y los combierte a products y los retorna.
    }

    //4. Metodo para consultar un producto en particular
    @Override
    public Optional<Product> getProduct(int productId) {
        //FindById ya contiene el optional
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));

    }

    //Metodo para guardar un producto
    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }


    //Metodo para eliminar un producto
    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }
}
