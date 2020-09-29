package design2.huffman;

import javafx.scene.layout.Pane;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title IHuffmanAlgorithm
 * @description 哈夫曼算法接口
 * @date 2019/12/23 11:03
 * @modified by:
 */
public interface IHuffmanAlgorithm {
    /**
     * 编码字符串
     *
     * @param string 指定的需要编码的字符串
     * @return 编码结果
     */
    public EncodeResult encode(String string, Pane pane);

    /**
     * 根据编码结果返回原来的字符串。
     *
     * @param encodeResult 原来字符串的编码结果。
     * @return 解码出来的字符串。
     */
    public String decode(EncodeResult encodeResult);
}
