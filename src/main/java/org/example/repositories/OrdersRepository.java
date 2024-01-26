package org.example.repositories;

import org.example.models.Order;
import org.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {
    void deleteOrderByBookId(int id);
    //boolean findOrderByBookId(int id);
    boolean findOrderByBookIdExists(int id);
}
