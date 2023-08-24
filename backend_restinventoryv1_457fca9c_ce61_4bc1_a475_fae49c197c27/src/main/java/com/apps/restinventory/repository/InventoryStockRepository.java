package com.apps.restinventory.repository;


import com.apps.restinventory.model.InventoryStock;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class InventoryStockRepository extends SimpleJpaRepository<InventoryStock, String> {
    private final EntityManager em;
    public InventoryStockRepository(EntityManager em) {
        super(InventoryStock.class, em);
        this.em = em;
    }
    @Override
    public List<InventoryStock> findAll() {
        return em.createNativeQuery("Select * from \"restinventoryv1\".\"InventoryStock\"", InventoryStock.class).getResultList();
    }
}