package com.project.marketapi.web.controller;

import com.project.marketapi.domain.Product;
import com.project.marketapi.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //Indica que esta clase va a ser un controlador de una API Rest
@RequestMapping("/products") //Lleva como parametro el path al cual vamos a hacer las peticiones
public class ProductController {


    //Inyectar el servicio
    @Autowired
    private ProductService productService;

    //1. Metodo que retorna una lista de productos
    @GetMapping("/all")
    public List<Product> getAll(){
        return productService.getAll();
    }

    //2. Metodo que encuentra un producto por id
    @GetMapping("/{productId}") //Para solucionar el error 404, debo poner el mismo nombre en get y path
    public Optional<Product> getProduct(@PathVariable("productId") int productId){
        return productService.getProduct(productId);
    }

    //3. Metodo que busca producto por su categoria
    @GetMapping("/category/{categoryId}")
    public Optional<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId);
    }

    //4. Metodo para guardar
    @PostMapping("/save")
    public Product save(@RequestBody Product product){
        return productService.save(product);
    }

    //5. Metodo para eliminar
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int productId){
        return productService.delete(productId);
    }

}

