package com.apps.restinventory.repository;


import com.apps.restinventory.model.ProductCatalogue;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class ProductCatalogueRepository extends SimpleJpaRepository<ProductCatalogue, String> {
    private final EntityManager em;
    public ProductCatalogueRepository(EntityManager em) {
        super(ProductCatalogue.class, em);
        this.em = em;
    }
    @Override
    public List<ProductCatalogue> findAll() {
        return em.createNativeQuery("Select * from \"restinventoryv1\".\"ProductCatalogue\"", ProductCatalogue.class).getResultList();
    }
}