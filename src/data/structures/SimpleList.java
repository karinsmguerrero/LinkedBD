package data.structures;

public class SimpleList extends BasicList{
    private  int size;

    private void addNodeAux(Object value){
        Node newest = new Node(value);
        if (isEmpty()) {
            head = tail = newest;
        }
        else {
            tail.setNext(newest);
            tail = newest;
        }
        size++;
    }

    public void addNode(Object value){
        try{
            addNodeAux(value);
        }
        catch (Exception e){
            System.out.println("Error al añadir nodo");
        }
    }

    private void addNodeByIndexAux(Object value, int index){
        Node newest = new Node(value);
        if (isEmpty()) {
            head = tail = newest;
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


    private void deleteNodeAux(int index){
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

    public void deleteNode(int index){
        deleteNodeAux(index);
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
