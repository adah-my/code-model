package xiaoce.primary.listener.example;

/**
 * 被监听对象
 */
public class Thief
{
    private ThiefListener listener;

    public void registerLister(ThiefListener listener){
        this.listener = listener;
    }

    public void steal(){
        // 偷之前，告诉警察
        if (listener != null ){
            Event event = new Event();
            // 喂，有胆开枪啊
            listener.shot(event);
        }
        // 偷东西
        System.out.println("to steal money...");
    }

}
