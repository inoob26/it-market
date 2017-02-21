import com.itmarket.App.AppConfig;
import com.itmarket.App.ItemRepository;
import com.itmarket.App.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import java.util.Arrays;
import java.util.List;

/**
 * {@code AppTest} class contains unit tests with data
 *
 *
 * @author inoob
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class AppTest {

    @Autowired
    private ItemRepository itemRepository;


    /**
    * fill table
    * */
    @Before
    @Rollback(false)
    public void setUp(){

        Item macBookPro = new Item("MacBookPro 13.3 A1278",
                "Core i5 2,3 GHz RAM 4GB SSD 128 GB",
                45000.00);
        Item lenovo = new Item("Lenovo g580",
                "Core i3 2348M 2300 Mhz RAM 6gb DDR3 HDD 1TB",
                40000.0);
        Item asus = new Item("ASUS ZENBOOK UX305CA",
                "Intel core m3 6Y30 900 Mhz RAM 8gb DDR3 SSD 256 gb",
                63535.0);
        Item macBookRetina = new Item("Apple MacBook Pro 13 with Retina display Early 2015 MF839",
                "Core i5 2700 Mhz RAM 8 Гб LPDDR3 SSD 128gb",
                83900.0);
        itemRepository.save(Arrays.asList(macBookPro,lenovo,asus,macBookRetina));
    }

    /**
     * get all elements from database
     */
    @Test
    public void getAllItems(){
        Iterable<Item> items = itemRepository.findAll();
        for (Item item : items){
            System.out.println(item.toString());
        }
    }

    /**
     * get element by id from database
     */
    @Test
    public void getItemById(){
        Item item = itemRepository.findOne((long) 1);
        System.out.println(item.toString());
    }

    /**
     * get elements containing some words into description
     */
    @Test
    public void testFindItemByContainingDescription(){
        List<Item> items = itemRepository.findByItemDescriptionContaining("Core i5");
        items.forEach(i -> System.out.println(i.toString()));
    }


    /**
     * get elements containing some words into name
     */
    @Test
    public void testFindItemByItemNameContaining(){
        List<Item> items = itemRepository.findItemByItemNameContaining("ZEN");
        items.forEach(i -> System.out.println(i.toString()));
    }

    /**
     * get all items between low and max defined price
     */
    @Test
    public void testFindPriceBetweenLowAndMax(){
        List<Item> items = itemRepository.findItemByItemPriceBetween(44000.00,65000.00);
        items.forEach(i-> System.out.println(i.toString()));
    }
}
