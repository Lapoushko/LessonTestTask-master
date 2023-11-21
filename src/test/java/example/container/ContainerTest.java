package example.container;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
public class ContainerTest {
    /**
     * Тест команды add
     */
    @Test
    public void testAdd(){
        Container container = new Container();
        Item item1 = new Item(1);
        Item item2 = new Item(2);
        container.add(item1);
        Assert.assertEquals(1,container.size());
        container.add(item2);
        Assert.assertEquals(2, container.size());
        Assert.assertTrue(Set.of(item1, item2).stream()
                .allMatch(container::contains));
    }

    /**
     * Тест команды remove
     */
    @Test
    public void testRemove(){
        Container container = new Container();
        Item item1 = new Item(1);
        Item item2 = new Item(2);
        container.add(item1);
        container.add(item2);
        container.remove(item1);
        Assert.assertEquals(1,container.size());
        Assert.assertEquals(item2,container.get(0));
    }
}