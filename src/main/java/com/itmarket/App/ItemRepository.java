package com.itmarket.App;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * {@code ItemRepository} defines methods for working with data
 *
 * @author inoob
 */
public interface ItemRepository  extends CrudRepository<Item,Long> {

    List<Item> findItemByItemNameContaining(String name);
    List<Item> findByItemDescriptionContaining(String description);
    List<Item> findItemByItemPriceBetween(Double low_price,Double max_price);

}
