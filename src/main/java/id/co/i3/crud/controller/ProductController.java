package id.co.i3.crud.controller;
import id.co.i3.crud.dbo.Product;
import id.co.i3.crud.dbo.ProductRepository;
import id.co.i3.crud.exception.BadRequestException;
import id.co.i3.crud.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ProductController
{
    private static final String PRODUCT_NOT_FOUND_STRING = "Product not found.";
    @Autowired private ProductRepository productRepository;

    @PostMapping(value = "product")
    public ResponseEntity<Product> createProduct(@RequestBody Product request) throws Exception
    {
        if (request.getVersion() == null || request.getVersion().isBlank()) throw new BadRequestException("Param \'version\' is not present.");
        if (request.getName() == null || request.getName().isBlank()) throw new BadRequestException("Param \'name\' is not present.");
        if (request.getPrice() == null || request.getPrice() < 0) throw new BadRequestException("Param \'price\' is not present.");
        if (request.getProductId() == null || request.getProductId().isBlank()) throw new BadRequestException("Param \'product_id\' is not present.");
        
        Product product = new Product();
        product.setVersion(request.getVersion());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setProductId(request.getProductId());
        
        productRepository.save(product);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping(value = "product/{id}")
    public ResponseEntity<Product> getProductDetail(@PathVariable(name = "id", required = true) Integer id) throws Exception
    {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND_STRING));
        
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping(value = "products")
    public ResponseEntity<List<Product>> getProducts() throws Exception
    {
        List<Product> products = productRepository.findAll();
        
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @DeleteMapping(value = "product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable(name = "id", required = true) Integer id) throws Exception
    {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND_STRING));
        productRepository.delete(product);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping(value = "product/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable(name = "id", required = true) Integer id,
        @RequestBody Product request) throws Exception
    {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND_STRING));
        
        if (request.getVersion() != null && !request.getVersion().isBlank() && !request.getVersion().equals(product.getVersion())){
            product.setVersion(request.getVersion());
        }

        if (request.getName() != null && !request.getName().isBlank() && !request.getName().equals(product.getName())){
            product.setName(request.getName());
        }

        if (request.getPrice() != null && request.getPrice() > -1 && request.getPrice() != product.getPrice()){
            product.setPrice(request.getPrice());
        }

        if (request.getProductId() != null && !request.getProductId().isBlank() && !request.getProductId().equals(product.getProductId())){
            product.setProductId(request.getProductId());
        }

        productRepository.save(product);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}
