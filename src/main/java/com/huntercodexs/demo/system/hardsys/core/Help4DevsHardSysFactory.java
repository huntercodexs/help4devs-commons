package com.huntercodexs.demo.system.hardsys.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Help4DevsHardSysFactory extends Help4DevsHardSysBase {

    public Help4DevsHardSysFactory(HashMap<String, List<String>> resources) {
        this.resources = resources;
    }

    public void make() {

        List<String> makeList = new ArrayList<>();
        List<String> removeList = new ArrayList<>();

        this.resources.forEach((key, list) -> {
            if (key.contains("_group") || key.equals("all")) {
                removeList.add(key);
            } else {

                for (String item : list) {
                    if (item.isEmpty()) continue;
                    item = "type: " +key+ " source: "+item
                            .replaceAll(" ", "-")
                            .replaceAll(":", ".@.")
                            .replaceAll("-{2,}", " description: ");

                    makeList.add(item);
                }

                this.resources.put(key, makeList);

            }

        });

        for (String remove : removeList) {
            this.resources.remove(remove);
        }
    }

}
