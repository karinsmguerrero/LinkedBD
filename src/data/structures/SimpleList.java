package data.structures;

public class SimpleList {
    private Node head;
    private Node current;
    private  int size;

    public  SimpleList ()
    {
        this.head = null;
        this.size = 0;
    }

    public int getSize(){
        return size;
    }

    public Node getHead(){
        return head;
    }

    public void addNode(Object value){
        Node newest = new Node(value);
        if (isEmpty()) {
            head = newest;
            current = newest;
        }
        else {
            current.setNext(newest);
            current = newest;
        }
        size++;
    }

    public boolean isEmpty()
    {
        if (head == null)
            return true;
        else
            return false;
    }

    //Eliminarlo después
    public void printList(){
        Node actual = head;
        for(int i = 0; i < size; i++)
        {
            System.out.print("[" + actual.getValue() + "] -> ");
            actual = actual.getNext();
        }

    }

    public void getNode(int index){
        int i = 0;
        Node actual = head;
        while (i < index){
            if(i == index)
                break;
            actual = actual.getNext();
            i++;
        }
        System.out.println(actual.getValue());
    }

    public void deleteNode(int index){
        Node actual = head;

        if (index == 0){
            head = actual.getNext();
        }
        else {
            int i = 0;
            while (i < index - 1){
                actual = actual.getNext();
                i++;
            }
            actual.setNext(actual.getNext().getNext());
        }
        size--;
    }
}
