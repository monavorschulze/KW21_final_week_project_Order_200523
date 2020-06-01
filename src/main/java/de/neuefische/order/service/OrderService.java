package de.neuefische.order.service;

import de.neuefische.order.db.OrderDB;
import de.neuefische.order.db.ProductDB;
import de.neuefische.order.model.Order;
import de.neuefische.order.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService {

    private ProductDB productsDB;
    private OrderDB orderDB;


    public OrderService(ProductDB productsDB){
        this.productsDB = productsDB;

    }

    public OrderService(ProductDB productDB, OrderDB orderDB) {
        this.orderDB =orderDB;
    }

    public Order orderProducts(ArrayList<String> orderIds) {
        ArrayList<Product> products = new ArrayList<>();

        for (String productId : orderIds){
            Product product = productsDB.getProductById(productId);
            products.add(product);
        }
        String uuid = UUID.randomUUID().toString();
        Order order = new Order(uuid, products);

        orderDB.addOrder(order);
        return new Order("2", products);


    }

    public List<Order> listOrders() {
        return orderDB.listOrders();
    }
}
