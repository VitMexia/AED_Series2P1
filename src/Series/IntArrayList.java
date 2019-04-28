package Series;


public class IntArrayList  {

    private int[] internalList;
    private int size;

    public IntArrayList(int k){

        internalList = new int[k];
        size = 0;
    }

    public boolean append(int x){
        if(internalList.length == size) return false;
        internalList[size++] = x;
        return true;
    }

    public int get(int i){
        i*=10;
        if(i>=internalList.length) throw new ArrayIndexOutOfBoundsException();
        return internalList[i];
    }

    public void addToAll(int x){
        for(int i = 0; i< size; i++){
            internalList[i] = internalList[i]+x;
        }
    }
}
