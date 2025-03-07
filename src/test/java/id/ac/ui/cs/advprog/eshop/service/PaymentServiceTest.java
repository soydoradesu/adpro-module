package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    private Order mockOrder;

    private Payment mockPayment;

    private Map<String, String> voucherPayment;

    private Map<String, String> codPayment;

    private static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId(UUID.fromString("a2c62328-4a37-4664-83c7-f32db8620155"));
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);

        products.add(product1);
        products.add(product2);
        return products;
    }

    @BeforeEach
    void setUp() {
        mockOrder = Order.builder().id("787c1e14-8383-4308-b2d5-f924b9d588b8")
                .products(getProducts())
                .orderTime(1708560000L)
                .author("lenpalen")
                .status(OrderStatus.WAITING_PAYMENT.getValue())
                .build();

        voucherPayment = new HashMap<>();
        voucherPayment.put("voucherCode", "ESHOP1234ABC5678");

        codPayment = new HashMap<>();
        codPayment.put("address", "Kukusan");
        codPayment.put("deliveryFee", "20000");

        mockPayment = new Payment("VOUCHER_CODE", voucherPayment, mockOrder);
    }

    @Test
    void testAddPaymentVoucherCode() {
        when(paymentRepository.addPayment(eq("VOUCHER_CODE"), anyMap(), any(Order.class))).thenReturn(mockPayment);

        Payment payment = paymentService.addPayment(mockOrder, "VOUCHER_CODE", voucherPayment);

        assertNotNull(payment);
        assertEquals("VOUCHER_CODE", payment.getPaymentMethod());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
        verify(paymentRepository, times(1)).addPayment(eq("VOUCHER_CODE"), anyMap(), any(Order.class));
    }

    @Test
    void testAddPaymentCOD() {
        Payment codMockPayment = new Payment("COD", codPayment, mockOrder);
        when(paymentRepository.addPayment(eq("COD"), anyMap(), any(Order.class))).thenReturn(codMockPayment);

        Payment payment = paymentService.addPayment(mockOrder, "COD", codPayment);

        assertNotNull(payment);
        assertEquals("COD", payment.getPaymentMethod());
        assertEquals("Kukusan", payment.getPaymentData().get("address"));
        assertEquals("20000", payment.getPaymentData().get("deliveryFee"));
        verify(paymentRepository, times(1)).addPayment(eq("COD"), anyMap(), any(Order.class));
    }

    @Test
    void testSetStatus() {
        when(paymentRepository.setStatus(any(Payment.class), anyString())).thenReturn(mockPayment);

        Payment updatedPayment = paymentService.setStatus(mockPayment, "SUCCESS");

        assertNotNull(updatedPayment);
        assertEquals(mockPayment, updatedPayment);
        verify(paymentRepository, times(1)).setStatus(any(Payment.class), anyString());
    }

    @Test
    void testGetPayment() {
        when(paymentRepository.getPayment(anyString())).thenReturn(mockPayment);

        Payment retrievedPayment = paymentService.getPayment("00000000-0000-0000-0000-000000000000");

        assertNotNull(retrievedPayment);
        assertEquals(mockPayment, retrievedPayment);
        verify(paymentRepository, times(1)).getPayment(anyString());
    }

    @Test
    void testGetAllPayments() {
        List<Payment> paymentList = Arrays.asList(mockPayment, mockPayment);
        Iterator<Payment> paymentIterator = paymentList.iterator();
        when(paymentRepository.getAllPayments()).thenReturn(paymentIterator);

        List<Payment> retrievedPayments = paymentService.getAllPayments();

        assertNotNull(retrievedPayments);
        assertEquals(2, retrievedPayments.size());
        verify(paymentRepository, times(1)).getAllPayments();
    }

}