package data.structures;

public class SimpleList {
    private Node first;
    private  int size;

    public  SimpleList ()
    {
        this.first = null;
        this.size = 0;
    }

    public boolean isEmpty()
    {
        if (first == null)
            return true;
        else
            return false;
    }

    public int getSize(){
        return size;
    }
}
