package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;

    private Order mockOrder;

    private Map<String, String> voucherPayment = new HashMap<>();

    private Map<String, String> codPayment = new HashMap<>();

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
        paymentRepository = new PaymentRepository();
        voucherPayment.put("voucherCode", "ESHOP1234ABC5678");
        codPayment.put("address", "Kukusan");
        codPayment.put("deliveryFee", "20000");

        mockOrder = Order.builder().id("787c1e14-8383-4308-b2d5-f924b9d588b8")
                .products(getProducts())
                .orderTime(1708560000L)
                .author("lenpalen")
                .status(OrderStatus.WAITING_PAYMENT.getValue())
                .build();
    }

    @Test
    void testAddPayment() {
        Payment payment = paymentRepository.addPayment(PaymentMethod.VOUCHER_CODE.getValue(), voucherPayment, mockOrder);
        assertNotNull(payment);
    }

    @Test
    void testAddPaymentThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = paymentRepository.addPayment(null, null, null);
        });
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = paymentRepository.addPayment(PaymentMethod.VOUCHER_CODE.getValue(),
                voucherPayment, mockOrder);
        String newStatus = PaymentStatus.SUCCESS.getValue();

        Payment updatedPayment = paymentRepository.setStatus(payment, newStatus);
        assertEquals(
                newStatus,
                updatedPayment.getPaymentStatus()
        );
        assertEquals(
                newStatus,
                mockOrder.getStatus()
        );
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = paymentRepository.addPayment(PaymentMethod.VOUCHER_CODE.getValue(),
                voucherPayment, mockOrder);
        String newStatus = PaymentStatus.REJECTED.getValue();

        Payment updatedPayment = paymentRepository.setStatus(payment, newStatus);
        assertEquals(
                newStatus,
                updatedPayment.getPaymentStatus()
        );
        assertEquals(
                OrderStatus.FAILED.getValue(),
                mockOrder.getStatus()
        );
    }


    @Test
    void testSetStatusSuccessToRejected() {
        Payment payment = paymentRepository.addPayment(PaymentMethod.VOUCHER_CODE.getValue(),
                voucherPayment, mockOrder);
        String initialStatus = PaymentStatus.SUCCESS.getValue();
        payment.setPaymentStatus(initialStatus);

        String newStatus = PaymentStatus.REJECTED.getValue();
        Payment updatedPayment = paymentRepository.setStatus(payment, newStatus);
        assertEquals(
                initialStatus,
                updatedPayment.getPaymentStatus()
        );
    }

    @Test
    void testSetStatusRejectedToSuccess() {
        Payment payment = paymentRepository.addPayment(PaymentMethod.VOUCHER_CODE.getValue(),
                voucherPayment, mockOrder);
        String initialStatus = PaymentStatus.REJECTED.getValue();
        payment.setPaymentStatus(initialStatus);

        String newStatus = PaymentStatus.SUCCESS.getValue();
        Payment updatedPayment = paymentRepository.setStatus(payment, newStatus);
        assertEquals(
                initialStatus,
                updatedPayment.getPaymentStatus()
        );
    }

    @Test
    void testGetPaymentFound() {
        Payment payment1 = paymentRepository.addPayment(PaymentMethod.VOUCHER_CODE.getValue(),
                voucherPayment, mockOrder);
        Payment payment2 = paymentRepository.addPayment(PaymentMethod.COD.getValue(),
                codPayment, mockOrder);

        Payment retrievedPayment = paymentRepository.getPayment(payment2.getPaymentId());
        assertEquals(
                payment2.getPaymentId(),
                retrievedPayment.getPaymentId()
        );
    }

    @Test
    void testGetPaymentNotFound() {
        paymentRepository.addPayment(PaymentMethod.VOUCHER_CODE.getValue(), voucherPayment, mockOrder);
        paymentRepository.addPayment(PaymentMethod.COD.getValue(), codPayment, mockOrder);

        Payment retrievedPayment = paymentRepository.getPayment("00000000-0000-0000-0000-000000000000");
        assertNull(retrievedPayment);
    }

    @Test
    void testGetAllPaymentsNotEmpty() {
        paymentRepository.addPayment(PaymentMethod.VOUCHER_CODE.getValue(), voucherPayment, mockOrder);
        paymentRepository.addPayment(PaymentMethod.COD.getValue(), codPayment, mockOrder);

        Iterator<Payment> paymentsIterator = paymentRepository.getAllPayments();
        assertNotNull(paymentsIterator);
        assertTrue(paymentsIterator.hasNext());
    }

    @Test
    void testGetAllPaymentsEmpty() {
        Iterator<Payment> paymentsIterator = paymentRepository.getAllPayments();
        assertFalse(paymentsIterator.hasNext());
    }

}