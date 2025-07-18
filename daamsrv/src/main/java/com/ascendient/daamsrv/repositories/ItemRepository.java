package com.ascendient.daamsrv.repositories;

import com.ascendient.daamsrv.entities.Item;
// jpa repository gives us access to many different crud functions
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByOrderid(Long orderid);

    void deleteByOrderid(Long orderid);
}