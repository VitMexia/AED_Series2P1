package Series;


import java.util.Iterator;

public class Node<E>  {

    public E value;
    public Node<E> previous;
    public Node<E> next;

    public Node(){}

    public Node(E e){value=e;}

}
