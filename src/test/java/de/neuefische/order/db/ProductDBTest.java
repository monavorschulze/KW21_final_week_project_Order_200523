package de.neuefische.order.db;

import de.neuefische.order.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDBTest {


    @Test
    @DisplayName("List products return all products")

    public void listProducts(){
        //GIVEN

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("1","Bommel"));
        products.add(new Product("2","Pinguin"));
        products.add(new Product("3","Ananas"));


        ProductDB db = new ProductDB(products);

        //WHEN
        List<Product> result = db.listProducts();

        //THEN
        assertEquals(result.size(), products.size());
        assertTrue(result.contains(new Product("1","Bommel")));
        assertTrue(result.contains(new Product("2","Pinguin")));
        assertTrue(result.contains(new Product("3","Ananas")));
    }

}