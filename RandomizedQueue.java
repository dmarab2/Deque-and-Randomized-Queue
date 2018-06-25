import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class RandomizedQueue<Item> implements Iterable<Item>{
    private int size;
    private Item[] array;
    private class RandomizedQueueIterator implements Iterator<Item>{
        private int i;
        private Item[] subArray;
        public RandomizedQueueIterator(){
            i = 0;
            subArray = (Item[]) new Object[size];
            for(int j = 0; j < subArray.length; j++){
                subArray[j] = array[j];
            }
            StdRandom.shuffle(subArray);
        }           
        public boolean hasNext(){
            return i < subArray.length;
        }
        public void remove(){
            throw new UnsupportedOperationException("You cannot use the operation Remove with this iterator.");
        }
        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException("There are no elements left.");
            }
            return subArray[i++];
        }
    }
        
    public RandomizedQueue(){
        array = (Item[]) new Object[2];
        size = 0;
    }
    public boolean isEmpty(){
        return (size == 0);
    }
    public int size(){
        return size;
    }
    public void enqueue(Item item){
        if (item == null){
            throw new IllegalArgumentException("You did not provide an arguement.");
        }
        if(array.length == size){
            resize(array.length * 2);
        }
        array[size++] = item;
    }
    public Item dequeue(){
        checkIfEmpty();
        int rand = StdRandom.uniform(size);
        Item item = array[rand];
        if(rand != (size - 1)){
            array[rand] = array[size - 1];
        }
        array[size - 1] = null;
        size--;
        if(size > 0 && size <= (array.length/4)){
            resize(array.length / 2);
        }
        return item;
    }
    
    public Item sample(){
        checkIfEmpty();
        int rand = StdRandom.uniform(size);
        Item item = array[rand];
        return item;
    }
    
    public Iterator<Item> iterator(){
        return new RandomizedQueueIterator();
    }
    
    private void resize(int capacity){
        Item[] copy = (Item[]) new Object[capacity];
        for(int i = 0; i < size; i++){
            copy[i] = array[i];
        }
        array = copy;
    }
    private void checkIfEmpty(){
        if (isEmpty()){
            throw new NoSuchElementException("There are no elements left.");
        }
    }
    
    public static void main(String[] args){
        RandomizedQueue<Integer> array = new RandomizedQueue<Integer>();
        for(int i = 1; i < 11; i++){
            array.enqueue(i);
        }
        for(int i : array){
            System.out.println(i);
        }
        System.out.println("The above is to test the random iterator...");
        System.out.println(" ");
        while(!array.isEmpty()){
            System.out.println(array.dequeue());
        }
    }
    
}