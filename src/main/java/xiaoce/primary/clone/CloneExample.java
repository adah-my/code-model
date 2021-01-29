package xiaoce.primary.clone;

import java.io.Serializable;

public class CloneExample implements Cloneable, Serializable
{
    private int[] arr;

    public int[] getArr()
    {
        return arr;
    }

    public void setArr(int[] arr)
    {
        this.arr = arr;
    }

    public CloneExample() {
        arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    public void set(int index, int value) {
        arr[index] = value;
    }

    public int get(int index) {
        return arr[index];
    }

    @Override
    public CloneExample clone() throws CloneNotSupportedException
    {
       return  (CloneExample) super.clone();
    }
}
