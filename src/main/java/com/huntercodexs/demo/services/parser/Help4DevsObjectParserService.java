package com.huntercodexs.demo.services.parser;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Help4DevsObjectParserService {

    private static String[] getValuesForObject(String[] rows, Field[] fields) {
        String[] cols = rows[0].replaceFirst("\\|", "").split("\\|");

        //Check data compatibility
        for (String col : cols) {
            boolean found = false;
            for (Field field : fields) {
                if (field.getName().equals(col.trim())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new RuntimeException("[Critical Error] Incompatible field: " + col.trim());
            }
        }
        return cols;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">listMapToObject</h6>
     *
     * <p style="color: #CDCDCD">To convert a Object Data into List object data</p>
     *
     * <p>Example</p>
     *
     * <blockquote><pre>
     * Help4DevsObjectParserService help4DevsObjectParserService = new Help4DevsObjectParserService();
     * List&lt;Employee&gt; objects = help4DevsObjectParserService.listMapToObject(table, Employee.class);
     * </pre></blockquote>
     *
     * @param obj (Object: DataTable from Cucumber is compatible)
     * @param classT (Class: The class name to convert the current object data
     * @return List&lt;T&gt; (One Java List of Object T relate)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public <T> List<T> listMapToObject(Object obj, Class<T> classT) {

        Field[] fields = classT.getDeclaredFields();
        String[] rows = obj.toString().split("\\|\n");
        String[] cols = getValuesForObject(rows, fields);

        try {

            Object instanceClass = Class.forName(classT.getName()).getDeclaredConstructor().newInstance();

            List<T> objectList = new ArrayList<>();

            for (int i = 1; i < rows.length; i++) {

                String[] rowItems = rows[i].replaceFirst("\\|", "").split("\\|");

                //Fields
                for (int j = 0; j < rowItems.length; j++) {
                    Field field1 = classT.getDeclaredField(cols[j].trim());
                    field1.setAccessible(true);
                    field1.set(instanceClass, rowItems[j].trim());
                }

                objectList.add((T) instanceClass);
            }

            return objectList;

        } catch (
                IllegalAccessException |
                NoSuchFieldException |
                ClassNotFoundException |
                InstantiationException |
                InvocationTargetException |
                NoSuchMethodException e
        ) {
            throw new RuntimeException(e);
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">mapInMap</h6>
     *
     * <p style="color: #CDCDCD">To convert a Object Data into Map object data</p>
     *
     * <p>Example</p>
     *
     * <blockquote><pre>
     * Help4DevsObjectParserService help4DevsObjectParserService = new Help4DevsObjectParserService();
     * Map<String, Map<String, String>> mapMap = help4DevsObjectParserService.mapInMap(table);
     * </pre></blockquote>
     *
     * @param obj (Object: DataTable from Cucumber is compatible)
     * @return Map&lt;String, Map&lt;String, String&gt;&gt;
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public Map<String, Map<String, String>> mapInMap(Object obj) {

        Map<String, Map<String, String>> tableMapMap = new HashMap<>();

        String[] rows = obj.toString().split("\\|\n");
        String[] cols = rows[0].split("\\|");

        String key = cols[cols.length-2].trim();
        String val = cols[cols.length-1].trim();

        for (int i = 1; i < rows.length; i++) {
            Map<String, String> tableMap = new HashMap<>();
            String[] columns = rows[i].replaceFirst("\\|", "").split("\\|");
            tableMap.put(key, columns[1].trim());
            tableMap.put(val, columns[2].trim());
            tableMapMap.put(columns[0].trim(), tableMap);
        }

        return tableMapMap;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">mapInList</h6>
     *
     * <p style="color: #CDCDCD">To convert a Object Data into Map List object data</p>
     *
     * <p>Example</p>
     *
     * <blockquote><pre>
     * Help4DevsObjectParserService help4DevsObjectParserService = new Help4DevsObjectParserService();
     * Map<String, List<String>> mapList = help4DevsObjectParserService.mapInList(table);
     * </pre></blockquote>
     *
     * @param obj (Object: DataTable from Cucumber is compatible)
     * @return Map&lt;String, List&lt;String&gt;&gt;
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public Map<String, List<String>> mapInList(Object obj) {

        Map<String, List<String>> tableMapList = new HashMap<>();

        String[] rows = obj.toString().split("\\|\n");

        for (String row : rows) {

            String[] list = row.replaceFirst("\\|", "").trim().split("\\|");
            String key = list[0].trim();

            List<String> listStr = new ArrayList<>();

            for (int j = 1; j < list.length; j++) {
                listStr.add(list[j].trim());
            }

            tableMapList.put(key, listStr);
        }

        return tableMapList;
    }

}
