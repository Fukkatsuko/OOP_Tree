package Tree;

public class Node implements Comparable{
    private int ind;
    public Node(int ind){
        this.ind=ind;
    }

    public int nextNumber(){ return this.ind; }
    public int getNumber(){ return ind; }

    @Override
    public int CompareTo(Object o) {
        Node another=(Node) o;
        if(this.ind>another.ind) return  1;
        else{
            if(this.ind<another.ind) return -1;
            else{
                return 0;
            }
        }
    }
}