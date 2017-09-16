package data.structures.generics;

public class DoubleNode<T>{
    DoubleNode<T> previous, next;
    T value;

    public DoubleNode(T value){
        this.value = value;
        this.previous = previous = null;
    }

    public DoubleNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }

    public DoubleNode<T> getNext() {
        return next;
    }

    public T getValue() {
        return this.value;
    }

    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

}
