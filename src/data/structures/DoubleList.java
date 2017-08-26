package data.structures;

public class DoubleList extends BasicList{
    private Node tail, current;

    public DoubleList(){
        this.tail = null;
    }

    public Node getTail() {
        return tail;
    }

    private void addNodeToTheTailAux(Object value){
        if (isEmpty()) {
            Node newest = new Node(value);
            head = tail = newest;
        }
        else {
            Node newest = new Node(value, tail, null);
            tail.setNext(newest);
            tail = newest;
        }
        size++;
    }

    public void addNodeToTheTail(Object value){
        addNodeToTheTailAux(value);
    }

    private void addNodeToTheHeadAux(Object value){
        if (isEmpty()) {
            Node newest = new Node(value);
            head = tail = newest;
        }
        else {
            Node newest = new Node(value, null, head);
            head.setPrevious(newest);
            head = newest;
        }
        size++;
    }

    public void addNodeToTheHead(Object value){
        addNodeToTheHeadAux(value);
    }

    private void addNodeByIndexAux(Object value, int index){
        Node newest = new Node(value);
        if (isEmpty()) {
            head = tail = newest;
        }
        else if(index == 0 && tail == head){
            head.setNext(null);
            tail.setNext(null);
            head = tail = null;
        }
        else {
            Node actual = head;
            for (int i = 0; i < index  - 1; i++) {
                actual = actual.getNext();
            }
            newest.setNext(actual.getNext());
            actual.getNext().setPrevious(newest);
            newest.setPrevious(actual);
            actual.setNext(newest);
        }
        size++;
    }

    public void addNodeByIndex(Object value, int index){
        addNodeByIndexAux(value, index);
    }

    private void getNodeValueDynamicAux(int index){
        Node actual = head;
        if(getSize()/2 > index || getSize()/2 == index) {

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
            actual = tail;
            for(int i = 0; i < (size - 1) - index; i++){

                actual = actual.getPrevious();

            }
            System.out.println(actual.getValue());
        }
    }

    public void getNodeValueDynamic(int index){
        getNodeValueDynamicAux(index);
    }

    private void deleteNodeAux(int index){
        Node actual = head;
        Node temp;
        if(head == tail) {
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

    public void deleteNode(int index){
        deleteNodeAux(index);
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
