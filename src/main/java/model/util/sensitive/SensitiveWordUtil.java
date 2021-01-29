package model.util.sensitive;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SensitiveWordUtil {
    public static final int MinMatchType = 1;
    public static final int MaxMatchType = 2;
    public static HashMap sensitiveWordMap;

    public SensitiveWordUtil() {
    }

    public static synchronized void init(Set<String> sensitiveWordSet) {
        initSensitiveWordMap(sensitiveWordSet);
    }

    private static void initSensitiveWordMap(Set<String> sensitiveWordSet) {
        sensitiveWordMap = new HashMap(sensitiveWordSet.size());
        Iterator iterator = sensitiveWordSet.iterator();

        while(iterator.hasNext()) {
            String key = (String)iterator.next();
            Map nowMap = sensitiveWordMap;

            for(int i = 0; i < key.length(); ++i) {
                char keyChar = key.charAt(i);
                Object wordMap = ((Map)nowMap).get(keyChar);
                if (wordMap != null) {
                    nowMap = (Map)wordMap;
                } else {
                    Map<String, String> newWorMap = new HashMap();
                    newWorMap.put("isEnd", "0");
                    ((Map)nowMap).put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if (i == key.length() - 1) {
                    ((Map)nowMap).put("isEnd", "1");
                }
            }
        }

    }

    public static boolean contains(String txt, int matchType) {
        boolean flag = false;

        for(int i = 0; i < txt.length(); ++i) {
            int matchFlag = checkSensitiveWord(txt, i, matchType);
            if (matchFlag > 0) {
                flag = true;
            }
        }

        return flag;
    }

    public static boolean contains(String txt) {
        return contains(txt, 2);
    }

    public static Set<String> getSensitiveWord(String txt, int matchType) {
        Set<String> sensitiveWordList = new HashSet();

        for(int i = 0; i < txt.length(); ++i) {
            int length = checkSensitiveWord(txt, i, matchType);
            if (length > 0) {
                sensitiveWordList.add(txt.substring(i, i + length));
                i = i + length - 1;
            }
        }

        return sensitiveWordList;
    }

    public static Set<String> getSensitiveWord(String txt) {
        return getSensitiveWord(txt, 2);
    }

    public static String replaceSensitiveWord(String txt, char replaceChar, int matchType) {
        String resultTxt = txt;
        Set<String> set = getSensitiveWord(txt, matchType);

        String word;
        String replaceString;
        for(Iterator iterator = set.iterator(); iterator.hasNext(); resultTxt = resultTxt.replaceAll(word, replaceString)) {
            word = (String)iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
        }

        return resultTxt;
    }

    public static String replaceSensitiveWord(String txt, char replaceChar) {
        return replaceSensitiveWord(txt, replaceChar, 2);
    }

    public static String replaceSensitiveWord(String txt, String replaceStr, int matchType) {
        String resultTxt = txt;
        Set<String> set = getSensitiveWord(txt, matchType);

        String word;
        for(Iterator iterator = set.iterator(); iterator.hasNext(); resultTxt = resultTxt.replaceAll(word, replaceStr)) {
            word = (String)iterator.next();
        }

        return resultTxt;
    }

    public static String replaceSensitiveWord(String txt, String replaceStr) {
        return replaceSensitiveWord(txt, replaceStr, 2);
    }

    private static String getReplaceChars(char replaceChar, int length) {
        String resultReplace = String.valueOf(replaceChar);

        for(int i = 1; i < length; ++i) {
            resultReplace = resultReplace + replaceChar;
        }

        return resultReplace;
    }

    private static int checkSensitiveWord(String txt, int beginIndex, int matchType) {
        boolean flag = false;
        int matchFlag = 0;
        Map nowMap = sensitiveWordMap;

        for(int i = beginIndex; i < txt.length(); ++i) {
            char word = txt.charAt(i);
            nowMap = (Map)((Map)nowMap).get(word);
            if (nowMap == null) {
                break;
            }

            ++matchFlag;
            if ("1".equals(((Map)nowMap).get("isEnd"))) {
                flag = true;
                if (1 == matchType) {
                    break;
                }
            }
        }

        if (matchFlag < 2 || !flag) {
            matchFlag = 0;
        }

        return matchFlag;
    }

    public static void main(String[] args) {
        Set<String> sensitiveWordSet = new HashSet();
        sensitiveWordSet.add("太多");
        sensitiveWordSet.add("爱恋");
        sensitiveWordSet.add("静静");
        sensitiveWordSet.add("哈哈");
        sensitiveWordSet.add("啦啦");
        sensitiveWordSet.add("感动");
        sensitiveWordSet.add("发呆");
        init(sensitiveWordSet);
        System.out.println("敏感词的数量：" + sensitiveWordMap.size());
        String string = "太多的伤感情怀也许只局限于饲养基地 荧幕中的情节。然后我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个贱人一杯红酒一部电影在夜 深人静的晚上，关上电话静静的发呆着。";
        System.out.println("待检测语句字数：" + string.length());
        boolean result = contains(string);
        System.out.println(result);
        result = contains(string, 1);
        System.out.println(result);
        Set<String> set = getSensitiveWord(string);
        PrintStream var10000 = System.out;
        int var10001 = set.size();
        var10000.println("语句中包含敏感词的个数为：" + var10001 + "。包含：" + set);
        set = getSensitiveWord(string, 1);
        var10000 = System.out;
        var10001 = set.size();
        var10000.println("语句中包含敏感词的个数为：" + var10001 + "。包含：" + set);
        String filterStr = replaceSensitiveWord(string, '*');
        System.out.println(filterStr);
        filterStr = replaceSensitiveWord(string, '*', 1);
        System.out.println(filterStr);
        String filterStr2 = replaceSensitiveWord(string, "[*敏感词*]");
        System.out.println(filterStr2);
        filterStr2 = replaceSensitiveWord(string, "[*敏感词*]", 1);
        System.out.println(filterStr2);
    }
}
