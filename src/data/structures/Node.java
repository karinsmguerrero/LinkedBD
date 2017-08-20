package data.structures;

public class Node {
    int value;
    Node next;
    Node previous;

    public Node (int value)
    {
        this.value = value;
        this.previous = null;
        this.next = null;

    }
}
