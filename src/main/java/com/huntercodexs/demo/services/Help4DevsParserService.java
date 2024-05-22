package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Slf4j
@Service
public class Help4DevsParserService {

    private static JSONObject parseNetJsonObject(Object jsonCandidate) throws Exception {
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(jsonCandidate.toString());
    }

    private static String JsonRefactorUrl(Object jsonString) throws Exception {
        if (!isItJsonCompatible(jsonString)) return jsonString.toString();

        return jsonString.toString()
                .replaceAll("http(s)?:([\\\\/\\\\/]+)", "http$1://")
                .replaceAll("\\\\+/", "/");
    }

    private static String JsonRefactorEscapeChars(Object jsonString) throws Exception {
        if (!isItJsonCompatible(jsonString)) return jsonString.toString();

        return jsonString.toString()
                .replaceAll("\\\\+\"", "\"")
                .replaceAll("(\\\\u)([0-9]+)(s)", "'$3")
                .replaceAll("’s", "'s");
    }

    private static String JsonRefactorSpaces(Object jsonString) throws Exception {
        if (!isItJsonCompatible(jsonString)) return jsonString.toString();

        return jsonString.toString()
                .replaceAll(", \\{", ",{")
                .replaceAll(", \\[", ",[")
                .replaceAll("\", \"", "\",\"")
                .replaceAll("\" ?, ?\"", "\",\"");
    }

    private static String JsonRefactorArrayFix(Object jsonString) throws Exception {
        if (!isItJsonCompatible(jsonString)) return jsonString.toString();
        return jsonString.toString()
                .replaceAll("\"\\[", "[")
                .replaceAll("]\"", "]")
                .replaceAll("\"\\{", "{")
                .replaceAll("}\"", "}")
                .replaceAll("\\[\"", "[")
                .replaceAll("\"]", "]")
                .replaceAll("\\{\"", "{")
                .replaceAll("\"}", "}")
                .replaceAll("\", \"", "\",\"");
    }

    private static String JsonRefactorComplexArray(Object jsonString) throws Exception {
        if (!isItJsonCompatible(jsonString)) return jsonString.toString();

        String json = JsonRefactorArrayFix(jsonString);

        json = json
                .replaceAll("(\\{)([0-9a-zA-Z]+)(\":)", "$1\"$2$3")
                .replaceAll("(:\")([0-9Aa-zA-Z-!@#$%¨&_+=)(/\\\\:.? ]+)(})", "$1$2\"$3")
                .replaceAll("([{,])\"([0-9a-zA-Z-_ ]+)\"\\:\\[([0-9a-zA-Z-_’'!@#$%&*)\"(+=/\\\\:;,.| ]+)( ?, ?)?", "$1\"$2\":[\"$3\"$4")
                .replaceAll("( ?, ?\\[ ?)([0-9a-zA-Z-_’!@#$%&*)\\(+=/\\\\:;,.| ]+)(\\])", ",[\"$2\"]")
                .replaceAll("(\\[)?(\"\")(\\])?", "$1\"$3")
                .replaceAll("(\\[\\[)([0-9a-zA-Z-_!@#$%&*=+ ,]+)(\\]\\])", "$1\"$2\"$3");

        return json;
    }

    private static String JsonRefactorObjects(Object jsonString) throws Exception {
        if (!isItJsonCompatible(jsonString)) return jsonString.toString();

        return jsonString.toString()
                .replaceAll("([ ,{])([0-9a-zA-Z_]+)=([0-9a-zA-Z- \\\\/?%=.@#$!&*)(:]+)(,)?", "$1\"$2\":\"$3\"$4")
                .replaceAll("\"([0-9a-zA-Z-_]+)\"(:)\"([0-9a-zA-Z-_!@#$%&*)(=+, ]+)\"(,)( )?([0-9a-zA-Z-_!@#$%&*)(=+, ]+)(, ?)\"", "\"$1\"$2\"$3$4$5$6\"$7\"")
                .replaceAll("(object)=([0-9a-zA-Z.@#$!&*)(:{]+)(,)?", "\"$1\":\"$2\"$3")
                .replaceAll("(\")(\\{)|(\\})(\")", "$2$3")
                .replaceAll("\"null\", ", null+",")
                .replaceAll("\" ?, ?\"", "\",\"")
                .replaceAll("([ ,{])([0-9a-zA-Z_]+)=", "$1\"$2\":")
                .replaceAll("\\[([0-9a-zA-Z-_ !@#$%&*+=\\?.,':\\|\\\\\\\\/)(]+)]", "[\"$1\"]")
                .replaceAll("\"([0-9a-zA-Z-_ !@#$%&*)(=+:/\\\\.,]+)( )?(\")\\+( )?([0-9a-zA-Z-_ !@#$%&*)(=+:/\\\\.,]+)(,)( )?\"", "\"[$1$2+$4$5\"$6$7\"")
                .replaceAll("(,)?( )?([0-9a-zA-Z-_]+)( )\"([0-9a-zA-Z-_ ]+\")(:)", "$1$2\"$3$4$5$6");
    }

    private static String JsonRefactorArrayFromString(Object jsonString) throws Exception {
        if (!isItJsonCompatible(jsonString)) return jsonString.toString();

        return jsonString.toString()
                .replaceAll("( )?([0-9a-zA-Z- !@#$%&*)'(+=;:./\\\\|]+)\"\\](,)?", "\"$2\"]$3")
                .replaceAll("([0-9a-zA-Z- !@#$%&*)'(+=;:./\\\\|]+),", "\"$1\",")
                .replaceAll("(\\[)(\")+(?!,)", "[\"")
                .replaceAll("(?!,)(\")+(\\])", "\"]")
                .replaceAll("\"\"([0-9a-zA-Z-_ !@#$%&*)(=+,.'/\\\\]+)\"", "\"$1")
                .replaceAll("\"?(true|false|null)\"?", "$1")
                .replaceAll("(\")+", "\"");
    }

    private static String JsonRefactorDatetime(Object jsonString) throws Exception {
        if (!isItJsonCompatible(jsonString)) return jsonString.toString();
        return jsonString.toString()
                .replaceAll(
                        "(\\\")( ?: ?)([0-9]+[-/.][0-9]+[-/.][0-9]+)( [0-9]+:[0-9]+:[0-9]+)?(,)?",
                        "$1$2\"$3$4\"$5");
    }

    private static String JsonRefactorSanitize(Object jsonString) throws Exception {
        if (!isItJsonCompatible(jsonString)) return jsonString.toString();

        return jsonString.toString()
                .replaceAll("\\[([0-9a-zA-Z ]+\")", "[\"$1")
                .replaceAll("(\"[0-9a-zA-Z ]+)\\]", "$1\"]")
                .replaceAll("\"?(true|false|null)\"?", "$1")
                .replaceAll("(\")([0-9a-zA-Z-_ .]+)(\")(:)(\")?([0-9]+)(\")?", "$1$2$3$4$6")
                /*TODO: Check this line*/
                .replaceAll("(\")+", "\"")
                /*Clear all spaces in final stage*/
                .replaceAll(", \\{", ",{")
                .replaceAll("}, \"", "},\"")
                .replaceAll(", \\[", ",[")
                .replaceAll("\\], \"", "],\"")
                .replaceAll("\" ?, ?\"", "\",\"")
                .replaceAll(", ?\"", ",\"")
                .replaceAll("\"\\[", "\"")
                .replaceAll("\\]\"", "\"")
                .replaceAll("\"\\{", "\"")
                .replaceAll("\\}\"", "\"")
                /* " XYZ123", => "XYZ123", */
                .replaceAll("\" ([0-9a-zA-Z- _!@#$%&*)(=+.,\\?]+)\"(,)?", "\"$1\"$2");
    }

    private static String JsonRefactorArrayNumber(Object jsonString) throws Exception {
        if (!isItJsonCompatible(jsonString)) return jsonString.toString();
        return jsonString.toString().replaceAll("(\")([0-9]+)(\")", "$2");
    }

    private static boolean isItJsonCompatible(Object jsonString) throws Exception {

        String[] compatibleTypes = new String[]{
                "java.util.ArrayList",
                "org.json.JSONArray",
                "org.json.JSONObject",
                "net.minidev.json.JSONObject",
                "net.minidev.json.JSONArray",
                "java.util.List",
                "java.lang.String",
                "java.lang.Object",
                "java.util.HashMap",
                "java.util.Map",
                "java.util.LinkedHashMap",
                "java.util.LinkedHash",
                "java.util.LinkedMap",
                "java.util.LinkedList"
        };

        String foundType = jsonString.getClass().getName();

        if (foundType.equals("java.lang.String") || foundType.equals("java.lang.Object")) {

            if ((
                    jsonString.toString().contains("{") &&
                            jsonString.toString().contains("}") &&
                            jsonString.toString().contains(":")) || (
                    jsonString.toString().contains("[") &&
                            jsonString.toString().contains("]") &&
                            jsonString.toString().contains(",")
            )) {
                return true;
            }
        }

        for (String compatibleType : compatibleTypes) {
            if (compatibleType.equals(foundType)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNetJson(Object jsonString) {

        if (jsonString == null) return false;

        String[] compatibleTypes = new String[]{
                "net.minidev.json.JSONObject",
                "net.minidev.json.JSONArray"
        };

        String foundType = jsonString.getClass().getName();

        for (String compatibleType : compatibleTypes) {
            if (compatibleType.equals(foundType)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOrgJson(Object jsonString) {

        if (jsonString == null) return false;

        String[] compatibleTypes = new String[]{
                "org.json.JSONArray",
                "org.json.JSONObject"
        };

        String foundType = jsonString.getClass().getName();

        for (String compatibleType : compatibleTypes) {
            if (compatibleType.equals(foundType)) {
                return true;
            }
        }
        return false;
    }

    public static String jsonRefactor(String refactorMode, Object jsonString) throws Exception {
        if (!isItJsonCompatible(jsonString)) return jsonString.toString();

        String jsonRefactor = jsonString.toString();

        switch (refactorMode) {
            case "easy":
                jsonRefactor = JsonRefactorUrl(jsonRefactor);
                break;
            case "middle":
                jsonRefactor = JsonRefactorUrl(jsonRefactor);
                jsonRefactor = JsonRefactorEscapeChars(jsonRefactor);
                jsonRefactor = JsonRefactorSpaces(jsonRefactor);
                jsonRefactor = JsonRefactorSanitize(jsonRefactor);
                break;
            case "regular":
                jsonRefactor = JsonRefactorUrl(jsonRefactor);
                jsonRefactor = JsonRefactorEscapeChars(jsonRefactor);
                jsonRefactor = JsonRefactorComplexArray(jsonRefactor);
                jsonRefactor = JsonRefactorObjects(jsonRefactor);
                jsonRefactor = JsonRefactorSanitize(jsonRefactor);
                break;
            case "complex":
                jsonRefactor = JsonRefactorUrl(jsonRefactor);
                jsonRefactor = JsonRefactorEscapeChars(jsonRefactor);
                jsonRefactor = JsonRefactorSpaces(jsonRefactor);
                jsonRefactor = JsonRefactorComplexArray(jsonRefactor);
                jsonRefactor = JsonRefactorObjects(jsonRefactor);
                jsonRefactor = JsonRefactorArrayFromString(jsonRefactor);
                jsonRefactor = JsonRefactorSanitize(jsonRefactor);
                jsonRefactor = JsonRefactorArrayNumber(jsonRefactor);
                break;
            default:
                String error = "\n[EXCEPTION] Invalid refactorMode, use: [easy, middle, regular, complex]\n";
                error += "See the README.md documentation in github project to more details\n";
                throw new RuntimeException(error);
        }

        return jsonRefactor;
    }

    public static JSONObject objectToNetJson(Object object) throws Exception {

        String json = object.toString()
                .replaceAll("(^[0-9a-zA-Z]+)(\\()|(=[0-9a-zA-Z]+)(\\))$", "$3")
                .replaceAll("([^0-9a-zA-Z-_!@#$%&*(=+;.,\\/\\]\\[\\\\\\}\\{])$", "")
                .replaceAll("(\\\\u)([0-9]+)(s)", "'$3")
                .replaceAll("\\\\/", "/")
                .replaceAll("(, ?)([a-zA-Z_][0-9a-zA-Z_ ]+)(=)", "$1\"$2\":")
                .replaceAll("^([a-zA-Z_][0-9a-zA-Z_ ]+)(=)", "\"$1\":")
                .replaceAll("\\[([0-9a-zA-Z-_, ]+)\\]", "[\"$1\"]")
                .replaceAll("(\\?)([0-9a-zA-Z_ ]+)(=)", "$1$2°_EQ_°")
                .replaceAll("([a-zA-Z_][0-9a-zA-Z_ ]+)(=)", "\"$1\":")
                .replaceAll("(\\\" ?: ?)(?!null)([a-zA-Z_][0-9a-zA-Z_!@#$%&*)(=+. ]+)", "$1\"$2\"")
                .replaceAll("°_EQ_°", "=");

        json = "{"+json+"}";
        json = JsonRefactorDatetime(json);

        return parseNetJsonObject(json);
    }

    public static JSONObject JsonFromLinkedHashMap(
            LinkedHashMap<?, ?> linkedHashMap,
            Object[] expectedFields
    ) {

        JSONObject jsonResponse = new JSONObject();

        for (Object field : expectedFields) {
            if (linkedHashMap.containsKey(field.toString())) {
                jsonResponse.put(field.toString(), linkedHashMap.get(field.toString()));
            }
        }

        return jsonResponse;
    }

}
