package data.structures;

public class DoubleList extends BasicList{
    private Node tail, current;

    public DoubleList(){
        this.tail = null;
    }

    public Node getTail() {
        return tail;
    }

    public void addNodeToTheTail(Object value){
        if (isEmpty()) {
            Node newest = new Node(value);
            head = tail = newest;
            current = newest;
        }
        else {
            Node newest = new Node(value, tail, null);
            tail.setNext(newest);
            tail = newest;
            current = newest;
        }
        size++;
    }

    public void addNodeToTheHead(Object value){
        if (isEmpty()) {
            Node newest = new Node(value);
            head = tail = newest;
            current = newest;
        }
        else {
            Node newest = new Node(value, null, head);
            head.setPrevious(newest);
            head = newest;
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
    public void printListHeadToTail(){
        Node actual = head;
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
        Node actual = tail;
        String list = "";
        while (actual != null)
        {
            list += "[" + actual.getValue() + "] <-> ";
            actual = actual.getPrevious();
        }
        System.out.println(list);

    }

}
