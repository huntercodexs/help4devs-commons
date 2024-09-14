package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.data.Help4DevsLinkedListTypedefService.Iterator;
import com.huntercodexs.demo.services.data.Help4DevsLinkedListTypedefService.LinkedList;
import org.junit.Test;

public class Help4DevsLinkedListTypedefUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void linkedListStringTypedefTest() {

        LinkedList<String> linkedList = new LinkedList<String>();

        linkedList.add("ITEM 1");//1
        linkedList.add("ITEM 2");//2
        linkedList.add("ITEM 3");//3
        linkedList.add("ITEM 4");//4
        linkedList.add("ITEM 5");//5
        linkedList.add("ITEM 5");//6
        linkedList.add("ITEM 5");//7
        linkedList.add("ITEM 6");//8
        linkedList.add("ITEM 7");//9

        codexsTesterAssertInt(9, linkedList.getSize());
        codexsTesterAssertText("ITEM 1", linkedList.getFirst().getValue());
        codexsTesterAssertText("ITEM 7", linkedList.getLast().getValue());

        codexsTesterAssertText("ITEM 1", linkedList.get(0).getValue());
        codexsTesterAssertText("ITEM 2", linkedList.get(1).getValue());
        codexsTesterAssertText("ITEM 3", linkedList.get(2).getValue());
        codexsTesterAssertText("ITEM 4", linkedList.get(3).getValue());
        codexsTesterAssertText("ITEM 5", linkedList.get(4).getValue());
        codexsTesterAssertText("ITEM 5", linkedList.get(5).getValue());
        codexsTesterAssertText("ITEM 5", linkedList.get(6).getValue());
        codexsTesterAssertText("ITEM 6", linkedList.get(7).getValue());
        codexsTesterAssertText("ITEM 7", linkedList.get(8).getValue());

        linkedList.remove("ITEM 1");
        codexsTesterAssertInt(8, linkedList.getSize());
        codexsTesterAssertText("ITEM 2", linkedList.getFirst().getValue());

        linkedList.remove("ITEM 7");
        codexsTesterAssertInt(7, linkedList.getSize());
        codexsTesterAssertText("ITEM 6", linkedList.getLast().getValue());
        codexsTesterAssertText("ITEM 4", linkedList.get(2).getValue());

        linkedList.remove("ITEM 3");
        codexsTesterAssertInt(6, linkedList.getSize());
        codexsTesterAssertText("ITEM 5", linkedList.get(2).getValue());
        codexsTesterAssertText("ITEM 5", linkedList.get(3).getValue());
        codexsTesterAssertText("ITEM 5", linkedList.get(4).getValue());

        linkedList.removeAll("ITEM 5");
        for (int i = 0; i < linkedList.getSize(); i++) {
            System.out.println(linkedList.get(i).getValue());
        }
        codexsTesterAssertInt(3, linkedList.getSize());

        linkedList.drop();
        codexsTesterAssertInt(0, linkedList.getSize());

        linkedList.add("ITEM 1");
        linkedList.add("ITEM 2");
        linkedList.add("ITEM 3");
        codexsTesterAssertInt(3, linkedList.getSize());
        codexsTesterAssertText("ITEM 1", linkedList.getFirst().getValue());
        codexsTesterAssertText("ITEM 3", linkedList.getLast().getValue());

        linkedList.remove("ITEM 2");
        codexsTesterAssertInt(2, linkedList.getSize());
        codexsTesterAssertText("ITEM 3", linkedList.get(1).getValue());
    }

    @Test
    public void linkedListIntegerTypedefTest() {

        LinkedList<Integer> linkedList = new LinkedList<Integer>();

        linkedList.add(1);//1
        linkedList.add(2);//2
        linkedList.add(3);//3
        linkedList.add(4);//4
        linkedList.add(5);//5
        linkedList.add(5);//6
        linkedList.add(5);//7
        linkedList.add(6);//8
        linkedList.add(7);//9

        codexsTesterAssertInt(9, linkedList.getSize());
        codexsTesterAssertInt(1, linkedList.getFirst().getValue());
        codexsTesterAssertInt(7, linkedList.getLast().getValue());

        codexsTesterAssertInt(1, linkedList.get(0).getValue());
        codexsTesterAssertInt(2, linkedList.get(1).getValue());
        codexsTesterAssertInt(3, linkedList.get(2).getValue());
        codexsTesterAssertInt(4, linkedList.get(3).getValue());
        codexsTesterAssertInt(5, linkedList.get(4).getValue());
        codexsTesterAssertInt(5, linkedList.get(5).getValue());
        codexsTesterAssertInt(5, linkedList.get(6).getValue());
        codexsTesterAssertInt(6, linkedList.get(7).getValue());
        codexsTesterAssertInt(7, linkedList.get(8).getValue());

        linkedList.remove(1);
        codexsTesterAssertInt(8, linkedList.getSize());
        codexsTesterAssertInt(2, linkedList.getFirst().getValue());

        linkedList.remove(7);
        codexsTesterAssertInt(7, linkedList.getSize());
        codexsTesterAssertInt(6, linkedList.getLast().getValue());
        codexsTesterAssertInt(4, linkedList.get(2).getValue());

        linkedList.remove(3);
        codexsTesterAssertInt(6, linkedList.getSize());
        codexsTesterAssertInt(5, linkedList.get(2).getValue());
        codexsTesterAssertInt(5, linkedList.get(3).getValue());
        codexsTesterAssertInt(5, linkedList.get(4).getValue());

        linkedList.removeAll(5);
        for (int i = 0; i < linkedList.getSize(); i++) {
            System.out.println(linkedList.get(i).getValue());
        }
        codexsTesterAssertInt(3, linkedList.getSize());

        linkedList.drop();
        codexsTesterAssertInt(0, linkedList.getSize());

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        codexsTesterAssertInt(3, linkedList.getSize());
        codexsTesterAssertInt(1, linkedList.getFirst().getValue());
        codexsTesterAssertInt(3, linkedList.getLast().getValue());

        linkedList.remove(2);
        codexsTesterAssertInt(2, linkedList.getSize());
        codexsTesterAssertInt(3, linkedList.get(1).getValue());
    }

    @Test
    public void linkedListBooleanTypedefTest() {

        LinkedList<Boolean> linkedList = new LinkedList<Boolean>();

        linkedList.add(true);//1
        linkedList.add(true);//2
        linkedList.add(true);//3
        linkedList.add(true);//4
        linkedList.add(true);//5
        linkedList.add(true);//6
        linkedList.add(true);//7
        linkedList.add(true);//8
        linkedList.add(true);//9

        codexsTesterAssertInt(9, linkedList.getSize());
        codexsTesterAssertBool(true, linkedList.getFirst().getValue());
        codexsTesterAssertBool(true, linkedList.getLast().getValue());

        codexsTesterAssertBool(true, linkedList.get(0).getValue());
        codexsTesterAssertBool(true, linkedList.get(1).getValue());
        codexsTesterAssertBool(true, linkedList.get(2).getValue());
        codexsTesterAssertBool(true, linkedList.get(3).getValue());
        codexsTesterAssertBool(true, linkedList.get(4).getValue());
        codexsTesterAssertBool(true, linkedList.get(5).getValue());
        codexsTesterAssertBool(true, linkedList.get(6).getValue());
        codexsTesterAssertBool(true, linkedList.get(7).getValue());
        codexsTesterAssertBool(true, linkedList.get(8).getValue());

        linkedList.remove(true);
        codexsTesterAssertInt(8, linkedList.getSize());
        codexsTesterAssertBool(true, linkedList.getFirst().getValue());

        linkedList.remove(true);
        codexsTesterAssertInt(7, linkedList.getSize());
        codexsTesterAssertBool(true, linkedList.getLast().getValue());
        codexsTesterAssertBool(true, linkedList.get(2).getValue());

        linkedList.remove(true);
        codexsTesterAssertInt(6, linkedList.getSize());
        codexsTesterAssertBool(true, linkedList.get(2).getValue());
        codexsTesterAssertBool(true, linkedList.get(3).getValue());
        codexsTesterAssertBool(true, linkedList.get(4).getValue());

        linkedList.removeAll(true);
        for (int i = 0; i < linkedList.getSize(); i++) {
            System.out.println(linkedList.get(i).getValue());
        }
        codexsTesterAssertInt(3, linkedList.getSize());

        linkedList.drop();
        codexsTesterAssertInt(0, linkedList.getSize());

        linkedList.add(false);
        linkedList.add(false);
        linkedList.add(false);
        codexsTesterAssertInt(3, linkedList.getSize());
        codexsTesterAssertBool(false, linkedList.getFirst().getValue());
        codexsTesterAssertBool(false, linkedList.getLast().getValue());

        linkedList.remove(false);
        for (int i = 0; i < linkedList.getSize(); i++) {
            System.out.println(linkedList.get(i).getValue());
        }
        codexsTesterAssertInt(2, linkedList.getSize());
        codexsTesterAssertBool(false, linkedList.get(1).getValue());
    }

    @Test
    public void iteratorTest() {

        LinkedList<Integer> linkedList = new LinkedList<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            linkedList.add(i);
        }
        long end = System.currentTimeMillis();
        long total = end - start;

        System.out.println("[ADD] Elapsed time: " + total + "ms");

        start = System.currentTimeMillis();
        Iterator<Integer> iterator = linkedList.getIterator();
        while (iterator.hasNext()) {
            iterator.getNext();
        }
        end = System.currentTimeMillis();
        total = end - start;

        System.out.println("[GET] Elapsed time: " + total + "ms");

    }

}
