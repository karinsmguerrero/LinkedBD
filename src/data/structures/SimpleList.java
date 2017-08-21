package data.structures;

public class SimpleList extends BasicList{
    private Node current;
    private  int size;

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

    //Eliminarlo después
    public void printList(){
        Node actual = head;
        for(int i = 0; i < size; i++)
        {
            System.out.print("[" + actual.getValue() + "] -> ");
            actual = actual.getNext();
        }

    }
}
