package com.apps.restinventory.repository;


import com.apps.restinventory.model.Reorder;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class ReorderRepository extends SimpleJpaRepository<Reorder, String> {
    private final EntityManager em;
    public ReorderRepository(EntityManager em) {
        super(Reorder.class, em);
        this.em = em;
    }
    @Override
    public List<Reorder> findAll() {
        return em.createNativeQuery("Select * from \"restinventoryv1\".\"Reorder\"", Reorder.class).getResultList();
    }
}