package codexstester.cucumber;

import com.huntercodexs.demo.services.parser.Help4DevsObjectParserService;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

public class LambdaSteps implements En {

    Help4DevsObjectParserService help4DevsObjectParserService;

    public LambdaSteps() {

        this.help4DevsObjectParserService = new Help4DevsObjectParserService();

        Given("list test", (DataTable table) -> {
            System.out.println("=========+++> LIST");
            for (String string : table.asList()) {
                System.out.println(string);
            }
        });

        And("list list test", (DataTable table) -> {
            System.out.println("=========+++> LIST LIST");
            for (List<String> list : table.asLists()) {
                System.out.println(list);
            }
        });

        And("list map test", (DataTable table) -> {
            System.out.println("=========+++> LIST MAP");
            for (Map<String, String> map : table.asMaps()) {
                System.out.println(map);
            }
        });

        And("map test", (DataTable table) -> {
            System.out.println("=========+++> MAP");
            table.asMap(String.class, String.class).forEach((k, v)->{
                System.out.println("{"+k+"="+v+"}");
            });
        });

        And("map list test", (DataTable table) -> {
            System.out.println("=========+++> MAP LIST");
            Map<String, List<String>> mapList = help4DevsObjectParserService.mapInList(table);

            mapList.forEach((k, v) -> {
                System.out.println(k+" => "+v);
            });
        });

        And("map map test", (DataTable table) -> {
            System.out.println("=========+++> MAP MAP");
            Map<String, Map<String, String>> mapMap = help4DevsObjectParserService.mapInMap(table);

            mapMap.forEach((k, v) -> {
                System.out.println(k+" => "+v);
            });
        });

        And("with the following template string definition", (String test) -> {
            System.out.println("====> " + test);
        });
    }
}
