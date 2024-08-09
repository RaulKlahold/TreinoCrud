package Controllers;


import DTO.ProductDTO;
import Entity.EntityTest;
import Repository.ProductRepository;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/products")
    public ResponseEntity<EntityTest>
    saveProduct(@RequestBody @Valid ProductDTO productDTO){
        var entityTest = new EntityTest();
        BeanUtils.copyProperties(productDTO, entityTest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productRepository.save(entityTest));

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object>
    deleteProduct(@PathVariable(value = "id") UUID id){
        Optional<EntityTest> productO = productRepository.findById(id);
        if(productO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("produto não encontrado");
        }
        productRepository.delete(productO.get());
        return ResponseEntity.status(HttpStatus.OK).body("produto deletado com successo");

    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object>
    updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductDTO productDTO){
        Optional<EntityTest> product0 = productRepository.findById(id);
        if(product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }


}
