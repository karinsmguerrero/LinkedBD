package data.structures.generics;

public class SimpleList<T> extends BasicList{

    public SimpleList(){
        super();
    }
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
        deleteNodeAux(index);
    }

    private void deleteNodeAux(int index){
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

    public void deleteByContent(T search){
        deleteByContentAux(search);
    }

    private void deleteByContentAux(T search){
        Node<T> actual = head;
        int i = 0;
        while (actual != null){
            if (actual.getValue() == search){
                if (actual == head){
                    head = actual.getNext();
                }
                else if(actual == tail){
                    Node<T> temp = getNode(i);
                    temp.setNext(tail.getNext());
                    tail = temp;
                }
                else {
                    Node<T> temp = getNode(i - 1);
                    temp.setNext(actual.getNext());
                }
            }
            i++;
            actual = actual.getNext();
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
