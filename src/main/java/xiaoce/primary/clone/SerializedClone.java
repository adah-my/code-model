package xiaoce.primary.clone;

import java.io.*;

public class SerializedClone
{
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T obj){
        T cloneObj = null;
        try
        {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(obj);
            oos.close();

            ByteArrayInputStream ins = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(ins);
            cloneObj = (T) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return cloneObj;
    }
}