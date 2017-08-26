package data.structures;

public class NodeSimple {

    private Object value;
    private NodeSimple next;

    public NodeSimple (Object value)
    {
        this.value = value;
        this.next = null;
    }

    public NodeSimple getNext() {
        return next;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setNext(NodeSimple next) {
        this.next = next;
    }


}
