package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Product product = new Product();
        when(productRepository.create(product)).thenReturn(product);

        Product created = productService.create(product);
        assertSame(product, created);
        verify(productRepository).create(product);
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());

        when(productRepository.findAll()).thenReturn(productList.iterator());

        List<Product> result = productService.findAll();
        assertEquals(2, result.size());
        verify(productRepository).findAll();
    }

    @Test
    void testFindById() {
        UUID productId = UUID.randomUUID();
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(product);

        Product found = productService.findById(productId);
        assertSame(product, found);
        verify(productRepository).findById(productId);
    }

    @Test
    void testEdit() {
        UUID productId = UUID.randomUUID();
        Product changes = new Product();
        Product edited = new Product();
        when(productRepository.edit(changes, productId)).thenReturn(edited);

        Product result = productService.edit(changes, productId);
        assertSame(edited, result);
        verify(productRepository).edit(changes, productId);
    }

    @Test
    void testDelete() {
        UUID productId = UUID.randomUUID();
        productService.delete(productId);
        verify(productRepository).delete(productId);
    }
}