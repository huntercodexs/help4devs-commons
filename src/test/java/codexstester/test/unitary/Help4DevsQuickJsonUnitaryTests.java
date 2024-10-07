package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.dto.QuickJsonDto;
import com.huntercodexs.demo.services.parser.quickjson.Help4DevsQuickJson;
import com.huntercodexs.demo.services.parser.quickjson.Help4DevsQuickJsonBuilder;
import com.huntercodexs.demo.services.parser.quickjson.Help4DevsQuickJsonExtractor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Help4DevsQuickJsonUnitaryTests extends Help4DevsBridgeTests {

    Help4DevsQuickJson qj;
    Help4DevsQuickJsonBuilder qjBuilder;
    Help4DevsQuickJsonExtractor qjExtractor;

    @Before
    public void setup() {
        this.qj = new Help4DevsQuickJson();
        this.qjBuilder = new Help4DevsQuickJsonBuilder();
        this.qjExtractor = new Help4DevsQuickJsonExtractor();
    }

    @Test
    public void addTest() {
        qj.add("name", "John");
        qj.print();
    }

    @Test
    public void addAllTest() {
        qj.addAll("name", "John", "lastname", "Smith");
        qj.print();

        System.out.println("-----------------------------------------------");

        qj.addAll("name", "Mary", "lastname", "Smith", "age");
        qj.print();
    }

    @Test
    public void removeTest() {
        qj.add("name", "John");
        qj.print();

        qj.remove("name");
        qj.print();
    }

    @Test
    public void clearTest() {
        qj.addAll("name", "John", "lastname", "Smith");
        qj.print();

        System.out.println("-----------------------------------------------");

        qj.addAll("name", "Mary", "lastname", "Smith", "age");
        qj.print();

        qj.clear();
        qj.print();
    }

    @Test
    public void updateTest() {
        qj.add("name", "John");
        qj.print();

        System.out.println("-----------------------------------------------");

        qj.update("name", "Mary");
        qj.print();
    }

    @Test
    public void getTest() {
        qj.add("name", "John");
        qj.add("lastname", "Smith");
        qj.add("age", 35);

        Object result = qj.get("name");
        codexsTesterAssertExact("John", result.toString());

        result = qj.get("lastname");
        codexsTesterAssertExact("Smith", result.toString());

        result = qj.get("age");
        codexsTesterAssertInt(35, (Integer) result);
    }

    @Test
    public void create_Sample1_Test() {
        qj.add("name", "John");
        qj.add("lastname", "Smith");
        qj.add("age", 35);

        qj.create(null);

        String result = qj.json();
        codexsTesterAssertExact("{\"name\":\"John\",\"age\":35,\"lastname\":\"Smith\"}", result);
    }

    @Test
    public void create_Sample2_Test() {
        qj.add("name", "John");
        qj.add("lastname", "Smith");
        qj.add("age", 35);

        String result = qj.json();
        codexsTesterAssertExact("{\"name\":\"John\",\"age\":35,\"lastname\":\"Smith\"}", result);
    }

    @Test
    public void create_Sample3_Test() {
        qj.add("name", "John");
        qj.add("lastname", "Smith");
        qj.add("age", 35);
        qj.add("address", Arrays.asList("Street 1", "200", "New York City"));
        qj.add("contacts", Arrays.asList("12345678", "98789789", "12424242"));
        qj.add("reference", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}");

        String result = qj.json();
        codexsTesterAssertExact("{\"reference\":{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"},\"address\":[\"Street 1\", 200, \"New York City\"],\"name\":\"John\",\"age\":35,\"contacts\":[12345678, 98789789, 12424242],\"lastname\":\"Smith\"}", result);
    }

    @Test
    public void create_Sample4_Test() {
        qj.add("name", "John");
        qj.add("lastname", "Smith");
        qj.add("age", 35);
        qj.add("address", Arrays.asList("Street 1", "200", "New York City"));
        qj.add("contacts", Arrays.asList("12345678", "98789789", "12424242"));
        qj.add("reference", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}");
        qj.add("family",
                Arrays.asList(
                        "mother", "July Smith",
                        "father", "Luis Smith",
                        Arrays.asList(
                                "sister", "Elen Smith", "age", 22
                        ),
                        Arrays.asList(
                                "brother", "Igor Smith", "age", 24
                        )
                )
        );

        String result = qj.json();
        codexsTesterAssertExact("{\"reference\":{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"},\"address\":[\"Street 1\", 200, \"New York City\"],\"name\":\"John\",\"family\":[\"mother\", \"July Smith\", \"father\", \"Luis Smith\", [\"brother\", \"Igor Smith\", \"age\", 24], [\"sister\", \"Elen Smith\", \"age\", 22]],\"age\":35,\"contacts\":[12345678, 98789789, 12424242],\"lastname\":\"Smith\"}", result);
    }

    @Test
    public void create_Sample5_Test() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("map1", "Map 1 Value Test");
        map.put("map2", 345);
        map.put("map3", Arrays.asList("Array 1", "Array 2", 222, "Array 3"));

        qj.add("name", "John");
        qj.add("lastname", "Smith");
        qj.add("age", 35);
        qj.add("address", Arrays.asList("Street 1", "200", "New York City"));
        qj.add("contacts", Arrays.asList("12345678", "98789789", "12424242"));
        qj.add("reference", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}");
        qj.add("family",
                Arrays.asList(
                        "mother", "July Smith",
                        "father", "Luis Smith",
                        Arrays.asList(
                                "sister", "Elen Smith", "age", 22
                        ),
                        Arrays.asList(
                                "brother", "Igor Smith", "age", 24
                        )
                )
        );
        qj.add("map", map);

        String result = qj.json();
        codexsTesterAssertExact("{\"reference\":{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"},\"address\":[\"Street 1\", 200, \"New York City\"],\"name\":\"John\",\"family\":[\"mother\", \"July Smith\", \"father\", \"Luis Smith\", [\"brother\", \"Igor Smith\", \"age\", 24], [\"sister\", \"Elen Smith\", \"age\", 22]],\"map\":{\"map3\":[\"Array 1\", \"Array 2\", 222, \"Array 3\"], \"map2\":345, \"map1\":\"Map 1 Value Test\"},\"age\":35,\"contacts\":[12345678, 98789789, 12424242],\"lastname\":\"Smith\"}", result);
    }

    @Test
    public void mergeTest() {
        qj.add("name", "John");
        qj.add("lastname", "Smith");
        qj.add("age", 35);
        qj.add("address", Arrays.asList("Street 1", "200", "New York City"));
        qj.add("contacts", Arrays.asList("12345678", "98789789", "12424242"));
        qj.add("reference", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}");
        qj.add("family",
                Arrays.asList(
                        "mother", "July Smith",
                        "father", "Luis Smith",
                        Arrays.asList(
                                "sister", "Elen Smith", "age", 22
                        ),
                        Arrays.asList(
                                "brother", "Igor Smith", "age", 24
                        )
                )
        );

        String result1 = qj.json();

        qj.clear();

        qj.add("name", "John");
        qj.add("lastname", "Smith");
        qj.add("age", 35);
        qj.add("address", Arrays.asList("Street 1", "200", "New York City"));
        qj.add("contacts", Arrays.asList("12345678", "98789789", "12424242"));
        qj.add("reference", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}");
        qj.add("family",
                Arrays.asList(
                        "mother", "July Smith",
                        "father", "Luis Smith",
                        Arrays.asList(
                                "sister", "Elen Smith", "age", 22
                        ),
                        Arrays.asList(
                                "brother", "Igor Smith", "age", 24
                        )
                )
        );

        String result2 = qj.json();

        qj.clear();

        qj.add("name", "John");
        qj.add("lastname", "Smith");
        qj.add("age", 35);
        qj.add("address", Arrays.asList("Street 1", "200", "New York City"));
        qj.add("contacts", Arrays.asList("12345678", "98789789", "12424242"));
        qj.add("reference", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}");
        qj.add("family",
                Arrays.asList(
                        "mother", "July Smith",
                        "father", "Luis Smith",
                        Arrays.asList(
                                "sister", "Elen Smith", "age", 22
                        ),
                        Arrays.asList(
                                "brother", "Igor Smith", "age", 24
                        )
                )
        );

        String result3 = qj.json();

        List<String> jsonList = new ArrayList<>();
        jsonList.add(result1);
        jsonList.add(result2);
        jsonList.add(result3);

        String merge = Help4DevsQuickJson.merge(jsonList, "employee");

        codexsTesterAssertExact("{\"employee\": [{\"reference\":{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"},\"address\":[\"Street 1\", 200, \"New York City\"],\"name\":\"John\",\"family\":[\"mother\", \"July Smith\", \"father\", \"Luis Smith\", [\"brother\", \"Igor Smith\", \"age\", 24], [\"sister\", \"Elen Smith\", \"age\", 22]],\"age\":35,\"contacts\":[12345678, 98789789, 12424242],\"lastname\":\"Smith\"},{\"reference\":{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"},\"address\":[\"Street 1\", 200, \"New York City\"],\"name\":\"John\",\"family\":[\"mother\", \"July Smith\", \"father\", \"Luis Smith\", [\"brother\", \"Igor Smith\", \"age\", 24], [\"sister\", \"Elen Smith\", \"age\", 22]],\"age\":35,\"contacts\":[12345678, 98789789, 12424242],\"lastname\":\"Smith\"},{\"reference\":{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"},\"address\":[\"Street 1\", 200, \"New York City\"],\"name\":\"John\",\"family\":[\"mother\", \"July Smith\", \"father\", \"Luis Smith\", [\"brother\", \"Igor Smith\", \"age\", 24], [\"sister\", \"Elen Smith\", \"age\", 22]],\"age\":35,\"contacts\":[12345678, 98789789, 12424242],\"lastname\":\"Smith\"}]}", merge);

    }

    @Test
    public void prettifyTest() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("map1", "Map 1 Value Test");
        map.put("map2", 345);
        map.put("map3", Arrays.asList("Array 1", "Array 2", 222, "Array 3"));

        qj.add("name", "John");
        qj.add("lastname", "Smith");
        qj.add("fullname", "John Smith Viz");
        qj.add("age", 35);
        qj.add("address", Arrays.asList("Street 1", "200", "New York City"));
        qj.add("contacts", Arrays.asList("12345678", "98789789", "12424242"));
        qj.add("reference", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\",\"contact\":{\"phone\":\"1234567890\",\"email\":\"john@email.com\"}}");
        qj.add("family",
                Arrays.asList(
                        "mother", "July Smith",
                        "father", "Luis Smith",
                        Arrays.asList(
                                "sister", "Elen Smith", "age", 22
                        ),
                        Arrays.asList(
                                "brother", "Igor Smith", "age", 24
                        )
                )
        );
        qj.add("map", map);

        String result = qj.prettify();
        System.out.println(result);
        codexsTesterAssertText("{\n" +
                "\t\"reference\":{\n" +
                "\t\t\"name\":\"Sarah Wiz\",\n" +
                "\t\t\"parental\":\"friend\",\n" +
                "\t\t\"contact\":{\n" +
                "\t\t\t\"phone\":\"1234567890\",\n" +
                "\t\t\t\"email\":\"john@email.com\"\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"address\":[\n" +
                "\t\t\"Street 1\",\n" +
                "\t\t200,\n" +
                "\t\t\"New York City\"\n" +
                "\t],\n" +
                "\t\"name\":\"John\",\n" +
                "\t\"fullname\":\"John Smith Viz\",\n" +
                "\t\"family\":[\n" +
                "\t\t\"mother\",\n" +
                "\t\t\"July Smith\",\n" +
                "\t\t\"father\",\n" +
                "\t\t\"Luis Smith\",[\n" +
                "\t\t\t\"brother\",\n" +
                "\t\t\t\"Igor Smith\",\n" +
                "\t\t\t\"age\",\n" +
                "\t\t\t24\n" +
                "\t\t],[\n" +
                "\t\t\t\"sister\",\n" +
                "\t\t\t\"Elen Smith\",\n" +
                "\t\t\t\"age\",\n" +
                "\t\t\t22\n" +
                "\t\t]\n" +
                "\t],\n" +
                "\t\"map\":{\n" +
                "\t\t\"map3\":[\n" +
                "\t\t\t\"Array 1\",\n" +
                "\t\t\t\"Array 2\",\n" +
                "\t\t\t222,\n" +
                "\t\t\t\"Array 3\"\n" +
                "\t\t],\n" +
                "\t\t\"map2\":345,\n" +
                "\t\t\"map1\":\"Map 1 Value Test\"\n" +
                "\t},\n" +
                "\t\"age\":35,\n" +
                "\t\"contacts\":[\n" +
                "\t\t12345678,\n" +
                "\t\t98789789,\n" +
                "\t\t12424242\n" +
                "\t],\n" +
                "\t\"lastname\":\"Smith\"\n" +
                "}", result);
    }

    @Test
    public void standardExtractorTest() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("map1", "Map 1 Value Test");
        map.put("map2", 345);
        map.put("map3", Arrays.asList("Array 1", "Array 2", 222, "Array 3"));

        qj.setStdoutOn(false);
        qj.add("name", "John");
        qj.add("lastname", "Smith");
        qj.add("fullname", "John Smith Viz");
        qj.add("age", 35);
        qj.add("address", Arrays.asList("Street 1", "200", "New York City"));
        qj.add("contacts", Arrays.asList("12345678", "98789789", "12424242"));
        qj.add("reference", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}");
        qj.add("family",
                Arrays.asList(
                        "mother", "July Smith",
                        "father", "Luis Smith",
                        Arrays.asList(
                                "sister", "Elen Smith", "age", 22
                        ),
                        Arrays.asList(
                                "brother", "Igor Smith", "age", 24
                        )
                )
        );
        qj.add("map", map);

        String result = qj.json();

        Object extract = qjExtractor.standardExtraction(result, "name");
        codexsTesterAssertExact("John", String.valueOf(extract));

        extract = qjExtractor.standardExtraction(result, "lastname");
        codexsTesterAssertExact("Smith", String.valueOf(extract));

        extract = qjExtractor.standardExtraction(result, "fullname");
        codexsTesterAssertExact("John Smith Viz", String.valueOf(extract));

        extract = qjExtractor.standardExtraction(result, "age");
        codexsTesterAssertInt(35, Integer.parseInt(String.valueOf(extract)));

        extract = qjExtractor.standardExtraction(result, "address");
        codexsTesterAssertExact("[\"Street 1\", 200, \"New York City\"]", String.valueOf(extract));

        extract = qjExtractor.standardExtraction(result, "contacts");
        codexsTesterAssertExact("[12345678, 98789789, 12424242]", String.valueOf(extract));

        extract = qjExtractor.standardExtraction(result, "reference");
        codexsTesterAssertExact("{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}", String.valueOf(extract));

        extract = qjExtractor.standardExtraction(result, "family");
        codexsTesterAssertExact("[\"mother\", \"July Smith\", \"father\", \"Luis Smith\", [\"brother\", \"Igor Smith\", \"age\", 24], [\"sister\", \"Elen Smith\", \"age\", 22]]", String.valueOf(extract));

        extract = qjExtractor.standardExtraction(result, "map");
        codexsTesterAssertExact("{\"map3\":[\"Array 1\", \"Array 2\", 222, \"Array 3\"], \"map2\":345, \"map1\":\"Map 1 Value Test\"}", String.valueOf(extract));

    }

    @Test
    public void smartExtractorTest() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("map1", "Map 1 Value Test");
        map.put("map2", 345);
        map.put("map3", Arrays.asList("Array 1", "Array 2", 222, "Array 3"));

        qj.setStdoutOn(false);
        qj.add("name", "John");
        qj.add("lastname", "Smith");
        qj.add("fullname", "John Smith Viz \\\"Don\\\"");
        qj.add("age", 35);
        qj.add("address", Arrays.asList("Street 1", "200", "New York City"));
        qj.add("contacts", Arrays.asList("12345678", "98789789", "12424242"));
        qj.add("numbers", Arrays.asList(1, 2, 3, 4, 5));
        qj.add("reference", "{\"parental\":\"mother\",\"name\":\"Sarah Wiz\",\"alias\":\"mom\"}");
        qj.add("family",
                Arrays.asList(
                        "mother", "July Smith",
                        "father", "Luis Smith",
                        Arrays.asList(
                                "sister", "Elen Smith", "age", 22
                        ),
                        Arrays.asList(
                                "brother", "Igor Smith", "age", 24
                        )
                )
        );
        qj.add("map", map);

        String result = qj.json();
        System.out.println(result);

        Object extract;

        extract = qjExtractor.smartExtraction(result, "notExist");
        codexsTesterAssertExact("", String.valueOf(extract));

        extract = qjExtractor.smartExtraction(result, "age");
        codexsTesterAssertInt(35, Integer.parseInt(String.valueOf(extract)));

        extract = qjExtractor.smartExtraction(result, "name");
        codexsTesterAssertExact("John", String.valueOf(extract));

        extract = qjExtractor.smartExtraction(result, "lastname");
        codexsTesterAssertExact("Smith", String.valueOf(extract));

        extract = qjExtractor.smartExtraction(result, "fullname");
        codexsTesterAssertExact("John Smith Viz \"Don\"", String.valueOf(extract));

        extract = qjExtractor.smartExtraction(result, "address");
        codexsTesterAssertExact("[\"Street 1\",200,\"New York City\"]", String.valueOf(extract));

        extract = qjExtractor.smartExtraction(result, "contacts");
        codexsTesterAssertExact("[12345678, 98789789, 12424242]", String.valueOf(extract));

        extract = qjExtractor.smartExtraction(result, "numbers");
        codexsTesterAssertExact("[1, 2, 3, 4, 5]", String.valueOf(extract));

        extract = qjExtractor.smartExtraction(result, "reference");
        codexsTesterAssertExact("{\"parental\":\"mother\",\"name\":\"Sarah Wiz\",\"alias\":\"mom\"}", String.valueOf(extract));

        extract = qjExtractor.smartExtraction(result, "family");
        codexsTesterAssertExact("[\"mother\",\"July Smith\",\"father\",\"Luis Smith\",[\"brother\",\"Igor Smith\",\"age\",24],[\"sister\",\"Elen Smith\",\"age\",22]]", String.valueOf(extract));

        extract = qjExtractor.smartExtraction(result, "map");
        codexsTesterAssertExact("{\"map3\":[\"Array 1\",\"Array 2\",222,\"Array 3\"],\"map2\":345,\"map1\":\"Map 1 Value Test\"}", String.valueOf(extract));

    }

    @Test
    public void build_JsonToObject_Test() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("map1", "Map 1 Value Test");
        map.put("map2", 345);
        map.put("map3", Arrays.asList("Array 1", "Array 2", 222, "Array 3"));
        map.put("map4", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}");

        qj.setStrictMode(false);
        qj.add("type", "Person");
        qj.add("name", "John");
        qj.add("lastname", "Smith");
        qj.add("fullname", "John Smith Viz");
        qj.add("age", 35);
        qj.add("address", Arrays.asList("Street 1", "200", "New York City"));
        qj.add("contacts", Arrays.asList("12345678", "98789789", "12424242"));
        qj.add("numbers", Arrays.asList(1, 2, 3, 4, 5, 6));
        qj.add("reference", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}");
        qj.add("family",
                Arrays.asList(
                        "mother", "July Smith",
                        "father", "Luis Smith",
                        Arrays.asList(
                                "sister", "Elen Smith", "age", 22
                        ),
                        Arrays.asList(
                                "brother", "Igor Smith", "age", 24
                        )
                )
        );
        qj.add("map", map);

        String jsonResult = qj.json();

        qjBuilder.setStrictMode(false);
        QuickJsonDto build = (QuickJsonDto) qjBuilder.build(jsonResult, QuickJsonDto.class);

        codexsTesterAssertExact("QuickJsonDto(type=Person, age=35, name=John, lastname=Smith, fullname=John Smith Viz, reference={\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}, address=[\"Street 1\", 200, \"New York City\"], contacts=[12345678,  98789789,  12424242], numbers=[1,  2,  3,  4,  5,  6], family=[\"mother\", \"July Smith\", \"father\", \"Luis Smith\", [\"brother\", \"Igor Smith\", \"age\", 24], [\"sister\", \"Elen Smith\", \"age\", 22]], map={map3=[\"Array 1\",\"Array 2\",222,\"Array 3\"], map2=345, map1=Map 1 Value Test, map4={\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}})", build.toString());

    }

    @Test
    public void build_ObjectToJson_Test() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("map1", "Map 1 Value Test");
        map.put("map2", 345);
        map.put("map3", Arrays.asList("Array 1", "Array 2", 222, "Array 3"));
        map.put("map4", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}");

        qj.setStrictMode(false);
        qj.add("type", "Person");
        qj.add("name", "John");
        qj.add("lastname", "Smith");
        qj.add("fullname", "John Smith Viz");
        qj.add("age", 35);
        qj.add("address", Arrays.asList("Street 1", "200", "New York City"));
        qj.add("contacts", Arrays.asList("12345678", "98789789", "12424242"));
        qj.add("numbers", Arrays.asList(1, 2, 3, 4, 5, 6));
        qj.add("reference", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}");
        qj.add("family",
                Arrays.asList(
                        "mother", "July Smith",
                        "father", "Luis Smith",
                        Arrays.asList(
                                "sister", "Elen Smith", "age", 22
                        ),
                        Arrays.asList(
                                "brother", "Igor Smith", "age", 24
                        )
                )
        );
        qj.add("map", map);

        String jsonResult = qj.json();

        qjBuilder.setStrictMode(false);
        QuickJsonDto quickJsonDto = (QuickJsonDto) qjBuilder.build(jsonResult, QuickJsonDto.class);

        Object jsonFinal = qjBuilder.build(quickJsonDto);

        codexsTesterAssertExact("{\"reference\":{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"},\"address\":[\"Street 1\", 200, \"New York City\"],\"name\":\"John\",\"numbers\":[1, 2, 3, 4, 5, 6],\"fullname\":\"John Smith Viz\",\"type\":\"Person\",\"family\":[\"mother\", \"July Smith\", \"father\", \"Luis Smith\", \"Igor Smith\", \"age\", \"Elen Smith\", \"age\"],\"map\":{\"map3\":[\"Array 1\",\"Array 2\",222,\"Array 3\"],\"map2\":345,\"map1\":\"Map 1 Value Test\",\"map4\":{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}},\"age\":35,\"contacts\":[12345678, 98789789, 12424242],\"lastname\":\"Smith\"}", String.valueOf(jsonFinal));

    }

}
