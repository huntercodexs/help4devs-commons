package com.huntercodexs.demo.services.parser.quickjson;

import static com.huntercodexs.demo.services.parser.quickjson.Help4DevsQuickJsonAbstract.*;

public class Help4DevsQuickJsonExtractor {

    public Help4DevsQuickJsonExtractor() {
    }

    private boolean jsonOk(String json, String field) {
        return json != null && !json.isEmpty() && json.matches("^.*(\"" + field + "\":).*$");
    }

    private boolean fieldOk(String field) {
        return field != null && !field.isEmpty();
    }

    private String jsonFilter(String json, String field) {
        return json
                .replaceAll("\n", "")
                .replaceAll("\r", "")
                .replaceAll("\t", "")

                .replaceAll("\", \"", "\",\"")
                .replaceAll("\", \\[", "\",[")
                .replaceAll("\", \\{", "\",{")

                .replaceAll("\": \"", "\":\"")
                .replaceAll("\": \\[", "\":[")
                .replaceAll("\": \\{", "\":{")

                .replaceAll("], \"", "],\"")
                .replaceAll("], \\[", "],[")
                .replaceAll("], \\{", "],{")

                .replaceAll("}, \"", "},\"")
                .replaceAll("}, \\[", "},[")
                .replaceAll("}, \\{", "},{")

                .replaceAll(JSON_FIELD_REGEXP[0].replaceFirst(TARGET, field), JSON_FIELD_REGEXP[1]);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">standardExtraction</h6>
     *
     * <p style="color: #CDCDCD">Retrieve the value from on specific json</p>
     *
     * <p>Example</p>
     *
     * <blockquote><pre>
     * public void test() {
     *     Help4DevsQuickJsonService quickJson = new Help4DevsQuickJsonService();
     *     quickJson.add("name", "John");
     *     quickJson.add("lastname", "Smith");
     *     quickJson.add("fullname", "John Smith Viz");
     *     quickJson.add("age", 35);
     *     quickJson.add("address", Arrays.asList("Street 1", "200", "New York City"));
     *     quickJson.add("contacts", Arrays.asList("12345678", "98789789", "12424242"));
     *     quickJson.add("reference", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}");
     *     quickJson.add("family",
     *             Arrays.asList(
     *                     "mother", "July Smith",
     *                     "father", "Luis Smith",
     *                     Arrays.asList(
     *                             "sister", "Elen Smith", "age", 22
     *                     ),
     *                     Arrays.asList(
     *                             "brother", "Igor Smith", "age", 24
     *                     )
     *             )
     *     );
     *
     *     String result = quickJson.json();
     *     Object extract = Help4DevsQuickJsonService.extract(result, "name");
     *     extract = Help4DevsQuickJsonService.extract(result, "lastname");
     *     extract = Help4DevsQuickJsonService.extract(result, "fullname");
     *     extract = Help4DevsQuickJsonService.extract(result, "age");
     *     extract = Help4DevsQuickJsonService.extract(result, "address");
     *     extract = Help4DevsQuickJsonService.extract(result, "contacts");
     *     extract = Help4DevsQuickJsonService.extract(result, "reference");
     *     extract = Help4DevsQuickJsonService.extract(result, "family");
     *     extract = Help4DevsQuickJsonService.extract(result, "map");
     *
     * }
     * </pre></blockquote>
     *
     * @param jsonObj (String)
     * @param field (String)
     * @return Object (Extracted Value from JSON Data)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public Object standardExtraction(Object jsonObj, String field) {

        String[] split;
        String json = String.valueOf(jsonObj);

        String subArray1Extract = json
                .replaceFirst(SUB_ARRAY1_REGEXP[0].replaceFirst(TARGET, field), SUB_ARRAY1_REGEXP[1]);

        split = subArray1Extract.split("\\{@EXTRACT}");

        if (split.length > 1 && !split[1].isEmpty()) {
            return split[1];
        }

        String subArrayExtract = json
                .replaceFirst(SUB_ARRAY2_REGEXP[0].replaceFirst(TARGET, field), SUB_ARRAY2_REGEXP[1]);

        split = subArrayExtract.split("\\{@EXTRACT}");

        if (split.length > 1 && !split[1].isEmpty()) {
            return split[1];
        }

        String jsonExtract = json
                .replaceFirst(JSON_REGEXP[0].replaceFirst(TARGET, field), JSON_REGEXP[1]);

        split = jsonExtract.split("\\{@EXTRACT}");

        if (split.length > 1 && !split[1].isEmpty()) {
            return split[1].replaceFirst("(},.*)", "}");
        }

        String arrayExtract = json
                .replaceFirst(ARRAY_REGEXP[0].replaceFirst(TARGET, field), ARRAY_REGEXP[1]);

        split = arrayExtract.split("\\{@EXTRACT}");

        if (split.length > 1 && !split[1].isEmpty()) {
            return split[1];
        }

        String dataExtract = json.replaceFirst(STR_REGEXP[0].replaceFirst(TARGET, field), STR_REGEXP[1]);

        split = dataExtract.split("\\{@EXTRACT}");

        if (split.length > 1) {
            return split[1]
                    .replaceFirst("}$", "")
                    .replaceFirst("^\\{", "")
                    .replaceFirst("(\".*)", "")
                    .trim();
        }

        return null;

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">smartExtraction</h6>
     *
     * <p style="color: #CDCDCD">Retrieve the value from on specific json - Strict</p>
     *
     * <p>This method </p>
     *
     * <p>Example</p>
     *
     * <blockquote><pre>
     * public void test() {
     *     Help4DevsQuickJsonService quickJson = new Help4DevsQuickJsonService();
     * }
     * </pre></blockquote>
     *
     * @param json (String)
     * @param field (String)
     * @return Object (Extracted Value from JSON Data)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public Object smartExtraction(String json, String field) {

        if (!jsonOk(json, field) || !fieldOk(field)) return "";

        json = jsonFilter(json, field);

        int pos = json.indexOf("\""+field+"\":");
        int len = ("\""+field+"\":").length();

        System.out.println("===[pos-1]> " + pos);

        // No primary field
        if (pos > 1 && String.valueOf(json.charAt(pos-1)).equals("{")) {
            pos = json.indexOf(",\""+field+"\":");
            len = (",\""+field+"\":").length();
            System.out.println("===[pos-2]> " + pos);
        }

        int tot = pos + len;

        boolean isIntOn = false;
        boolean isStringOn = false;
        boolean isJsonOn = false;
        boolean isArrayOn = false;

        boolean arrayTest = true;
        boolean jsonTest = true;

        StringBuilder result = new StringBuilder();

        int jsonOpenCounter = 0;
        int arrayOpenCounter = 0;

        String prevChar = "";

        for (int i = tot; i < json.length(); i++) {
            String ch4r = String.valueOf(json.charAt(i));

            //Array
            if ((ch4r.equals("[") || isArrayOn) && arrayTest) {

                System.out.println("=======++> ARRAY");

                if (ch4r.equals("[") && !prevChar.equals("\\")) {
                    arrayOpenCounter += 1;
                } else if (ch4r.equals("]") && !prevChar.equals("\\")) {
                    arrayOpenCounter -= 1;
                }

                //isArrayOn = false
                if (arrayOpenCounter == 0 && isArrayOn) {
                    result.append(json.charAt(i));
                    break;
                }

                isArrayOn = true;
                result.append(json.charAt(i));
                prevChar = ch4r;
                continue;
            }
            //JSON
            if ((ch4r.equals("{") || isJsonOn) && jsonTest) {

                System.out.println("=======++> JSON");

                if (ch4r.equals("{") && !prevChar.equals("\\")) {
                    jsonOpenCounter += 1;
                } else if (ch4r.equals("}") && !prevChar.equals("\\")) {
                    jsonOpenCounter -= 1;
                }

                //isJsonOn = false
                if (jsonOpenCounter == 0 && isJsonOn) {
                    result.append(json.charAt(i));
                    break;
                }

                isJsonOn = true;
                result.append(json.charAt(i));
                prevChar = ch4r;
                continue;
            }
            //String
            if (ch4r.equals("\"") || isStringOn) {

                System.out.println("=======++> STRING");

                //isStringOn = false
                if (ch4r.equals("\"") && isStringOn && !prevChar.equals("\\")) {
                    break;
                }

                if (!ch4r.equals("\"") && !ch4r.equals("\\") || prevChar.equals("\\")) {
                    result.append(json.charAt(i));
                }

                isStringOn = true;
                prevChar = ch4r;
                continue;
            }
            //Integer
            if (ch4r.matches("^[0-9]$") || isIntOn) {

                System.out.println("=======++> INTEGER");

                //isIntOn = false
                if (!ch4r.matches("^[0-9]$")) {
                    break;
                }

                isIntOn = true;
                result.append(json.charAt(i));
                continue;
            }
        }

        return String.valueOf(result);
    }

}
