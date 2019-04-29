package Series;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListUtils {

    /*que, dada a lista duplamente ligada, sem sentinela e nao circular, referenciada por list, retorna o no do
       meio da lista. Caso a lista seja de dimens˜ao par, retorna o primeiro nó do meio.*/
    public static <E> Node<E> getMiddle(Node<E> list){

        if(list == null) return null;
        Node<E> midleNode = list;


        Iterator<Node<E>> iterator = new Iterator<Node<E>>() {

            Node<E> current = list;

            @Override
            public boolean hasNext() {

                if(current.next != null) return true;
                return false;
            }

            @Override
            public Node<E> next() {
                if(!hasNext()) throw new NoSuchElementException();
                Node<E> aux = current.next;
                aux.previous = current;
                current = aux;

                return current;
            }
        };

        int nodeCount = 1;
        boolean adjustMidleNode = false;
        while(iterator.hasNext()){
            nodeCount++;

            if(adjustMidleNode)
            {
                midleNode = midleNode.next;
                adjustMidleNode = false;
            }
            if(iterator.next().previous != null && nodeCount % 2 == 0){
                adjustMidleNode = true;
            }

        }
        if(adjustMidleNode) midleNode = midleNode.next;


        return midleNode;
    }

        /*que dada uma sublista duplamente ligada, n˜ao circular e sem sentinela, em que first ´e uma referˆencia
        para o primeiro elemento da sublista e last ´e uma referˆencia para o ´ultimo elemento da sublista, ordena
        a sublista de modo crescente, segundo o algoritmo quicksort e o comparador cmp.*/
    public static <E> void quicksort(Node<E> first, Node<E> last, Comparator<E> cmp){

        if(last!=null && first!=last && first!=last.next){
            Node<E> pivot = partition(first,last, cmp);
            quicksort(first,pivot.previous, cmp);
            quicksort(pivot.next,last, cmp);
        }

    }

    private static <E> Node<E> partition(Node<E> first, Node<E> last, Comparator<E> cmp)
    {
        E x = last.value;
        Node<E> i = first.previous;

        for(Node<E> j=first; j!=last; j=j.next)
        {
            if(cmp.compare( j.value, x )<= 0)
            {
                i = (i!=null) ?  i.next : first;
                E temp = i.value;
                i.value = j.value;
                j.value = temp;
            }
        }
        i = (i != null) ?  i.next : first ;
        E temp = i.value;
        i.value = last.value;
        last.value = temp;
        return i;
    }



//*que dado um array de listas duplamente ligadas, n˜ao circulares e sem sentinela, ordenadas pelo comparador
//cmp, retorna uma lista duplamente ligada, circular e com sentinela, resultante da jun¸c˜ao ordenada, pelo
//comparador, das listas presentes em lists. A lista resultante deve reutilizar os n´os presentes em lists.
//As listas em lists devem ficar vazias.*/
    public static <E> Node<E> merge(Node<E>[] lists, Comparator<E> cmp){

        Node<E> listToReturn = new Node<>();
        listToReturn.next = listToReturn;
        listToReturn.previous = listToReturn;

        if(lists == null || lists.length == 0) return  listToReturn;

        for (int i = 0; i < lists.length; i++) {

           if(lists[i] != null) {

               Iterator<Node<E>> ltrIterator = getIteratorWithSentinel(listToReturn);
               Node<E> aux = !ltrIterator.hasNext() ? listToReturn : ltrIterator.next();

               Iterator<Node<E>> nodeToAddItr = getIterator(lists[i]);
               Node<E> aux2 = new Node<>();

               if (nodeToAddItr.hasNext())
                   aux2 = nodeToAddItr.next();

               if(aux2.value != null) {

                   for (; ; ) {

                       if (aux2.value == null){
                           lists[i] = new Node<E>();
                           break;
                       }


                       Node<E> temp = aux2;

                       if(aux.value == null ){

                           lists[i]=temp.next;
                           nodeToAddItr = getIterator(lists[i]);
                           aux2 = new Node<E>();

                           if (nodeToAddItr.hasNext())
                               aux2 = nodeToAddItr.next();

                           aux.next = temp;
                           aux.previous = temp;
                           aux.next.next = aux;
                           aux.next.previous = aux;

                           aux = ltrIterator.next();
                       }
                       else if (cmp.compare(aux2.value, aux.value) < 0) {


                           lists[i]=temp.next;
                           nodeToAddItr = getIterator(lists[i]);
                           aux2 = new Node<E>();

                           if (nodeToAddItr.hasNext())
                               aux2 = nodeToAddItr.next();

                           temp.previous = aux.previous;
                           temp.previous.next = temp;
                           aux.previous = temp;
                           temp.next = aux;

                       } else {

                           if (ltrIterator.hasNext()){
                               aux = ltrIterator.next();
                           }
                           else {

                               lists[i]=temp.next;
                               nodeToAddItr = getIterator(lists[i]);
                               aux2 = new Node<E>();

                               if (nodeToAddItr.hasNext())
                                   aux2 = nodeToAddItr.next();


                               temp.previous = aux;

                               aux.next = temp;
                               temp.next = listToReturn;

                           }
                       }
                   }
               }


           }

        }

        return listToReturn;
    }

    private static <E> Iterator<Node<E>> getIteratorWithSentinel(Node<E> node){

        return  new Iterator<Node<E>>() {

            Node<E> current = node;
            Node<E> head = node;

            @Override
            public boolean hasNext() {

                if(current.next != null && current.next != head) return true;
                return false;
            }

            @Override
            public Node<E> next() {
                if(!hasNext()) throw new NoSuchElementException();
                Node<E> aux = current.next;
                aux.previous = current;
                current = aux;

                return current;
            }
        };
    }

    private static <E> Iterator<Node<E>> getIterator(Node<E> node){

        return  new Iterator<Node<E>>() {

            Node<E> current = node;

            @Override
            public boolean hasNext() {

                if(current != null) return true;
                return false;
            }

            @Override
            public Node<E> next() {
                if(!hasNext()) throw new NoSuchElementException();
                Node<E> aux = current;
                current = current.next;

                return aux;
            }
        };
    }

    private static  <E>  Node<E> lastNode(Node<E> node){
        while(node.next!=null)
            node = node.next;
        return node;
    }



}
