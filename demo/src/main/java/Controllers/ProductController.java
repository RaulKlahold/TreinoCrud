package Controllers;


import Entity.EntityTest;
import Repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public ResponseEntity<List<EntityTest>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productRepository.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object>
    getOneProduct(@PathVariable(value="id") UUID id){
        Optional<EntityTest> productO =
                productRepository.findById(id);
        if(productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produto não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(productO.get());
    }

}
