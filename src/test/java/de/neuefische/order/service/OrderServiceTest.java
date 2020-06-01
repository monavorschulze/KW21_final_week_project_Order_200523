package de.neuefische.order.service;

import de.neuefische.order.db.OrderDB;
import de.neuefische.order.db.ProductDB;
import de.neuefische.order.model.Order;
import de.neuefische.order.model.product.Product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    public OrderServiceTest() {
    }

    @Test
    @DisplayName("Return a new order")
    public void returnNewOrder(){
        //GIVEN

        ArrayList<Product> initialProducts = new ArrayList<>();
        initialProducts.add(new Product("1","Bommel"));
        initialProducts.add(new Product("2","Pinguin"));
        initialProducts.add(new Product("3","Ananas"));

        ProductDB productsDB = new ProductDB(initialProducts);
        OrderService service = new OrderService(productsDB);

        ArrayList<String> orderIds = new ArrayList<>();
        orderIds.add("1");
        orderIds.add("3");

        //WHEN
        Order order = service.orderProducts(orderIds);

        //THEN
        assertEquals(2,order.getProducts().size());
        assertTrue(order.getProducts().contains(new Product("1","Bommel")));
        assertTrue(order.getProducts().contains(new Product("3","Ananas")));
        assertNotNull(order.getId());




    }

    @Test
    @DisplayName("Return Product by Id")
    public void getProductByIdReturnProduct(){

        //GIVEN
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("1","Bommel"));
        products.add(new Product("2","Pinguin"));
        products.add(new Product("3","Ananas"));

        ProductDB db = new ProductDB(products);

        //WHEN
        Optional<Product> result = db.getProductById("3");

        //THEN
        assertEquals(new Product ("3","Ananas"), result.get());
    }

    @Test
    @DisplayName("Two orders have different ids")
    public void twoOrdershavedifferentIds(){

        //GIVEN

        ArrayList<Product> initialProducts = new ArrayList<>();
        initialProducts.add(new Product("1","Bommel"));
        initialProducts.add(new Product("2","Pinguin"));
        initialProducts.add(new Product("3","Ananas"));

        ProductDB productsDB = new ProductDB(initialProducts);
        OrderService service = new OrderService(productsDB);

        ArrayList<String> orderIds = new ArrayList<>();
        orderIds.add("1");
        orderIds.add("2");

        //WHEN
        Order firstOrder = service.orderProducts(orderIds);
        Order secondOrder = service.orderProducts(orderIds);

        //THEN
        assertNotEquals(firstOrder.getId(),secondOrder.getId());
    }


    @Test
    @DisplayName("Add a new order")
    public void AddOrderToDB(){
        //GIVEN
        OrderDB orderDB = new OrderDB();

        ArrayList<Product> initialProducts = new ArrayList<>();
        initialProducts.add(new Product("1","Bommel"));
        initialProducts.add(new Product("2","Pinguin"));
        initialProducts.add(new Product("3","Ananas"));

        ProductDB productsDB = new ProductDB(initialProducts);
        OrderService service = new OrderService(productsDB);

        ArrayList<String> orderIds = new ArrayList<>();
        orderIds.add("1");
        orderIds.add("2");

        //WHEN
        Order order = service.orderProducts(orderIds);
        Order result = orderDB.getOrderById(order.getId());

        //THEN
        assertEquals(2,result.getProducts().size());
        assertTrue(result.getProducts().contains(new Product("1","Bommel")));
        assertTrue(result.getProducts().contains(new Product("3","Ananas")));
        assertEquals(order.getId(),result.getId());
    }

}