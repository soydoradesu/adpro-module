package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        if (product.getProductId() == null) {
            product.setProductId(UUID.randomUUID());
        }
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(UUID productId) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public Product edit(Product changes, UUID productId) {
        Product product = findById(productId);

        if (product != null) {
            product.setProductName(changes.getProductName());
            product.setProductQuantity(changes.getProductQuantity());
        }
        return product;
    }

    public void delete(UUID productId) {
        productData.removeIf(product -> product.getProductId().equals(productId));
    }
}
