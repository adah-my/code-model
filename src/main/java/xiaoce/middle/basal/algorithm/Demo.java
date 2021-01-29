package xiaoce.middle.basal.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Demo
{
    public static void main(String[] args)
    {
        ArrayList<Couple> husbands = new ArrayList<>();
        husbands.add(new Couple(5, "罗密欧"));
        husbands.add(new Couple(4, "工藤"));
        husbands.add(new Couple(3, "干将"));
        husbands.add(new Couple(2, "牛郎"));
        husbands.add(new Couple(1, "梁山伯"));

        ArrayList<Couple> wifes = new ArrayList<>();
        wifes.add(new Couple(1, "祝英台"));
        wifes.add(new Couple(2, "织女"));
        wifes.add(new Couple(3, "莫邪"));
        wifes.add(new Couple(4, "小兰"));
        wifes.add(new Couple(5, "朱丽叶"));

        int count = 0;
//        for (Couple husband : husbands){
//            for (Couple wife : wifes){
//                // 循环次数
//                count++;
//                if (husband.getFamilyId().equals(wife.getFamilyId())){
//                    System.out.println(husband.getUserName()+"爱"+wife.getUserName());
//                    wifes.remove(wife);
//                    break;
//                }
//            }
//        }
        Map<Integer, Couple> map = new HashMap<>();
        for (Couple wife : wifes){
            count++;
            map.put(wife.getFamilyId(), wife);
        }
        for (Couple husband : husbands){
            count++;
            Couple wife = map.get(husband.getFamilyId());
            System.out.println(husband.getUserName()+"爱"+wife.getUserName());
        }
        System.out.println("共循环"+count+"次");
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Couple{

    private Integer familyId;
    private String userName;
}
