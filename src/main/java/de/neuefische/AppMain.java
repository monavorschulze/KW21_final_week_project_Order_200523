package de.neuefische;

import de.neuefische.order.db.OrderDB;
import de.neuefische.order.db.ProductDB;
import de.neuefische.order.model.Order;
import de.neuefische.order.model.Product;
import de.neuefische.order.service.OrderService;

import java.util.ArrayList;
import java.util.List;

public class AppMain {


    public static void main(String[] args) {


        ProductDB productDB = setupProduct();
        OrderDB orderDB = new OrderDB();

        printProducts(productDB);


        // Order Product
        OrderService orderService = new OrderService(productDB, orderDB);

        ArrayList<String> orderIds = SetupOrder();

        Order order = orderService.orderProducts(orderIds);

        ArrayList<String> secondOrderIds = SetupOrder();
        Order secondOrder = orderService.orderProducts(secondOrderIds);

        printOrders(orderDB);

        // Print Orders
        List<Order> orderList = orderService.listOrders();

        for (Order printOrder : orderList) {
            System.out.println(printOrder);
        }
    }

    private static ArrayList<String> SetupOrder() {
        ArrayList<String> orderIds = new ArrayList<>();
        orderIds.add("1");
        orderIds.add("3");
        return orderIds;
    }


    private static ProductDB setupProduct() {
        ArrayList<Product> initialProducts = new ArrayList<>();
        initialProducts.add(new Product("1","Bommel"));
        initialProducts.add(new Product("2","Pinguin"));
        initialProducts.add(new Product("3","Ananas"));
        return new ProductDB (initialProducts);
    }

    private static void printProducts(ProductDB db){
        List<Product> products = db.listProducts();
        System.out.println("All Products");
        for (Product product : products) {
            System.out.println(product);
        }

}
    private static void printOrders(OrderDB db) {
        List<Order> orders = db.listOrders();
        System.out.println("All Orders");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}