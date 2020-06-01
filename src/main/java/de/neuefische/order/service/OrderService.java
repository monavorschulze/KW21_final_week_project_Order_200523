package de.neuefische.order.service;

import de.neuefische.order.db.OrderDB;
import de.neuefische.order.db.ProductDB;
import de.neuefische.order.model.Order;
import de.neuefische.order.model.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderService {

    private ProductDB productsDB;
    private OrderDB orderDB;


    public OrderService(ProductDB productDB, OrderDB orderDB) {
        this.productsDB = productDB;
        this.orderDB = orderDB;
    }

    public Order orderProducts(ArrayList<String> orderIds) {
        ArrayList<Product> products = new ArrayList<>();

        for (String productId : orderIds) {
            Optional<Product> optionalProduct = productsDB.getProductById(productId);
            if (optionalProduct.isPresent()) {
                products.add(optionalProduct.get());
            } else {
                throw new RuntimeException("Product with id" + productId + "not found");
            }
        }

            String uuid = UUID.randomUUID().toString();
            Order order = new Order(uuid, products);

            orderDB.addOrder(order);
            return order;
        }


    public List<Order> listOrders() {
        return orderDB.listOrders();
    }
}