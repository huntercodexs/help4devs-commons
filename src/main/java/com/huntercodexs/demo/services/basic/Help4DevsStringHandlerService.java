package com.huntercodexs.demo.services.basic;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class Help4DevsStringHandlerService {

    private int index = 0;

    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">repeat</h6>
     *
     * <p style="color: #CDCDCD">Repeat a string or char one or more times - Java 1.8 or minor</p>
     *
     * @param str (String: Data to repeat)
     * @param len (int: Quantity to repeat a str)
     * @return String (A Data repeated n times according len parameter)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String repeat(String str, int len) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(str);
        }
        return String.valueOf(stringBuilder);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">reverse</h6>
     *
     * <p style="color: #CDCDCD">Reverse a string - Java 1.8 or minor</p>
     *
     * @param str (String: Data to reverse)
     * @return String (Data reversed)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String reverse(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = str.length()-1; i >= 0; i--) {
            stringBuilder.append(str.charAt(i));
        }
        return String.valueOf(stringBuilder);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">queryStringBuilder</h6>
     *
     * <p style="color: #CDCDCD">Create a query string from Data JSON or List structures</p>
     *
     * @param input (Object: Data input to create a query string)
     * @return String (Query string created)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String queryStringBuilder(Object input) {
        String[] splitter = input.toString().split("},");
        StringBuilder queryBuilder = new StringBuilder();

        for (String s : splitter) {
            String tmp = s.replaceAll("[]}{\\[\"']", "")
                    .replaceAll(", ", "&")
                    .replaceAll(",", "&")
                    .replaceAll(":", "=")
                    .replaceAll("= ", "=")+"&";
            queryBuilder.append(tmp);
        }

        return String.valueOf(queryBuilder).replaceAll("&$", "").trim();
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">getDataFromQueryString</h6>
     *
     * <p style="color: #CDCDCD">Extract field from a Query String Data Structure</p>
     *
     * @param queryString (String: Query string to extract field)
     * @param field (String: Field to extract from Query String)
     * @return Object (Field found)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static Object getDataFromQueryString(String queryString, String field) {

        String str = (queryString.split(field+"=")[1]);

        if (str.contains("&")) {
            str = str.split("&")[0];
        }

        return str;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">queryStringToJson</h6>
     *
     * <p style="color: #CDCDCD">Convert a Query String into JSON Object</p>
     *
     * @param input (String: Query string)
     * @return JSONObject (Data Json)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static JSONObject queryStringToJson(String input) {

        System.out.println("INPUT STRING: " + input);

        String[] splitter = input.split("&");
        JSONObject jsonData = new JSONObject();

        for (String split : splitter) {
            String[] splitter2 = split.split("=");
            jsonData.appendField(splitter2[0].trim(), splitter2[1].trim());
        }

        return jsonData;

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">stringToJson</h6>
     *
     * <p style="color: #CDCDCD">Convert a String into JSON Object</p>
     *
     * @param str (String: Query string)
     * @return JSONObject (Data Json)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static JSONObject stringToJson(String str) {

        JSONObject jsonData = new JSONObject();
        String strClean = str.replaceAll("([\"{\\[\\]}'/\\\\]+)", "");

        try {
            String[] splitter = strClean.split(",");

            for (String split : splitter) {
                String[] splitter2 = split.split(":");
                jsonData.appendField(splitter2[0].trim(), splitter2[1].trim());
            }
        } catch (Exception e) {
            try {
                String[] splitter = strClean.split(":");
                jsonData.appendField(splitter[0].trim(), splitter[1].trim());
            } catch (Exception er) {
                jsonData.appendField("message", null);
            }
        }

        return jsonData;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">sanitizeAscii</h6>
     *
     * <p style="color: #CDCDCD">Data Convert and Data Clean remove non ASCII characters</p>
     *
     * @param input (String: String to sanitize)
     * @param letterType (String: Output format type [upper, lower])
     * @return String (Data Json)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String sanitizeAscii(String input, String letterType) {
        if (letterType == null) letterType = "";
        try {
            if (letterType.endsWith("upper")) {
                return Normalizer.normalize(input, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toUpperCase();
            } else if (letterType.endsWith("lower")) {
                return Normalizer.normalize(input, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
            } else {
                return Normalizer.normalize(input, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
            }
        } catch (RuntimeException re) {
            throw new RuntimeException(re.getMessage());
        }
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">queryExtractor</h6>
     *
     * <p style="color: #CDCDCD">Data extract from initial and final position from any string source</p>
     *
     * @param input (String: Data input to cut)
     * @param begin (int: Initial position to cut)
     * @param end (int: Final position to cut)
     * @return String (Data cut)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String queryExtractor(String input, int begin, int end) {
        if (begin > input.length() || end > input.length()) {
            return input;
        }
        return input.substring(begin, end);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">replaceIndexing</h6>
     *
     * <p style="color: #CDCDCD">Replace all string occurrence using an index to identify each one</p>
     *
     * @param input (String: Data input to replace)
     * @param target (String: Data target to replace)
     * @param replacement (String: Data to replacement)
     * @param separator (String: Data to separate the fields - for example [,:-_ ])
     * @param useIndex (boolean: If it should create indexes)
     * @return String (Data replaced)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public String replaceIndexing(
            String input,
            String target,
            String replacement,
            String separator,
            boolean useIndex
    ) {
        int index = this.index;
        String replace;

        if (separator == null) separator = "";

        while (true) {

            if (useIndex) {
                replace = input.replaceFirst(target, replacement+"_"+index+separator);
                index++;
            } else {
                replace = input.replaceFirst(target, replacement+separator);
            }

            if (!replace.contains(target)) {
                break;
            }

            input = replace;

        }

        this.index = index;

        return replace;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">stringExtractor</h6>
     *
     * <p style="color: #CDCDCD">Extract one specific string from one input string</p>
     *
     * <p>Usa example 1</p>
     *
     * <pre>
     *     String source = "Intel(R) Core(TM) i5-9300H CPU @ 2.40GHz 4000 MHz";
     *     String pattern = "(i[0-9]+|AMD|NVIDIA)([-_.0-9a-zA-Z]+)";
     *     String replacer = "model:$1$2";
     *     String clear = "model";
     *     stringExtractor(source, clear, pattern, replacer, 1);
     * </pre>
     *
     * <p>Result example</p>
     *
     * <pre>
     *     i5-9300H
     * </pre>
     *
     * <p>Usa example 2</p>
     *
     * <pre>
     *     String source = "source: /dev/input/event4 description: AT-Translated-Set-2-keyboard";
     *     String pattern = "(description: [-_.0-9a-zA-Z]+)";
     *     String replacer = "description:$1";
     *     String clear = "description";
     *     stringExtractor(source, clear, pattern, replacer, 2);
     * </pre>
     *
     * <p>Result example</p>
     *
     * <pre>
     *     AT-Translated-Set-2-keyboard
     * </pre>
     *
     * @param input (String: Data input to extract, for example: "Intel Core")
     * @param clear (String: Data to clear final string resulted)
     * @param pattern (String: Expression to execute the replacement, for example: (Intel|AMD))
     * @param replacer (String: Expression to replacement, for example: "model: $1")
     * @param index (int: can be >= 0)
     * @return String (Data extracted)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String stringExtractor(String input, String clear, String pattern, String replacer, int index) {

        try {

            String begin = input.replaceAll(pattern, "#<" + index + "#" + replacer + "#" + index + ">#");
            String extract = begin.replaceAll(", ", " ");

            return StringUtils
                    .substringBetween(extract, "#<" + index + "#", "#" + index + ">#")
                    .replaceAll(clear + ":", "").trim();

        } catch (Exception ex) {
            System.out.println("Exception during stringExtractor: " + ex.getMessage());
            return "";
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">stringList</h6>
     *
     * <p style="color: #CDCDCD">Create one list of String from an List items</p>
     *
     * <p>Usage example</p>
     *
     * <pre>
     *     List&lt;String&gt; list = Arrays.asList(
     *          "data to remove: Intel(R) Core(TM)",
     *          "data to remove: Intel(R) Core(TM)",
     *          "data to remove: Intel(R) Core(TM)"
     *     );
     *
     *     String result = stringList(list, "data to remove: ");
     * </pre>
     *
     * <p>Result example</p>
     *
     * <pre>
     *     Intel(R) Core(TM),Intel(R) Core(TM),Intel(R) Core(TM)
     * </pre>
     *
     * @param items (List[String]: Data input to extract)
     * @param clear (String: Expression to remove data from the input data source [items])
     * @return String (Data String Converted)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String stringList(List<String> items, String clear) {

        int i = 0;
        StringBuilder result = new StringBuilder();

        for (String current : items) {

            result.append(current.replaceAll(clear, "").replaceAll(",", ""));

            if (i < items.size()-1) {
                result.append(",");
            }

            i++;
        }

        return String.valueOf(result);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">listExtractor</h6>
     *
     * <p style="color: #CDCDCD">Data extractor from a List source for a list of string</p>
     *
     * <p>Usage example</p>
     *
     * <pre>
     *     List&lt;String&gt; source = Arrays.asList(
     *          "data to remove: Intel(R) Core(TM) i5-9300H CPU @ 2.40GHz 4000 MHz",
     *          "data to remove: Intel(R) Core(TM) i5-9300H CPU @ 2.40GHz 4001 MHz",
     *          "data to remove: Intel(R) Core(TM) i5-9300H CPU @ 2.40GHz 4002 MHz",
     *     );
     *     String pattern = "([0-9]+) (MHz)";
     *     String replacer = "speedCore:$1 $2";
     *     String detail = "speedCore";
     *
     *     String result = listExtractor(source, detail, "data to remove: ", pattern, replacer);
     * </pre>
     *
     * <p>Result example</p>
     *
     * <pre>
     *     4000 MHz,4001 MHz,4002 MHz
     * </pre>
     *
     * @param items (List&lt;String&gt;: Data input to extract)
     * @param detail (String: Any data to apply or control the replacement, for example: model)
     * @param pattern (String: Expression to execute the replacement, for example: (Intel|AMD))
     * @param replacer (String: Expression to replacement, for example: "model: $1")
     * @return String (Data Extracted)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String listExtractor(List<String> items, String detail, String clear, String pattern, String replacer) {

        int i = 0;
        StringBuilder result = new StringBuilder();

        for (String current : items) {

            String item = current
                    .replaceAll(clear, "")
                    .replaceAll(pattern, "#<"+i+"#"+replacer+"#"+i+">#");

            result.append(
                    StringUtils.substringBetween(item, "#<"+i+"#", "#"+i+">#")
                            .replaceAll(detail+":", ""));

            if (i < items.size()-1) {
                result.append(",");
            }

            i++;
        }

        return String.valueOf(result);
    }


    /**
     * <h6 style="color: #FFFF00; font-size: 11px">listClear</h6>
     *
     * <p style="color: #CDCDCD">Create one list of String from an List items cleanup the content</p>
     *
     * <p>Usage example</p>
     *
     * <pre>
     *     List&lt;String&gt; list = Arrays.asList(
     *          "type: keyboard source: type: keyboard source: /dev/input/event9 description: MosArt-Wireless-Keyboard/Mouse",
     *          "type: keyboard source: type: keyboard source: /dev/input/event4 description: AT-Translated-Set-2-keyboard"
     *     );
     *
     *     String result = listClear(list, "type: keyboard source: type: keyboard source: ", "source: );
     * </pre>
     *
     * <p>Result example</p>
     *
     * <pre>
     *     List&lt;String&gt; list = Arrays.asList(
     *          "source: /dev/input/event9 description: MosArt-Wireless-Keyboard/Mouse",
     *          "source: /dev/input/event4 description: AT-Translated-Set-2-keyboard"
     *     );
     * </pre>
     *
     * @param items (List[String]: Data to Clear)
     * @param replace (String: Data to replace)
     * @param replacement (String: Data to replacement)
     * @return LIst&lt;String&gt; (List clear)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static List<String> listClear(List<String> items, String replace, String replacement) {
        List<String> result = new ArrayList<>();
        for (String current : items) {
            result.add(current.replaceAll(replace, replacement));
        }
        return result;
    }
}
