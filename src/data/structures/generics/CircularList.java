package data.structures.generics;

public class CircularList<T> extends BasicList{

        private  <T> void addNodeToTailAux(T value){
            Node<T> newest = new Node<>(value);
            if (isEmpty()) {
                head = tail = newest;
                head.setNext(tail);
            }
            else {
                tail.setNext(newest);
                newest.setNext(head);
                tail = newest;
            }
            size++;
        }

        public <T> void addNodeToTail(T value){
            try {
                addNodeToTailAux(value);
            }catch (Exception e){
                System.out.println("No se ha podido añadir el nodo: " + e.getMessage());
            }

        }


    private <T> void deleteNodeAux(int index){
        Node<T> actual = head;
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

    public <T> void deleteNode(int index){
        try {
            deleteNodeAux(index);
        }
        catch (Exception e){
            System.out.println("No se ha podido eliminar el nodo: " + e.getMessage());
        }

    }


    //Eliminarlo después
        public <T> void printList(){
            Node<T> actual = head;
            for(int i = 0; i < size  + 1; i++)
            {
                System.out.print("[" + actual.getValue() + "] -> ");
                actual = actual.getNext();
            }
        }

}
