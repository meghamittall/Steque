/*
 *  File: Steque.java
 *  Author: 
 *  Date: 18th Nov, 2021
 *  ---------------------------------------
 *  Steque is stack-ended queue data structure which supports
 *  stack operations: pop and push, along with queue enqueue 
 *  operation.
 *  
 *  Salient features:
 *  1. Operations like push, pop, enqueue are supported.
 *  2. NullPointerException is thrown when null element is inserted.
 *  3. UnsupportedOperationException is thrown when using remove() method.
 *  4. The data structure is iterable and is implemented for generic type.
 *  
 */

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * 
 * Steque is a stack-ended data structure which 
 * supports stack operations as well as queue's 
 * enqueue operation.
 * 
 * @author 
 * @version 1.0
 *
 */
public class StequeArrays<Item> implements Iterable<Item> {
    private Item[] array = (Item[]) new Object[8]; 
    private int n = 0;
    /**
     * constructs a steque object.
     */
    public StequeArrays() {

    }
    
    
    /**
     * inserts an item in the steque in queue fashion.
     * @param item Item to be inserted.
     */
    public void enqueue(Item item) {
        if (item==null){
            throw new IllegalArgumentException("pass an element to enqueue");
        }
        if (n==array.length){
            Item[] newArray = (Item[]) new Object[n*2];
            for(int i=0; i < n; i++){
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[n++]=item;
    }
    
    
    /**
     * inserts an item in the steque in stack fashion.
     * @param item Item to be inserted.
     */
    public void push(Item item) {
        if (item==null){
            throw new IllegalArgumentException("pass an element to push");
        }
        if (n==array.length){
            Item[] newArray = (Item[]) new Object[n*2];
            for(int i=0; i < n; i++){
                newArray[i] = array[i];
            }
            array = newArray;
        }  
        for(int i=n; i>0; i--){
            array[i] = array[i-1];
        }
        array[0]=item;
        n++;
    }
    
    /**
     * pops a least recent item in steque.
     * @return Item object from steque.
     */
    public Item pop(){

        if(isEmpty()){
            throw new IllegalStateException("There is no element to pop");
        }
        if(n < array.length/4){
            Item[] newArray = (Item[]) new Object[n*2];
            for(int i=0; i < n; i++){
                newArray[i] = array[i];
            }
            array = newArray;
        }
        Item pop = array[0];
        for (int i = 0; i <= n; i++) {
            array[i] = array[i+1];
        }
        n--;
        return pop;
    }

    
    /**
     * checks to see if steque is empty.
     * @return true if steque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return n == 0;
    }
    
    /**
     * return the number of elements currently in the steque.
     * @return size as integer.
     */
    public int size() {
        return n;
    }
    
    /**
     * returns an iterator over the elements 
     * stored in steque.
     * 
     */
    public Iterator<Item> iterator() {
        return new StequeArraysIterator();
    }
    private class StequeArraysIterator implements Iterator<Item> {
        private int i = 0;
        public boolean hasNext(){
            return i < n;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()){
                throw new NoSuchElementException("steque running out of element");
            }
            Item item = array[i];
            i++;
            return item;
        }
    }

    public static void main(String[] args){
        StequeArrays<Integer> steque =new StequeArrays<Integer>();
        steque.push(40);
        steque.enqueue(10);
        steque.push(30);
        steque.enqueue(70);
        steque.push(50);
        steque.pop();
        Iterator<Integer> iterate = steque.iterator();

        System.out.println("Elements in steque:");

        while(iterate.hasNext()){
            System.out.println(iterate.next());

        }
    }
}