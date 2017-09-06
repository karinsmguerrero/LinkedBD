package data.structures;

public class CircularList extends BasicList{
    private Node tail;

    private void addNodeAux(Object value){
        Node newest = new Node(value);
        if (isEmpty()) {
            head = tail= newest;
            head.setNext(head);
        }
        else {
            tail.setNext(newest);
            newest.setNext(head);
            tail = newest;
        }
        size++;
    }

    //[Adios] -> [2] -> [4.5] -> [[I@3b95a09c] -> [data.structures.SimpleList@6ae40994]
    private void addNodeByIndexAux(Object value, int index){
        Node newest = new Node(value);
        if (isEmpty()) {
            head = tail= newest;
            head.setNext(head);
        }
        else {
            Node actual = head;
            for (int i = 0; i < index  - 1; i++) {
                actual = actual.getNext();
            }
            newest.setNext(actual.getNext());
            actual.setNext(newest);
        }
        size++;
    }

    public void addNodeByIndex(Object value, int index){
        addNodeByIndexAux(value, index);
    }

    public void addNode(Object value){
        try {
            addNodeAux(value);
        }catch (Exception e){
            System.out.println("No se ha podido añadir el nodo: " + e.getMessage());
        }

    }

    private void deleteNodeAux(int index){
        Node actual = head;
        switch (index){
            case 0:
                head = head.getNext();
                tail.setNext(head);
                break;
            default:
                for(int i = 0;i < index - 1; i++){
                    actual = actual.getNext();
                }
                actual.setNext(actual.getNext().getNext());
                break;
        }
        size--;
    }

    public void deleteNode(int index){
        try {
            deleteNodeAux(index);
        }
        catch (Exception e){
            System.out.println("No se ha podido eliminar el nodo: " + e.getMessage());
        }

    }

    //Eliminarlo después
    public void printList(){
        Node actual = head;
        for(int i = 0; i < size  + 1; i++)
        {
            System.out.print("[" + actual.getValue() + "] -> ");
            actual = actual.getNext();
        }
    }

}