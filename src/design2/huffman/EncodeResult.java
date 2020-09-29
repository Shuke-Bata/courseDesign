package design2.huffman;

import java.util.Map;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title EncodeResult
 * @description 对字符串编码后的结果：包括编码后的字符串和字符/编码对
 * @date 2019/12/23 11:00
 * @modified by:
 */
public class EncodeResult {
    /**
     * 字符串编码后的结果
     */
    private String encode;
    /**
     * 字符串编码对
     */
    private Map<Character,String> letterCode;

    public EncodeResult(String encode, Map<Character, String> letterCode) {
        this.encode = encode;
        this.letterCode = letterCode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public String getEncode() {
        return encode;
    }

    public Map<Character, String> getLetterCode() {
        return letterCode;
    }
}
