package serie2_tests;

import Series.Node;
import org.junit.Test;

import static Series.ListUtils.getMiddle;
import static org.junit.Assert.*;
import static serie2_tests.ListUtilTest.getRandomList;

public class GetMiddleTest {

    @Test
    public void getMiddle_empty_list() {
        Node<Integer> list = ListUtilTest.emptyListWithoutSentinel();
        assertEquals(null,getMiddle(list));
    }

    @Test
    public void getMiddle_singleton_list() {
        Node<Integer> list = getRandomList(1);
        assertEquals(list,getMiddle(list));
    }

    @Test
    public void getMiddle_list_with_odd_dimension() {
        Node<Integer> list = ListUtilTest.getListWithoutSentinel(1, 20, 1);
        assertEquals(10,(int)getMiddle(list).value);
    }

    @Test
    public void getMiddle_list_with_even_dimension() {
        Node<Integer> list = ListUtilTest.getListWithoutSentinel(0, 20, 1);
        assertEquals(10,(int)getMiddle(list).value);
    }

}
