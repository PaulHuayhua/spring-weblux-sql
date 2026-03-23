package ap1.paul.huayhua.rest;

import ap1.paul.huayhua.model.Product;
import ap1.paul.huayhua.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductRest {

    private final ProductService service;

    public ProductRest(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<Product> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Mono<Product> save(@RequestBody Product product) {
        return service.save(product);
    }

    @PutMapping("/{id}")
    public Mono<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PutMapping("/restore/{id}")
    public Mono<Product> restore(@PathVariable Long id) {
        return service.restore(id);
    }
}