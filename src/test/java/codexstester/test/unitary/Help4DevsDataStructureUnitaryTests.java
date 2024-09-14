package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.google.gson.Gson;
import com.huntercodexs.demo.services.data.Help4DevsDataStructureService;
import net.minidev.json.JSONObject;
import org.junit.Test;

import java.util.*;

import static com.huntercodexs.demo.services.data.Help4DevsDataStructureService.*;

public class Help4DevsDataStructureUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void arrayVectorTest() {

        int[] intVector = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        String[] strVector = new String[]{"AAA", "BBB", "CCC", "DDD", "EEE"};
        boolean[] bolVector = new boolean[]{true, false, true, false, true};
        float[] floVector = new float[]{1.3f, 2.5f, 4.7f, 5.8f};
        double[] douVector = new double[]{3.98, 4.54, 5.33, 6.12, 7.56, 8.55};

        System.out.println("\nint Vector");
        for (int i : intVector) {
            System.out.print(i + " ");
        }

        System.out.println("\nString Vector");
        for (String s : strVector) {
            System.out.print(s + " ");
        }

        System.out.println("\nboolean Vector");
        for (boolean b : bolVector) {
            System.out.print(b + " ");
        }

        System.out.println("\nfloat Vector");
        for (float v : floVector) {
            System.out.print(v + " ");
        }

        System.out.println("\ndouble Vector");
        for (double v : douVector) {
            System.out.print(v + " ");
        }

    }

    @Test
    public void intVectorTest() {
        int[] result = intVector(1234567890);
        codexsTesterAssertInt(result[0], 1);
        codexsTesterAssertInt(result[1], 2);
        codexsTesterAssertInt(result[2], 3);
        codexsTesterAssertInt(result[3], 4);
        codexsTesterAssertInt(result[4], 5);
        codexsTesterAssertInt(result[5], 6);
        codexsTesterAssertInt(result[6], 7);
        codexsTesterAssertInt(result[7], 8);
        codexsTesterAssertInt(result[8], 9);
        codexsTesterAssertInt(result[9], 0);
    }

    @Test
    public void charVectorTest() {
        String[] result = charVector("1234567890XYZ[~");
        codexsTesterAssertText(result[0], "1");
        codexsTesterAssertText(result[1], "2");
        codexsTesterAssertText(result[2], "3");
        codexsTesterAssertText(result[3], "4");
        codexsTesterAssertText(result[4], "5");
        codexsTesterAssertText(result[5], "6");
        codexsTesterAssertText(result[6], "7");
        codexsTesterAssertText(result[7], "8");
        codexsTesterAssertText(result[8], "9");
        codexsTesterAssertText(result[9], "0");
        codexsTesterAssertText(result[10], "X");
        codexsTesterAssertText(result[11], "Y");
        codexsTesterAssertText(result[12], "Z");
        codexsTesterAssertText(result[13], "[");
        codexsTesterAssertText(result[14], "~");
    }

    @Test
    public void listTest() {

        List<Integer> integerList = Arrays.asList(1,2,3,4,5);
        List<String> stringList = Arrays.asList("AAA","BBB","CCC","DDD","EEE");
        List<Boolean> booleanList = Arrays.asList(true,true,true,true,true);
        List<Float> floatList = Arrays.asList(1.5f,1.5f,1.5f,1.5f,1.5f);
        List<Double> doubleList = Arrays.asList(1.44,1.44,1.44,1.44,1.44);
        List<JSONObject> jsonList = Arrays.asList(new JSONObject(),new JSONObject(),new JSONObject());
        List<Object> objectList = Arrays.asList(1,"AAA",null,new Object());

        System.out.println("\nInteger List");
        for (Integer item : integerList) {
            System.out.print(item+" ");
        }

        System.out.println("\nString List");
        for (String item : stringList) {
            System.out.print(item+" ");
        }

        System.out.println("\nBoolean List");
        for (Boolean item : booleanList) {
            System.out.print(item+" ");
        }

        System.out.println("\nFloat List");
        for (Float item : floatList) {
            System.out.print(item+" ");
        }

        System.out.println("\nDouble List");
        for (Double item : doubleList) {
            System.out.print(item+" ");
        }

        System.out.println("\nJSONObject List");
        for (JSONObject item : jsonList) {
            System.out.print(item+" ");
        }

        System.out.println("\nObject List");
        for (Object item : objectList) {
            System.out.print(item+" ");
        }

    }

    @Test
    public void createIntegerListTest() {
        List<Integer> result = createIntegerList(1234567888);
        codexsTesterAssertInt(result.get(0), 1);

        result = createIntegerList("1234567888");
        codexsTesterAssertInt(result.get(0), 1);
    }

    @Test
    public void arrayListTest() {

        ArrayList<Integer> intArrayList = new ArrayList<>();
        Collections.addAll(intArrayList, 1,2,3,4,5);

        ArrayList<String> strArrayList = new ArrayList<>();
        Collections.addAll(strArrayList, "AAA", "BBB", "CCC", "DDD", "EEE");

        ArrayList<Boolean> bolArrayList = new ArrayList<>();
        Collections.addAll(bolArrayList, true, true, false, true, false);

        ArrayList<Float> floatArrayList = new ArrayList<>();
        Collections.addAll(floatArrayList, 1.5f,2.5f,3.5f,4.5f,5.5f);

        ArrayList<Double> doubleArrayList = new ArrayList<>();
        Collections.addAll(doubleArrayList, 1.55,2.55,3.55,4.55,5.55);

        ArrayList<JSONObject> jsonArrayList = new ArrayList<>();
        Collections.addAll(jsonArrayList, new JSONObject(),new JSONObject(),new JSONObject());

        ArrayList<Object> objectArrayList = new ArrayList<>();
        Collections.addAll(objectArrayList, 1, "AAA", new JSONObject(), null, 1.5f);

        System.out.println("\nInteger ArrayList");
        for (Integer item : intArrayList) {
            System.out.print(item+" ");
        }

        System.out.println("\nString ArrayList");
        for (String item : strArrayList) {
            System.out.print(item+" ");
        }

        System.out.println("\nBoolean ArrayList");
        for (Boolean item : bolArrayList) {
            System.out.print(item+" ");
        }

        System.out.println("\nFloat ArrayList");
        for (Float item : floatArrayList) {
            System.out.print(item+" ");
        }

        System.out.println("\nDouble ArrayList");
        for (Double item : doubleArrayList) {
            System.out.print(item+" ");
        }

        System.out.println("\nJSONObject ArrayList");
        for (JSONObject item : jsonArrayList) {
            System.out.print(item+" ");
        }

        System.out.println("\nObject ArrayList");
        for (Object item : objectArrayList) {
            System.out.print(item+" ");
        }
    }

    @Test
    public void hashMapTest() {

        HashMap<Integer, Integer> integerHashMap = new HashMap<Integer, Integer>(){{
            put(1,1);
            put(2,2);
            put(3,3);
            put(4,4);
            put(5,5);
        }};

        HashMap<String, String> stringHashMap = new HashMap<String, String>(){{
            put("AAA","AAA");
            put("BBB","BBB");
            put("CCC","CCC");
            put("DDD","DDD");
            put("EEE","EEE");
        }};
        HashMap<Boolean, Boolean> booleanHashMap = new HashMap<Boolean, Boolean>(){{
            put(true,true);
            put(false,true);
        }};
        HashMap<Float, Float> floatHashMap = new HashMap<Float, Float>(){{
            put(1.5f,1.5f);
            put(2.5f,2.5f);
            put(3.5f,3.5f);
            put(4.5f,4.5f);
            put(5.5f,5.5f);
        }};
        HashMap<Double, Double> doubleHashMap = new HashMap<Double, Double>(){{
            put(1.55,1.55);
            put(2.55,2.55);
            put(3.55,3.55);
            put(4.55,4.55);
            put(5.55,5.55);
        }};
        HashMap<JSONObject, JSONObject> jsonHashMap = new HashMap<JSONObject, JSONObject>(){{
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
        }};
        HashMap<Object, Object> objectHashMap = new HashMap<Object, Object>(){{
            put(1,1);
            put(2,"AAA");
            put(3,null);
            put(4,new JSONObject());
            put(5,5.334);
        }};

        System.out.println("\nInteger HashMap");
        integerHashMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nString HashMap");
        stringHashMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nBoolean HashMap");
        booleanHashMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nFloat HashMap");
        floatHashMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nDouble HashMap");
        doubleHashMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nJSONObject HashMap");
        jsonHashMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nObject HashMap");
        objectHashMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

    }

    @Test
    public void createIntegerHashMapTest() {
        HashMap<Integer, Object> result = createIntegerHashMap(1234567888);
        codexsTesterAssertObject(result.get(0), 1);

        result = createIntegerHashMap("1234567888");
        codexsTesterAssertObject(result.get(3), 4);
    }

    @Test
    public void hashTableTest() {

        Hashtable<Integer, Integer> integerHashtable = new Hashtable<Integer, Integer>(){{
            put(1,1);
            put(2,2);
            put(3,3);
            put(4,4);
            put(5,5);
        }};
        Hashtable<String, String> stringHashtable = new Hashtable<String, String>(){{
            put("AAA","AAA");
            put("BBB","BBB");
            put("CCC","CCC");
            put("DDD","DDD");
            put("EEE","EEE");
        }};
        Hashtable<Boolean, Boolean> booleanHashtable = new Hashtable<Boolean, Boolean>(){{
            put(true,true);
            put(false,true);
        }};
        Hashtable<Float, Float> floatHashtable = new Hashtable<Float, Float>(){{
            put(1.5f,1.5f);
            put(2.5f,2.5f);
            put(3.5f,3.5f);
            put(4.5f,4.5f);
            put(5.5f,5.5f);
        }};
        Hashtable<Double, Double> doubleHashtable = new Hashtable<Double, Double>(){{
            put(1.55,1.55);
            put(2.55,2.55);
            put(3.55,3.55);
            put(4.55,4.55);
            put(5.55,5.55);
        }};
        Hashtable<JSONObject, JSONObject> jsonHashtable = new Hashtable<JSONObject, JSONObject>(){{
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
        }};
        Hashtable<Object, Object> objectHashtable = new Hashtable<Object, Object>(){{
            put(1,1);
            put(2,"AAA");
            put(3,"null"); //The Hashtable doesn't accept null values: java.lang.NullPointerException
            put(4,new JSONObject());
            put(5,5.334);
        }};

        System.out.println("\nInteger Hashtable");
        integerHashtable.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nString Hashtable");
        stringHashtable.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nBoolean Hashtable");
        booleanHashtable.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nFloat Hashtable");
        floatHashtable.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nDouble Hashtable");
        doubleHashtable.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nJSONObject Hashtable");
        jsonHashtable.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nObject Hashtable");
        objectHashtable.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

    }

    @Test
    public void mapTest() {

        Map<Integer, Integer> integerMap = new HashMap<Integer, Integer>(){{
            put(1,1);
            put(2,2);
            put(3,3);
            put(4,4);
            put(5,5);
        }};

        Map<String, String> stringMap = new HashMap<String, String>(){{
            put("AAA","AAA");
            put("BBB","BBB");
            put("CCC","CCC");
            put("DDD","DDD");
            put("EEE","EEE");
        }};
        Map<Boolean, Boolean> booleanMap = new HashMap<Boolean, Boolean>(){{
            put(true,true);
            put(false,true);
        }};
        Map<Float, Float> floatMap = new HashMap<Float, Float>(){{
            put(1.5f,1.5f);
            put(2.5f,2.5f);
            put(3.5f,3.5f);
            put(4.5f,4.5f);
            put(5.5f,5.5f);
        }};
        Map<Double, Double> doubleMap = new HashMap<Double, Double>(){{
            put(1.55,1.55);
            put(2.55,2.55);
            put(3.55,3.55);
            put(4.55,4.55);
            put(5.55,5.55);
        }};
        Map<JSONObject, JSONObject> jsonMap = new HashMap<JSONObject, JSONObject>(){{
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
        }};
        Map<Object, Object> objectMap = new HashMap<Object, Object>(){{
            put(1,1);
            put(2,"AAA");
            put(3,null);
            put(4,new JSONObject());
            put(5,5.334);
        }};

        System.out.println("\nInteger Map");
        integerMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nString Map");
        stringMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nBoolean Map");
        booleanMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nFloat Map");
        floatMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nDouble Map");
        doubleMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nJSONObject Map");
        jsonMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nObject Map");
        objectMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

    }

    @Test
    public void likedListTest() {

        LinkedList<Integer> intLinkedList = new LinkedList<>();
        Collections.addAll(intLinkedList, 1,2,3,4,5);

        LinkedList<String> strLinkedList = new LinkedList<>();
        Collections.addAll(strLinkedList, "AAA", "BBB", "CCC", "DDD", "EEE");

        LinkedList<Boolean> bolLinkedList = new LinkedList<>();
        Collections.addAll(bolLinkedList, true, true, false, true, false);

        LinkedList<Float> floatLinkedList = new LinkedList<>();
        Collections.addAll(floatLinkedList, 1.5f,2.5f,3.5f,4.5f,5.5f);

        LinkedList<Double> doubleLinkedList = new LinkedList<>();
        Collections.addAll(doubleLinkedList, 1.55,2.55,3.55,4.55,5.55);

        LinkedList<JSONObject> jsonLinkedList = new LinkedList<>();
        Collections.addAll(jsonLinkedList, new JSONObject(),new JSONObject(),new JSONObject());

        LinkedList<Object> objectLinkedList = new LinkedList<>();
        Collections.addAll(objectLinkedList, 1, "AAA", new JSONObject(), null, 1.5f);

        System.out.println("\nInteger LinkedList");
        for (Integer item : intLinkedList) {
            System.out.print(item+" ");
        }

        System.out.println("\nString LinkedList");
        for (String item : strLinkedList) {
            System.out.print(item+" ");
        }

        System.out.println("\nBoolean LinkedList");
        for (Boolean item : bolLinkedList) {
            System.out.print(item+" ");
        }

        System.out.println("\nFloat LinkedList");
        for (Float item : floatLinkedList) {
            System.out.print(item+" ");
        }

        System.out.println("\nDouble LinkedList");
        for (Double item : doubleLinkedList) {
            System.out.print(item+" ");
        }

        System.out.println("\nJSONObject LinkedList");
        for (JSONObject item : jsonLinkedList) {
            System.out.print(item+" ");
        }

        System.out.println("\nObject LinkedList");
        for (Object item : objectLinkedList) {
            System.out.print(item+" ");
        }

    }

    @Test
    public void likedHashMapTest() {

        LinkedHashMap<Integer, Integer> integerLinkedHashMap = new LinkedHashMap<Integer, Integer>(){{
            put(1,1);
            put(2,2);
            put(3,3);
            put(4,4);
            put(5,5);
        }};

        LinkedHashMap<String, String> stringLinkedHashMap = new LinkedHashMap<String, String>(){{
            put("AAA","AAA");
            put("BBB","BBB");
            put("CCC","CCC");
            put("DDD","DDD");
            put("EEE","EEE");
        }};
        LinkedHashMap<Boolean, Boolean> booleanLinkedHashMap = new LinkedHashMap<Boolean, Boolean>(){{
            put(true,true);
            put(false,true);
        }};
        LinkedHashMap<Float, Float> floatLinkedHashMap = new LinkedHashMap<Float, Float>(){{
            put(1.5f,1.5f);
            put(2.5f,2.5f);
            put(3.5f,3.5f);
            put(4.5f,4.5f);
            put(5.5f,5.5f);
        }};
        LinkedHashMap<Double, Double> doubleLinkedHashMap = new LinkedHashMap<Double, Double>(){{
            put(1.55,1.55);
            put(2.55,2.55);
            put(3.55,3.55);
            put(4.55,4.55);
            put(5.55,5.55);
        }};
        LinkedHashMap<JSONObject, JSONObject> jsonLinkedHashMap = new LinkedHashMap<JSONObject, JSONObject>(){{
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
        }};
        LinkedHashMap<Object, Object> objectLinkedHashMap = new LinkedHashMap<Object, Object>(){{
            put(1,1);
            put(2,"AAA");
            put(3,null);
            put(4,new JSONObject());
            put(5,5.334);
        }};

        System.out.println("\nInteger LinkedHashMap");
        integerLinkedHashMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nString LinkedHashMap");
        stringLinkedHashMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nBoolean LinkedHashMap");
        booleanLinkedHashMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nFloat LinkedHashMap");
        floatLinkedHashMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nDouble LinkedHashMap");
        doubleLinkedHashMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nJSONObject LinkedHashMap");
        jsonLinkedHashMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nObject LinkedHashMap");
        objectLinkedHashMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

    }

    @Test
    public void linkedHashSetTest() {

        LinkedHashSet<Integer> intLinkedHashSet = new LinkedHashSet<>();
        Collections.addAll(intLinkedHashSet, 1,2,3,4,5);

        LinkedHashSet<String> strLinkedHashSet = new LinkedHashSet<>();
        Collections.addAll(strLinkedHashSet, "AAA", "BBB", "CCC", "DDD", "EEE");

        LinkedHashSet<Boolean> bolLinkedHashSet = new LinkedHashSet<>();
        Collections.addAll(bolLinkedHashSet, true, true, false, true, false);

        LinkedHashSet<Float> floatLinkedHashSet = new LinkedHashSet<>();
        Collections.addAll(floatLinkedHashSet, 1.5f,2.5f,3.5f,4.5f,5.5f);

        LinkedHashSet<Double> doubleLinkedHashSet = new LinkedHashSet<>();
        Collections.addAll(doubleLinkedHashSet, 1.55,2.55,3.55,4.55,5.55);

        LinkedHashSet<JSONObject> jsonLinkedHashSet = new LinkedHashSet<>();
        Collections.addAll(jsonLinkedHashSet, new JSONObject(),new JSONObject(),new JSONObject());

        LinkedHashSet<Object> objectLinkedHashSet = new LinkedHashSet<>();
        Collections.addAll(objectLinkedHashSet, 1, "AAA", new JSONObject(), null, 1.5f);

        System.out.println("\nInteger LinkedHashSet");
        for (Integer item : intLinkedHashSet) {
            System.out.print(item+" ");
        }

        System.out.println("\nString LinkedHashSet");
        for (String item : strLinkedHashSet) {
            System.out.print(item+" ");
        }

        System.out.println("\nBoolean LinkedHashSet");
        for (Boolean item : bolLinkedHashSet) {
            System.out.print(item+" ");
        }

        System.out.println("\nFloat LinkedHashSet");
        for (Float item : floatLinkedHashSet) {
            System.out.print(item+" ");
        }

        System.out.println("\nDouble LinkedHashSet");
        for (Double item : doubleLinkedHashSet) {
            System.out.print(item+" ");
        }

        System.out.println("\nJSONObject LinkedHashSet");
        for (JSONObject item : jsonLinkedHashSet) {
            System.out.print(item+" ");
        }

        System.out.println("\nObject LinkedHashSet");
        for (Object item : objectLinkedHashSet) {
            System.out.print(item+" ");
        }
    }

    @Test
    public void hashSetTest() {

        HashSet<Integer> intHashSet = new HashSet<>();
        Collections.addAll(intHashSet, 1,2,3,4,5);

        HashSet<String> strHashSet = new HashSet<>();
        Collections.addAll(strHashSet, "AAA", "BBB", "CCC", "DDD", "EEE");

        HashSet<Boolean> bolHashSet = new HashSet<>();
        Collections.addAll(bolHashSet, true, true, false, true, false);

        HashSet<Float> floatHashSet = new HashSet<>();
        Collections.addAll(floatHashSet, 1.5f,2.5f,3.5f,4.5f,5.5f);

        HashSet<Double> doubleHashSet = new HashSet<>();
        Collections.addAll(doubleHashSet, 1.55,2.55,3.55,4.55,5.55);

        HashSet<JSONObject> jsonHashSet = new HashSet<>();
        Collections.addAll(jsonHashSet, new JSONObject(),new JSONObject(),new JSONObject());

        HashSet<Object> objectHashSet = new HashSet<>();
        Collections.addAll(objectHashSet, 1, "AAA", new JSONObject(), null, 1.5f);

        System.out.println("\nInteger HashSet");
        for (Integer item : intHashSet) {
            System.out.print(item+" ");
        }

        System.out.println("\nString HashSet");
        for (String item : strHashSet) {
            System.out.print(item+" ");
        }

        System.out.println("\nBoolean HashSet");
        for (Boolean item : bolHashSet) {
            System.out.print(item+" ");
        }

        System.out.println("\nFloat HashSet");
        for (Float item : floatHashSet) {
            System.out.print(item+" ");
        }

        System.out.println("\nDouble HashSet");
        for (Double item : doubleHashSet) {
            System.out.print(item+" ");
        }

        System.out.println("\nJSONObject HashSet");
        for (JSONObject item : jsonHashSet) {
            System.out.print(item+" ");
        }

        System.out.println("\nObject HashSet");
        for (Object item : objectHashSet) {
            System.out.print(item+" ");
        }
    }

    @Test
    public void treeSetTest() {

        TreeSet<Integer> intTreeSet = new TreeSet<>();
        Collections.addAll(intTreeSet, 1,2,3,4,5);

        TreeSet<String> strTreeSet = new TreeSet<>();
        Collections.addAll(strTreeSet, "AAA", "BBB", "CCC", "DDD", "EEE");

        TreeSet<Boolean> bolTreeSet = new TreeSet<>();
        Collections.addAll(bolTreeSet, true, true, false, true, false);

        TreeSet<Float> floatTreeSet = new TreeSet<>();
        Collections.addAll(floatTreeSet, 1.5f,2.5f,3.5f,4.5f,5.5f);

        TreeSet<Double> doubleTreeSet = new TreeSet<>();
        Collections.addAll(doubleTreeSet, 1.55,2.55,3.55,4.55,5.55);

        ////java.lang.ClassCastException: net.minidev.json.JSONObject cannot be cast to java.lang.Comparable
        //TreeSet<JSONObject> jsonTreeSet = new TreeSet<>();
        //Collections.addAll(jsonTreeSet, new JSONObject(),new JSONObject(),new JSONObject());

        ////java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
        //TreeSet<Object> objectTreeSet = new TreeSet<>();
        //Collections.addAll(objectTreeSet, 1, "AAA", new JSONObject(), null, 1.5f);

        System.out.println("\nInteger TreeSet");
        for (Integer item : intTreeSet) {
            System.out.print(item+" ");
        }

        System.out.println("\nString TreeSet");
        for (String item : strTreeSet) {
            System.out.print(item+" ");
        }

        System.out.println("\nBoolean TreeSet");
        for (Boolean item : bolTreeSet) {
            System.out.print(item+" ");
        }

        System.out.println("\nFloat TreeSet");
        for (Float item : floatTreeSet) {
            System.out.print(item+" ");
        }

        System.out.println("\nDouble TreeSet");
        for (Double item : doubleTreeSet) {
            System.out.print(item+" ");
        }

        /*System.out.println("\nJSONObject TreeSet");
        for (JSONObject item : jsonTreeSet) {
            System.out.print(item+" ");
        }*/

        /*System.out.println("\nObject TreeSet");
        for (Object item : objectTreeSet) {
            System.out.print(item+" ");
        }*/
    }

    @Test
    public void treeMapTest() {

        TreeMap<Integer, Integer> integerTreeMap = new TreeMap<Integer, Integer>(){{
            put(1,1);
            put(2,2);
            put(3,3);
            put(4,4);
            put(5,5);
        }};

        TreeMap<String, String> stringTreeMap = new TreeMap<String, String>(){{
            put("AAA","AAA");
            put("BBB","BBB");
            put("CCC","CCC");
            put("DDD","DDD");
            put("EEE","EEE");
        }};
        TreeMap<Boolean, Boolean> booleanTreeMap = new TreeMap<Boolean, Boolean>(){{
            put(true,true);
            put(false,true);
        }};
        TreeMap<Float, Float> floatTreeMap = new TreeMap<Float, Float>(){{
            put(1.5f,1.5f);
            put(2.5f,2.5f);
            put(3.5f,3.5f);
            put(4.5f,4.5f);
            put(5.5f,5.5f);
        }};
        TreeMap<Double, Double> doubleTreeMap = new TreeMap<Double, Double>(){{
            put(1.55,1.55);
            put(2.55,2.55);
            put(3.55,3.55);
            put(4.55,4.55);
            put(5.55,5.55);
        }};
        ////java.lang.ClassCastException: net.minidev.json.JSONObject cannot be cast to java.lang.Comparable
        /*TreeMap<JSONObject, JSONObject> jsonTreeMap = new TreeMap<JSONObject, JSONObject>(){{
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
            put( new JSONObject(), new JSONObject());
        }};*/
        TreeMap<Object, Object> objectTreeMap = new TreeMap<Object, Object>(){{
            put(1,1);
            put(2,"AAA");
            put(3,null);
            put(4,new JSONObject());
            put(5,5.334);
        }};

        System.out.println("\nInteger TreeMap");
        integerTreeMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nString TreeMap");
        stringTreeMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nBoolean TreeMap");
        booleanTreeMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nFloat TreeMap");
        floatTreeMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        System.out.println("\nDouble TreeMap");
        doubleTreeMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

        /*System.out.println("\nJSONObject TreeMap");
        jsonTreeMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });*/

        System.out.println("\nObject TreeMap");
        objectTreeMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: " + value + " ");
        });

    }

    @Test
    public void jsonTest() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("one", 1);
        jsonObject.put("two", 2);
        jsonObject.put("three", 3);
        jsonObject.put("four", 4);
        jsonObject.put("five", 5);

        System.out.println("\nJSON JSONObject");
        jsonObject.forEach((key, value) -> {
            System.out.println("key: " + key + ", value: " + value);
        });
    }

    @Test
    public void gsonTest() {

        Help4DevsDataStructureService.User user = new Help4DevsDataStructureService.User();
        user.setId(1);
        user.setName("John Smith Viz");

        Gson gson = new Gson();
        String json = gson.toJson(user);

        System.out.println(json);

    }

}
