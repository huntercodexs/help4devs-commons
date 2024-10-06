package com.huntercodexs.demo.services.parser.quickjson;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Help4DevsQuickJsonBuilder {

    private boolean strictMode;

    Help4DevsQuickJsonExtractor qjExtract;

    public Help4DevsQuickJsonBuilder() {
        this.qjExtract = new Help4DevsQuickJsonExtractor();
        this.strictMode = false;
    }

    public void setStrictMode(boolean mode) {
        this.strictMode = mode;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">build</h6>
     *
     * <p style="color: #CDCDCD">Convert data from any Object to JSON String format</p>
     *
     * <p>IMPORTANT: This support only five type of data:</p>
     * <ul>
     *     <li>Object</li>
     *     <li>Integer</li>
     *     <li>String</li>
     *     <li>List</li>
     *     <li>HashMap</li>
     * </ul>
     *
     * <p>Example</p>
     *
     * <blockquote><pre>
     * public void test() {
     *     Help4DevsQuickJson qj = new Help4DevsQuickJson();
     *     HashMap<String, Object> map = new HashMap<>();
     *     map.put("map1", "Map 1 Value Test");
     *     map.put("map2", 345);
     *     map.put("map3", Arrays.asList("Array 1", "Array 2", 222, "Array 3"));
     *
     *     qj.setStrictMode(false);
     *     qj.add("name", "John");
     *     qj.add("lastname", "Smith");
     *     qj.add("fullname", "John Smith Viz");
     *     qj.add("age", 35);
     *     qj.add("address", Arrays.asList("Street 1", "200", "New York City"));
     *     qj.add("contacts", Arrays.asList("12345678", "98789789", "12424242"));
     *     qj.add("reference", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}");
     *     qj.add("family",
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
     *     qj.add("map", map);
     *
     *     String result = qj.json();
     *
     *     QuickJsonDto mapper = (QuickJsonDto) qj.mapper(QuickJsonDto.class, result);
     *
     *     System.out.println(mapper);
     * }
     * </pre></blockquote>
     *
     * @return String (JSON String value)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public <T> T build(Class<T> classT, Object jsonData) {

        Field[] fields = classT.getDeclaredFields();

        try {

            T instanceClass = ReflectionUtils.accessibleConstructor(classT).newInstance();

            for (Field field : fields) {

                String currentField = field.getName();
                Object fieldValue = this.qjExtract.standardExtraction(jsonData, currentField);

                if (fieldValue == null && this.strictMode) {
                    throw new RuntimeException("Invalid data to mapper, field not found: " + currentField);
                }

                Field field1 = classT.getField(currentField);
                String typeF = field1.getType().toString();

                switch (typeF) {
                    case "class java.lang.Object":
                        field1.set(instanceClass, fieldValue);
                        break;
                    case "class java.lang.Integer":
                        field1.set(instanceClass, Integer.parseInt(String.valueOf(fieldValue)));
                        break;
                    case "class java.lang.String":
                        field1.set(instanceClass, String.valueOf(fieldValue));
                        break;
                    case "interface java.util.List":

                        if (fieldValue != null) {

                            String[] arr = fieldValue.toString()
                                    .replaceFirst("^\\[", "")
                                    .replaceFirst("]$", "")
                                    .split(",");

                            field1.set(instanceClass, Arrays.asList(arr));

                        } else {
                            field1.set(instanceClass, null);
                        }

                        break;
                    case "interface java.util.HashMap":

                        if (fieldValue != null) {

                            field1.set(instanceClass, fieldValue);

                        } else {
                            field1.set(instanceClass, null);
                        }

                        break;
                }
            }

            return (T) instanceClass;

        } catch (
                NoSuchMethodException |
                InvocationTargetException |
                InstantiationException |
                IllegalAccessException |
                NoSuchFieldException e
        ) {
            throw new RuntimeException(e);
        }

    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">build</h6>
     *
     * <p style="color: #CDCDCD">Convert data from any JSON String to Object</p>
     *
     * @return Object (Data Object)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public Object build(Object jsonData, Class<?> className) {
        return null;
    }

}
