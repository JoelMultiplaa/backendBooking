package com.example.backendbooking2.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.backendbooking2.Entity.Orderline;
import com.example.backendbooking2.Repository.OrderlineRepository;
import com.example.backendbooking2.Service.OrderlineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class OrderlineServiceTest {

    @Mock
    private OrderlineRepository orderlineRepository;

    @InjectMocks
    private OrderlineService orderlineService;

    private Orderline orderline;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        orderline = new Orderline();
        orderline.setOrderlineId(1L);
        orderline.setQuantity(2);
    }

    @Test
    void testCreateOrderline() {
        // Arrange
        when(orderlineRepository.save(any(Orderline.class))).thenReturn(orderline);

        // Act
        Orderline result = orderlineService.createOrderline(orderline);

        // Assert
        assertNotNull(result);
        assertEquals(orderline.getOrderlineId(), result.getOrderlineId());
        verify(orderlineRepository, times(1)).save(orderline);
    }

    @Test
    void testGetAllOrderline() {
        // Arrange
        List<Orderline> orderlines = Arrays.asList(orderline);
        when(orderlineRepository.findAll()).thenReturn(orderlines);

        // Act
        Optional<List<Orderline>> result = orderlineService.getAllOrderline();

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
        verify(orderlineRepository, times(1)).findAll();
    }

    @Test
    void testGetOrderlineById() {
        // Arrange
        when(orderlineRepository.findById(1L)).thenReturn(Optional.of(orderline));

        // Act
        Optional<Orderline> result = orderlineService.getOrderlineById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(orderline.getOrderlineId(), result.get().getOrderlineId());
        verify(orderlineRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateOrderline() {
        // Arrange
        Orderline updatedOrderline = new Orderline();
        updatedOrderline.setQuantity(5);

        when(orderlineRepository.findById(1L)).thenReturn(Optional.of(orderline));
        when(orderlineRepository.save(any(Orderline.class))).thenReturn(orderline);

        // Act
        Optional<Orderline> result = orderlineService.updateOrderline(1L, updatedOrderline);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(5, result.get().getQuantity());
        verify(orderlineRepository, times(1)).findById(1L);
        verify(orderlineRepository, times(1)).save(any(Orderline.class));
    }

    @Test
    void testDeleteOrderline() {
        // Arrange
        when(orderlineRepository.findById(1L)).thenReturn(Optional.of(orderline));

        // Act
        orderlineService.deleteOrderline(1L);

        // Assert
        verify(orderlineRepository, times(1)).deleteById(1L);
    }
}
