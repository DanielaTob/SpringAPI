package com.project.marketapi.web.controller;

import com.project.marketapi.domain.Product;
import com.project.marketapi.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ApiOperation("Get all supermarket products") //Docu swagger
    @ApiResponse(code = 200, message = "OK") //Docu swagger
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    //2. Metodo que encuentra un producto por id
    @GetMapping("/{productId}") //Para solucionar el error 404, debo poner el mismo nombre en get y path
    @ApiOperation("Search a product with an ID") //Docu swagger
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product NOT_FOUND"),
    })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "The id of the product", required = true, example = "7")   //Docu swagger
                                                  @PathVariable("productId") int productId){
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //3. Metodo que busca producto por su categoria
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //4. Metodo para guardar
    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    //5. Metodo para eliminar
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId){
        if (productService.delete(productId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

