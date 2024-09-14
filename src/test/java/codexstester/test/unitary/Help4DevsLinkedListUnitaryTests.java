package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.data.Help4DevsLinkedListService.*;
import org.junit.Test;

public class Help4DevsLinkedListUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void linkedListTest() {

        LinkedList linkedList = new LinkedList();

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

}
