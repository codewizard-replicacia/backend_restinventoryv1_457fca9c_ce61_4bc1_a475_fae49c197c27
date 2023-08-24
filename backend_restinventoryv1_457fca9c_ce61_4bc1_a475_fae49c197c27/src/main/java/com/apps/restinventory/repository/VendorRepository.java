package com.apps.restinventory.repository;


import com.apps.restinventory.model.Vendor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class VendorRepository extends SimpleJpaRepository<Vendor, String> {
    private final EntityManager em;
    public VendorRepository(EntityManager em) {
        super(Vendor.class, em);
        this.em = em;
    }
    @Override
    public List<Vendor> findAll() {
        return em.createNativeQuery("Select * from \"restinventoryv1\".\"Vendor\"", Vendor.class).getResultList();
    }
}