package design2.huffman;

import design2.draw.DrawHuffmanTree;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title HuffmanAlgorithmAbstract
 * @description 哈夫曼算法的抽象实现类
 * @date 2019/12/23 11:07
 * @modified by:
 */
public class HuffmanAlgorithm implements IHuffmanAlgorithm {
    @Override
    public EncodeResult encode(String string, Pane pane) {
        ArrayList<Node> letterList = strToList(string);
        int countX = letterList.size();
        Node rootNode = createTree(letterList);
        DrawHuffmanTree drawHuffmanTree = new DrawHuffmanTree();
        drawHuffmanTree.createTree(rootNode, pane, 700, 30, 0);
        Map<Character, String> letterCode = getLetterCode(rootNode);
        EncodeResult result = encode(letterCode, string);
        return result;
    }

    /**
     * 把一个字符串转化成节点列表
     *
     * @param letters 字符串
     * @return ArrayList
     */
    private ArrayList<Node> strToList(String letters) {
        ArrayList<Node> letterList = new ArrayList<>();
        Map<Character, Integer> characterIntegerMap = new HashMap<Character, Integer>();

        // 运用Map 把字符出现的次数和字符对应
        for (int i = 0; i < letters.length(); i++) {
            Character character = letters.charAt(i);
            // 如果Map中不包含，就直接放入，如果包含，就先将值取出来加1后再放入
            if (!characterIntegerMap.keySet().contains(character)) {
                /*
                 * keySet().contains() 和containsKey()
                 * 判断是否包含指定的键名
                 * 两个方法都可以用，在网上看到在循环中keySet().contains()更快一点
                 */
                characterIntegerMap.put(character, 1);
            } else {
                Integer oldValue = characterIntegerMap.get(character);
                characterIntegerMap.put(character, oldValue + 1);
            }
        }

        /*
        * keySet()
        * 返回此映射中包含的键的 Set 视图
        * 将map中所有的键存入到Set集合，因为set具备迭代器，所有迭代方式取出所有的键
        * 再根据get()方法  ，获取每一个键对应的值
        */
        Set<Character> keys = characterIntegerMap.keySet();
        // 遍历集合，将字符和对用的次数存入List
        for (Character key : keys) {
            Data data = new Data(key, characterIntegerMap.get(key));
            Node node = new Node(data);
            letterList.add(node);
        }
        return letterList;
    }

    /**
     * 根据字符链表创建树
     *
     * @param letterList 字符次数链表
     * @return Node 根节点
     */
    protected Node createTree(ArrayList<Node> letterList) {
        /*
         * 创建哈夫曼树； 丢失了letterList中的数据，深拷贝letterList是需要完善的地方
         */
        init(letterList);
        while (letterList.size() != 1) {
            int size = letterList.size();
            // 小的节点放在右边（眼睛看到的左边）
            Node nodeLeft = letterList.get(size - 1);
            Node nodeRight = letterList.get(size - 2);
            Node nodeParent = new Node();
            nodeParent.setLeftChild(nodeLeft);
            nodeParent.setRightChild(nodeRight);
            Data data = new Data();
            data.setFrequency(nodeRight.getData().getFrequency() + nodeLeft.getData().getFrequency());
            nodeParent.setData(data);
            letterList.set(size - 2, nodeParent);
            letterList.remove(size - 1);
            sort(letterList);
        }
        // 返回根节点
        return letterList.get(0);
    }

    /**
     * 初始化 让节点列表有序
     *
     * @param letterList 节点列表
     */
    private void init(ArrayList<Node> letterList) {
        sort(letterList);
    }

    /**
     * 冒泡排序，把小的放在最后
     *
     * @param letterList 节点列表
     */
    private void sort(ArrayList<Node> letterList) {
        int size = letterList.size();
        // 处理只有一个元素的情况，也就是说，不需要排序
        if (size == 1) {
            return;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (letterList.get(j).getData().getFrequency() < letterList.get(j + 1).getData().getFrequency()) {
                    Node tempNode = letterList.get(j);
                    letterList.set(j, letterList.get(j + 1));
                    letterList.set(j + 1, tempNode);
                }
            }
        }
    }

    /**
     * 编码字符串。
     *
     * @param letterCode 字符/编码对集合。
     * @param letters    指定的需要编码的字符串。
     * @return 编码结果
     */
    private EncodeResult encode(Map<Character, String> letterCode, String letters) {
        StringBuilder encode = new StringBuilder();
        for (int i = 0, length = letters.length(); i < length; i++) {
            Character character = letters.charAt(i);
            encode.append(letterCode.get(character));
        }
        EncodeResult result = new EncodeResult(encode.toString(), letterCode);
        return result;
    }

    /**
     * 获取所有字符编码对
     *
     * @param rootNode 哈夫曼树的根节点
     * @return Map 所有字符编码对
     */
    private Map<Character, String> getLetterCode(Node rootNode) {
        Map<Character, String> letterCode = new HashMap<Character, String>();
        // 处理只有一个节点的情况
        if (rootNode.getLeftChild() == null && rootNode.getRightChild() == null) {
            letterCode.put(rootNode.getData().getCh(), "1");
            return letterCode;
        }
        getLetterCode(rootNode, "", letterCode);
        return letterCode;
    }

    /**
     * 先序遍历哈夫曼树,获得所有字符编码对。
     *
     * @param rooNode    哈夫曼树根结点
     * @param suffix     编码前缀，也就是编码这个字符时，之前路径上的所有编码
     * @param letterCode 用于保存字符编码结果
     */
    private void getLetterCode(Node rooNode, String suffix, Map<Character, String> letterCode) {
        if (rooNode != null) {
            if (rooNode.getLeftChild() == null && rooNode.getRightChild() == null) {
                Character character = rooNode.getData().getCh();
                letterCode.put(character, suffix);
            }
            getLetterCode(rooNode.getLeftChild(), suffix + "0", letterCode);
            getLetterCode(rooNode.getRightChild(), suffix + "1", letterCode);
        }
    }

    @Override
    public String decode(EncodeResult decodeResult) {
        // 解码得到的字符串
        StringBuffer decodeStr = new StringBuffer();
        // 获得解码器
        Map<String, Character> decodeMap = getDecoder(decodeResult.getLetterCode());
        // 解码器键集合
        Set<String> keys = decodeMap.keySet();
        // 待解码的（被编码的）字符串
        String encode = decodeResult.getEncode();
        // 从最短的开始匹配之所以能够成功，是因为哈夫曼编码的唯一前缀性质
        // 临时的可能的键值
        String temp = "";
        // 改变temp值大小的游标
        int i = 1;
        while (encode.length() > 0 && i < encode.length()) {
            temp = encode.substring(0, i);
            if (keys.contains(temp)) {
                Character character = decodeMap.get(temp);
                decodeStr.append(character);
                encode = encode.substring(i);
                i = 1;
            } else {
                i++;
            }
        }
        return decodeStr.toString();
    }

    /**
     * 获得解码器，也就是通过字符/编码对得到编码/字符对。
     *
     * @param letterCode 字符/编码对
     * @return 编码/字符对
     */
    private Map<String, Character> getDecoder(Map<Character, String> letterCode) {
        Map<String, Character> decodeMap = new HashMap<String, Character>();
        Set<Character> keys = letterCode.keySet();
        for (Character key : keys) {
            String value = letterCode.get(key);
            decodeMap.put(value, key);
        }
        return decodeMap;
    }

}
