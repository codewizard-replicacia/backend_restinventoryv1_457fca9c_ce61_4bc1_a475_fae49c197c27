package com.apps.restinventory.repository;


import com.apps.restinventory.model.ProductStore;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class ProductStoreRepository extends SimpleJpaRepository<ProductStore, String> {
    private final EntityManager em;
    public ProductStoreRepository(EntityManager em) {
        super(ProductStore.class, em);
        this.em = em;
    }
    @Override
    public List<ProductStore> findAll() {
        return em.createNativeQuery("Select * from \"restinventoryv1\".\"ProductStore\"", ProductStore.class).getResultList();
    }
}