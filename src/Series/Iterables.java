package Series;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;

public class Iterables {

    public static <K,U> Iterable<K> filterBy(Iterable<K> src1, Iterable<U> src2, BiPredicate<K,U> predicate){

        return new Iterable<K>() {

            @Override
            public Iterator<K> iterator() {

                return new Iterator<K>() {
                    Iterator<K> itr1 = src1.iterator();
                    Iterator<U> itr2 = src2.iterator();

                    K element = null;

                    @Override
                    public boolean hasNext() {

                        while(itr1.hasNext() && itr2.hasNext()){
                            K tmp1 = itr1.next();
                            U tmp2 = itr2.next();

                            if(predicate.test(tmp1,tmp2)){
                                element = tmp1;
                                return true;
                            }
                        }
                        return false;

                    }

                    @Override
                    public K next() {
                       return element;
                    }
                };

            }


        };
    }

    public static <K,V> Iterable<V> filterByMap(Iterable<K> src, Map<K,V> map){
        return new Iterable<V>() {

            @Override
            public Iterator<V> iterator() {

                return new Iterator<V>(){

                    Iterator<K> itr = src.iterator();
                    V element = null;

                    @Override
                    public boolean hasNext() {
                        while(itr.hasNext()){
                            K tmp = itr.next();
                            element = map.get(tmp);
                            if(element != null) return true;
                        }

                        return false;
                    }

                    @Override
                    public V next() {
                        return element;
                    }
                };
            }
            };
    }
}
