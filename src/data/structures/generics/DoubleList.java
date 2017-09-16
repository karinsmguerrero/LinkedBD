package data.structures.generics;

public class DoubleList<T>{
    private DoubleNode<T> current, head, tail;
    private int size;

    public DoubleList(){
        this.tail = null;
    }

    public DoubleNode<T> getTail() {
        return tail;
    }

    public void addNodeToTheTail(T value){
        DoubleNode<T> newest = new DoubleNode<>(value);
        if (isEmpty()) {
            head = tail = newest;
        }
        else {
            tail.setNext(newest);
            newest.setPrevious(tail);
            tail = newest;
        }
        size++;
    }

    private boolean isEmpty(){
        return head == null;
    }

    public int getSize(){
        return size;
    }

    public DoubleNode<T> getHead(){
        return head;
    }

    public void addNodeToTheHead(T value){
        DoubleNode<T> newest = new DoubleNode<>(value);
        if (isEmpty()) {
            head = tail = newest;
        }
        else {
            head.setPrevious(newest);
            head = newest;
        }
        size++;
    }

    public void getNode(int index){
        DoubleNode <T> actual = head;
        if(size >= index || size >= index) {

            int i = 0;
            while (i <= index) {
                if (i == index)
                    break;
                actual = actual.getNext();
                i++;
            }
            System.out.println(actual.getValue());
        }
        else {
            System.out.println("No existe el nodo");
        }
    }

    public void deleteNode(int index){
        DoubleNode<T> actual = head;
        DoubleNode<T> temp;
        if(head == tail & index == 0) {
            head = null;
            tail = null;
        }
        else {
            //[2]<->[5]<->[4]<->[2.3]<->["holiwis"]
            switch (index) {
                case 0:
                    head = actual.getNext();
                    head.setPrevious(null);
                    break;
                case 1:
                    temp = head.getNext();
                    head.setNext(temp.getNext());
                    temp.getNext().setPrevious(head);
                    break;
                default:
                    for (int i = 0; i < index - 1; i++){
                        actual = actual.getNext();
                    }
                    temp = actual.getNext();
                    actual.setNext(temp.getNext());
                    actual.getNext().setPrevious(actual);
                    break;
            }
        }
        size--;
    }

    //Eliminarlo después
    public void printListHeadToTail(){
        DoubleNode<T> actual = head;
        String list = "";
        while (actual != null)
        {
            list += "[" + actual.getValue() + "] <-> ";
            if(actual== null)
                break;
            actual = actual.getNext();
        }
        System.out.println(list);

    }

    public void printListTailToHead(){
        DoubleNode<T> actual = tail;
        String list = "";
        while (actual != null)
        {
            list += "[" + actual.getValue() + "] <-> ";
            actual = actual.getPrevious();
        }
        System.out.println(list);

    }

}
