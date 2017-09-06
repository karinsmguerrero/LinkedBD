package data.generics.structures;

abstract class BasicList<T> {
    protected Node<T> head, tail;
    protected int size;

    protected BasicList(){
        this.head = null;
        this.size = 0;
    }

    protected boolean isEmpty(){
        return head == null;
    }

    public int getSize(){
        return size;
    }

    public Node<T> getHead(){
        return head;
    }

    public Node<T> getNode(int index){
        int i = 0;
        Node<T> actual = head;
        while (i < index){
            if(i == index)
                break;
            actual = actual.getNext();
            i++;
        }
        return actual;
    }
}
