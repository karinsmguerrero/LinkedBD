package data.structures;

abstract class BasicList {
    protected Node head;
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

    public Node getHead(){
        return head;
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
}
