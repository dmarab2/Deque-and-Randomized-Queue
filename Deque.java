import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item>{
    private Node first;
    private Node last;
    private int size;
    private class Node{
        Item item;
        Node next;
        Node prev;
        public Node(Item i){
            item = i;
            next = null;
            prev = null;
        }
    }
    private class DequeIterator implements Iterator<Item>{
        private Node current;
        public DequeIterator(){
            current = first;
        }
        public boolean hasNext(){
            return current != null;
        }
        public void remove(){
            throw new UnsupportedOperationException("You cannot use the operation Remove with this iterator.");
        }
        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException("There are no elements left.");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public Deque(){
        first = null;
        last = null;
        size = 0;
    }
    public boolean isEmpty(){
        return (size == 0);
    }
    public int size(){
        return size;
    }
    public void addFirst(Item item){
        if (item == null){
            throw new IllegalArgumentException("You did not provide an arguement.");
        }
        if(first == null){
            first = new Node(item);
            last = first;
            size++;
        }
        else{
            Node oldFirst = first;
            first = new Node(item);
            first.next = oldFirst;
            oldFirst.prev = first;
            size++;
        }
    }
    public void addLast(Item item){
        if (item == null){
            throw new IllegalArgumentException("You did not provide an arguement.");
        }
        if(last == null){
            last = new Node(item);
            first = last;
            size++;
        }
        else{
            Node oldLast = last;
            last = new Node(item);
            last.prev = oldLast;
            oldLast.next = last;
            size++;
        }
    }
    public Item removeFirst(){
        checkIfEmpty();
        Item item = first.item;
        first = first.next;
        if(first == null){
            last = null;
        }
        else {
            first.prev = null;
        }
        size--;
        return item;
    }
    public Item removeLast(){
        checkIfEmpty();
        Item item = last.item;
        last = last.prev;
        if(last == null){
            first = null;
        }
        else{
            last.next = null;
        }
        size--;
        return item;
    }
    
    private void checkIfEmpty(){
        if (isEmpty()){
            throw new NoSuchElementException("There are no elements left.");
        }
    }
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }
    
        
}
        
