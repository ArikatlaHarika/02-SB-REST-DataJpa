package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.models.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //DSL(domain specific language)
    List<Product> findByName(String name);

    List<Product> findByPrice(double price);

    List<Product> findByNameAndPrice(String name,double price);

    List<Product> findByCategoryName(String name);

    List<Product> findByCategoryIdAndCategoryName(Long id,String name);

    @Query("select p from Product p INNER JOIN p.category c where c.name=:cName ")
    List<Product> fetchProductByCategoryName(String cName);

    //JPQL-works on class name and variable name
    //select * from Product -JPQL
    //select p from Product as p -JPQL

    //select * from product -NQ
    //Native queries-works on table name and column name
    @Query("SELECT p FROM Product p")
    List<Product> fetchAllProducts();

    @Query(value="SELECT * FROM product", nativeQuery = true)
    List<Product> fetchAllProductsByNative();






}
