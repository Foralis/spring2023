package org.example.services;

import org.example.models.Book;
import org.example.models.Order;
import org.example.models.User;
import org.example.repositories.BooksRepository;
import org.example.repositories.OrdersRepository;
import org.example.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrdersService {
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Order> findAll() {
        return ordersRepository.findAll();
    }


    Перенсти в BooksRepository и сделать там hql запрос возвращающий нужные книги
    public List<Book> getAllBooksOrderedByUser(User user) {
        List<Book> foundBooks = ordersRepository.findByUser(user);
        return foundBooks;
    }
}
