package Series;

import java.util.Iterator;
import java.util.Map;
import java.util.function.BiPredicate;

public class Iterables {

    public static <K,U> Iterable<K> filterBy(Iterable<K> src1, Iterable<U> src2, BiPredicate<K,U> predicate){

        return new Iterable<K>() {

            @Override
            public Iterator<K> iterator() {

                return new Iterator<K>() {
                    Iterator<K> itr1 = src1.iterator();
                    Iterator<U> itr2 = src2.iterator();

                    K current = null;

                    @Override
                    public boolean hasNext() {
                        if(current != null) return true;
                        while(itr1.hasNext() && itr2.hasNext()){
                            K tmp1 = itr1.next();
                            U tmp2 = itr2.next();

                            if(predicate.test(tmp1,tmp2)){
                                current = tmp1;
                                return true;
                            }
                        }
                        return false;

                    }

                    @Override
                    public K next() {
                        K element = null;
                        if(hasNext()){
                            element = current;
                            current = null;
                        }

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

                    V current = null;
                    @Override
                    public boolean hasNext() {
                        if(current != null) return true;
                        while(itr.hasNext()){
                            K tmp = itr.next();
                            current = map.get(tmp);
                            if(current != null) return true;
                        }

                        return false;
                    }

                    @Override
                    public V next() {
                        V element = null;

                        if(hasNext()){
                            element = current;
                            current = null;
                        }
                        return element;
                    }
                };
            }
            };
    }
}
