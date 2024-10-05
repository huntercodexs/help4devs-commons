package com.huntercodexs.demo.services.parser;

public class Help4DevsQuickJsonExtractor {

    public Help4DevsQuickJsonExtractor() {
    }

    private boolean jsonOk(String json, String field) {
        return json != null && !json.isEmpty() && json.matches("^.*(\"" + field + "\":).*$");
    }

    private boolean fieldOk(String field) {
        return field != null && !field.isEmpty();
    }

    private String jsonFilter(String json) {
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
                .replaceAll("}, \\{", "},{");
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">standardExtractor</h6>
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
    public Object standardExtractor(Object jsonObj, String field) {

        String[] split;
        String json = String.valueOf(jsonObj);

        String subArray1Extract = json
                .replaceFirst(
                        "(.*)(\""+field+ "\": ?)\\[([\"0-9a-zA-Z:}{, _+.-]+)(\"])(.*)",
                        "[$1][$2]{@EXTRACT}[$3$4{@EXTRACT}[$5]");

        split = subArray1Extract.split("\\{@EXTRACT}");

        if (split.length > 1 && !split[1].isEmpty()) {
            return split[1];
        }

        String subArrayExtract = json
                .replaceFirst(
                        "(.*)(\""+field+ "\": ?)\\[([\"0-9a-zA-Z:}{\\]\\[, _+.-]+)(]])(.*)",
                        "[$1][$2]{@EXTRACT}[$3$4{@EXTRACT}[$5]");

        split = subArrayExtract.split("\\{@EXTRACT}");

        if (split.length > 1 && !split[1].isEmpty()) {
            return split[1];
        }

        String jsonExtract = json
                .replaceFirst(
                        "(.*)(\""+field+"\": ?)\\{([\"0-9a-zA-Z:}{\\]\\[, _+.-]+)}(,\"[a-zA-Z][0-9a-zA-Z-_]\":)?(.*)",
                        "[$1][$2]{@EXTRACT}{$3}{@EXTRACT}[$4][$5]");

        split = jsonExtract.split("\\{@EXTRACT}");

        if (split.length > 1 && !split[1].isEmpty()) {
            return split[1].replaceFirst("(},.*)", "}");
        }

        String arrayExtract = json
                .replaceFirst(
                        "(.*)(\""+field+"\": ?)\\[([\"0-9a-zA-Z, _+.-]+)](.*)",
                        "[$1][$2]{@EXTRACT}[$3]{@EXTRACT}[$4]");

        split = arrayExtract.split("\\{@EXTRACT}");

        if (split.length > 1 && !split[1].isEmpty()) {
            return split[1];
        }

        String dataExtract = json.replaceFirst(
                "(,\"" + field + "\"): ?\"?([0-9a-zA-Z .}{\\]\\[)(@#!&*|/$%_+-]+)\"?,? ?",
                "$1{@EXTRACT}$2");

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
     * <h6 style="color: #FFFF00; font-size: 11px">advancedExtractor</h6>
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
    public Object advancedExtractor(String json, String field) {

        if (!jsonOk(json, field) || !fieldOk(field)) return "";

        json = jsonFilter(json);

        int pos = json.indexOf("\""+field+"\":");
        int len = ("\""+field+"\":").length();
        int tot = pos + len;

        boolean isIntOn = false;
        boolean isStringOn = false;
        boolean isJsonOn = false;
        boolean isArrayOn = false;

        boolean arrayTest = false;
        boolean jsonTest = false;

        StringBuilder result = new StringBuilder();

        int jsonOpenCounter = 0;
        int arrayOpenCounter = 0;

        String prevChar = "";

        for (int i = tot; i < json.length(); i++) {
            String ch4r = String.valueOf(json.charAt(i));

            //Array
            if ((ch4r.equals("[") || isArrayOn) && arrayTest) {

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
