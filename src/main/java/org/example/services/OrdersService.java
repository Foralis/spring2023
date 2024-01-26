package org.example.services;

import org.example.models.Order;
import org.example.models.User;
import org.example.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final BooksService booksService;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, BooksService booksService) {
        this.ordersRepository = ordersRepository;
        this.booksService = booksService;
    }

    public List<Order> findAll() {
        return ordersRepository.findAll();
    }

    public boolean isBooked(int id) {
        return ordersRepository.findOrderByBookIdExists(id);
    }


    public void deleteOrderByBookId(int bookId) {
        ordersRepository.deleteOrderByBookId(bookId);
    }

    public void order(User user, int bookId) {
        Order order = new Order();
        order.setUser(user);
        order.setBook(booksService.showBook(bookId));
        ordersRepository.save(order);
    }
}
