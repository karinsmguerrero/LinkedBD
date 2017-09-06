package data.generics.structures;

public class SimpleList<T> extends BasicList{
    public void addNode(T value){
        Node<T> newest = new Node(value);
        if (isEmpty()) {
            head = tail = newest;
        }
        else {
            tail.setNext(newest);
            tail = newest;
        }
        size++;
    }

    public void deleteNode(int index){
        Node<T> actual = head;

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
            if(index == size - 1){
                tail = tail.getNext();
            }
        }
        size--;
    }

    //Eliminarlo después
    public void printList(){
        Node<T> actual = head;
        for(int i = 0; i < size; i++)
        {
            System.out.print("[" + actual.getValue() + "] -> ");
            actual = actual.getNext();
        }

    }
}
