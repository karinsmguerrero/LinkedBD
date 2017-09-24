package data.structures.generics;

public class CircularDoubleList<T> {
    private DoubleNode<T> head, tail;
    private int size;

    public CircularDoubleList(){
        head = tail = null;
        size = 0;
    }

    private void insertNodeToTailAux(T value){
        DoubleNode<T> newNode = new DoubleNode<>(value);
        if(size == 0){
            head = tail = newNode;
            head.setNext(tail);
            head.setPrevious(tail);
            tail.setNext(head);
            tail.setPrevious(head);
        }
        else{
            newNode.setPrevious(tail);
            newNode.setNext(head);
            tail.setNext(newNode);
            head.setPrevious(newNode);
            tail = newNode;
        }
        size++;
    }

    public void insertNodeToTail(T value){
        insertNodeToTailAux(value);
    }

    private void deleteByIndexAux(int index) {
        if (index >= 0 & index < size) {
            DoubleNode<T> temp = head;
            if (index == 0) {
                temp.getPrevious().setNext(head.getNext());
                temp.getNext().setPrevious(head.getPrevious());
                head = head.getNext();
            }
            else if(index == 1){
                temp = temp.getNext();
                head.setNext(temp.getNext());
                temp.getNext().setPrevious(head);
            }
            else {
                if (index == size - 1) {
                    tail = tail.getPrevious();
                }

                for (int i = 0; i < index - 1; i++) {
                    temp = temp.getNext();
                }
                DoubleNode<T> nodeToDelete = temp.getNext();
                temp.setNext(nodeToDelete.getNext());
                temp.getNext().setPrevious(nodeToDelete.getPrevious());
            }
            size--;

        } else {
            System.out.println("No existe el indice");
        }
    }


    public void deleteByIndex(int index){
        deleteByIndexAux(index);
    }

    public void print(){
        if(size != 0) {
            DoubleNode<T> temp = head;
            System.out.println(temp.getValue());
            while (temp.getNext() != head) {
                temp = temp.getNext();
                System.out.println(temp.getValue());

            }
        }
    }

    public DoubleNode<T> getHead() {
        return head;
    }

    public DoubleNode<T> getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    public DoubleNode<T> getNodeByValue(T value){
        return getNodeByValueAux(value);
    }

    private DoubleNode<T> getNodeByValueAux(T value){
        DoubleNode<T> temp = head;
        for (int i = 0; i < size; i++){
            if(temp.getValue() == value){
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }
}
