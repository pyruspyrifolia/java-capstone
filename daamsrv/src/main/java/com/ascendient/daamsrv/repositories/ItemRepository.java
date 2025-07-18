// src/main/java/com/restaurant/api/repositories/ItemRepository.java
package com.ascendient.daamsrv.repositories;

import com.ascendient.daamsrv.entities.Item;
// jpa repository gives us access to many different crud functions
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    /**
     * Finds a list of items associated with a specific order ID.
     * @param orderid The ID of the order.
     * @return A list of items belonging to the specified order.
     */
    List<Item> findByOrderid(Long orderid);

    /**
     * Deletes all items associated with a specific order ID.
     * This method is typically used within a transactional context.
     * @param orderid The ID of the order.
     */
    void deleteByOrderid(Long orderid);
}