package data.structures;

public class Node {

    private Object value;
    private Node next;
    private Node previous;

    public Node(Object value){
        //Llama el otro constructor y le envía dos parametros nulos
        this(value, null, null);
    }

    public Node (Object value, Node previous, Node next)
    {
        this.value = value;
        this.previous = previous;
        this.next = next;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getPrevious() {
        return previous;
    }
}
