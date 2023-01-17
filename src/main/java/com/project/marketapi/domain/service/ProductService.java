package com.project.marketapi.domain.service;

import com.project.marketapi.domain.Product;
import com.project.marketapi.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    //Este servicio va a inyectar el ProductRepository
    @Autowired
    private ProductRepository productRepository;

    //1. Metodo que obtiene toda la lista de productos
    public List<Product> getAll(){
        return productRepository.getAll();
    }

    //2. Metodo que encuentra un producto por id
    public Optional<Product> getProduct(int productId){
        return productRepository.getProduct(productId);
    }

    //3. Metodo que busca producto por su categoria
    public Optional<List<Product>> getByCategory(int categoryId) {
        return productRepository.getByCategory(categoryId);
    }

    //4. Metodo para guardar
    public Product save(Product product){
        return productRepository.save(product);
    }

    //5. Metodo para eliminar
    public boolean delete(int productId){
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }
    //En este metodo estamos buscando un producto con el metodo que ya tenemos getproduct
    //Estamos utilizando el map del optional en donde al producto lo vamos a eliminar y retornamos true
    //Si el map se ejecuta es porque el producto existe
    //Cuando el producto no exista se retornará falso.

}
