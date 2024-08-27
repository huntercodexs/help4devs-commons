package com.huntercodexs.demo.system.hardsys.core.factory;

import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
import com.huntercodexs.demo.system.hardsys.dto.Help4DevsProcessorDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysHwinfoFactory extends Help4DevsHardSysBase {

    public Help4DevsHardSysHwinfoFactory(HashMap<String, List<String>> resources) {
        this.resources = resources;
    }

    private void hwinfoSystemFactory(List<String> items) {}

    private void hwinfoMachineFactory(List<String> items) {}

    private void hwinfoBatteryFactory(List<String> items) {}

    private void hwinfoMemoryFactory(List<String> items) {}

    private void hwinfoSlotsFactory(List<String> items) {}

    private void hwinfoProcessorFactory(List<String> items) {

        /*HWINFO - Layout*/
        String[] details = new String[]{"model", "family", "speed", "current"};
        String[] replacer = new String[]{"model:$1$2", "family:$1", "speed:$1", "current:$1 $2"};
        String[] pattern = new String[]{
                "(i[0-9]+|AMD|NVIDIA)([-_.0-9a-zA-Z]+)",
                "(Intel|AMD|NVIDIA)",
                "([0-9]+\\.[0-9]+[MG]Hz)",
                "([0-9]+) (MHz)"};

        int cores = items.size();
        int countFieldsProcessor = Help4DevsProcessorDto.class.getDeclaredFields().length - 1;
        String processorName = items.get(0).replaceAll("type: processor source: ", "");

        List<String> processorList = new ArrayList<>();

        /*
         * IMPORTANT: Vector Initialization - according countFieldsProcessor
         * This feature it will be used in the Help4DevsProcessorDto class to builder the DTO object
         * */
        for (int i = 0; i < countFieldsProcessor; i++) {
            processorList.add(null);
        }

        processorList.set(0, String.valueOf(cores));
        processorList.set(1, processorName.replaceAll(",", ""));

        for (int  i = 0; i < details.length; i++) {
            processorList.set(i+2, stringExtractor(processorName, details[i], pattern[i], replacer[i], i));
        }

        processorList.set(27, stringList(items, "type: processor source: "));

        processorList.set(28, listExtractor(
                items,
                "speedCore",
                "type: processor source: ",
                "([0-9]+) (MHz)",
                "speedCore:$1 $2"));

        this.resources.put(processor(), processorList);

    }

    private void hwinfoGraphicsFactory(List<String> items) {}

    private void hwinfoAudioFactory(List<String> items) {}

    private void hwinfoNetworkFactory(List<String> items) {}

    private void hwinfoDriversFactory(List<String> items) {}

    private void hwinfoPartitionFactory(List<String> items) {}

    private void hwinfoUsbFactory(List<String> items) {}

    private void hwinfoSensorsFactory(List<String> items) {}

    private void hwinfoRunningFactory(List<String> items) {}

    private void hwinfoMonitorFactory(List<String> items) {}

    private void hwinfoBiosFactory(List<String> items) {}

    private void hwinfoBaseboardFactory(List<String> items) {}

    private void hwinfoChassisFactory(List<String> items) {}

    private void hwinfoCacheFactory(List<String> items) {}

    private void hwinfoConnectorFactory(List<String> items) {}

    private void hwinfoKeyboardFactory(List<String> items) {}

    private void hwinfoMouseFactory(List<String> items) {}

    private void hwinfoHubFactory(List<String> items) {}

    private void hwinfoSwitcherFactory(List<String> items) {}

    private void hwinfoModemFactory(List<String> items) {}

    private void hwinfoDiskFactory(List<String> items) {}

    private void hwinfoBluetoothFactory(List<String> items) {}

    private void hwinfoVideoFactory(List<String> items) {}

    private void hwinfoStorageFactory(List<String> items) {}

    private void hwinfoBridgeFactory(List<String> items) {}

    private void hwinfoNetworkInterfaceFactory(List<String> items) {}

    private void hwinfoUnknownFactory(List<String> items) {}

    public void factory() {
        List<String> removeList = new ArrayList<>();

        this.resources.forEach((key, list) -> {

            List<String> makeList = new ArrayList<>();

            if (key.contains("_group") || key.equals("all")) {
                removeList.add(key);
            } else {

                for (String item : list) {
                    if (item.isEmpty()) continue;

                    String value = "type: " +key+ " source: "+item
                            .replaceAll("\\[", "(")
                            .replaceAll("]", ")")
                            .replaceAll("-{2,}", " description: ");

                    makeList.add(value);
                    this.resources.put(key, makeList);

                }
            }
        });

        for (String remove : removeList) {
            this.resources.remove(remove);
        }

        hwinfoProcessorFactory(this.resources.get(processor()));

    }

}
