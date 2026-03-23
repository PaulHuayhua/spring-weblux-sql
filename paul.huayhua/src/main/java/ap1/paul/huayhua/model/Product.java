package ap1.paul.huayhua.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Table("product") // ⚠️ nombre de la tabla en PostgreSQL
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private Long id; // ✅ autoincremental

    private String name;
    private String description;
    private String category;
    private String brand;
    private double price;
    private int stock;
    private boolean available;
    @Column("created_at")
    private LocalDateTime createdAt;
}