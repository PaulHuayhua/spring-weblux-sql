package ap1.paul.huayhua.service.impl;

import ap1.paul.huayhua.model.Product;
import ap1.paul.huayhua.repository.ProductRepository;
import ap1.paul.huayhua.service.ProductService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Product> save(Product product) {
        product.setAvailable(true);
        product.setCreatedAt(LocalDateTime.now());
        return repository.save(product);
    }

    @Override
    public Mono<Product> update(Long id, Product product) {
        return repository.findById(id)
                .flatMap(existing -> {
                    existing.setName(product.getName());
                    existing.setDescription(product.getDescription());
                    existing.setCategory(product.getCategory());
                    existing.setBrand(product.getBrand());
                    existing.setPrice(product.getPrice());
                    existing.setStock(product.getStock());
                    existing.setAvailable(product.isAvailable());
                    return repository.save(existing);
                });
    }

    @Override
    public Mono<Void> delete(Long id) {
        return repository.findById(id)
                .flatMap(product -> {
                    product.setAvailable(false); // eliminación lógica
                    return repository.save(product);
                })
                .then();
    }

    @Override
    public Mono<Product> restore(Long id) {
        return repository.findById(id)
                .flatMap(product -> {
                    product.setAvailable(true);
                    return repository.save(product);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado")));
    }
}