package com.itmarket.App;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * {@code Item} this class describe item
 * it keep next information:
 * Item name , Item price and description
 *
 * @author inoob
 * @see Item
 */
@Entity
@Table(name = "item")
@NoArgsConstructor
public class Item {
    @Id @Column(name = "iditem",length = 6, nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Getter private long id;

    @Column(name = "itemname",length = 100, nullable = false)
    @Getter @Setter
    private String itemName;

    @Column(name = "itemdescription",length = 100, nullable = false)
    @Getter @Setter
    private String itemDescription;

    @Column(name = "itemprice", nullable = false)
    @Getter @Setter
    private Double itemPrice;

    public Item(String name, String description, Double price){
        this.itemName = name;
        this.itemDescription = description;
        this.itemPrice = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
