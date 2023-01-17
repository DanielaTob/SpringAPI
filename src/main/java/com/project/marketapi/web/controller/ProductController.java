package com.project.marketapi.web.controller;

import com.project.marketapi.domain.Product;
import com.project.marketapi.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController //Indica que esta clase va a ser un controlador de una API Rest
@RequestMapping("/products") //Lleva como parametro el path al cual vamos a hacer las peticiones
public class ProductController {


    //Inyectar el servicio
    @Autowired
    private ProductService productService;

    //1. Metodo que retorna una lista de productos
    public List<Product> getAll(){
        return productService.getAll();
    }

    //2. Metodo que encuentra un producto por id
    public Optional<Product> getProduct(int productId){
        return productService.getProduct(productId);
    }

    //3. Metodo que busca producto por su categoria
    public Optional<List<Product>> getByCategory(int categoryId) {
        return productService.getByCategory(categoryId);
    }

    //4. Metodo para guardar
    public Product save(Product product){
        return productService.save(product);
    }

    //5. Metodo para eliminar
    public boolean delete(int productId){
        return productService.delete(productId);
    }

}
