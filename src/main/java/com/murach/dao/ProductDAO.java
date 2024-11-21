package com.murach.dao;

import com.murach.dto.Product;

public class ProductDAO extends BaseDAO<Product> {
    public ProductDAO() {
        super(Product.class);
    }
}
