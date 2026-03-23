package ap1.paul.huayhua.repository;

import ap1.paul.huayhua.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
}