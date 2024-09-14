package com.huntercodexs.demo.services.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsRunningDto {

    private List<Help4DevsRunning> details;

    public Help4DevsRunningDto() {
        this.details = new ArrayList<>();
    }

    public List<Help4DevsRunning> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsRunning> details) {
        this.details = details;
    }

    public String toString() {
        return "Help4DevsRunningDto[" +
                "details=" + details +
                "]";
    }

    public void addRunning(Help4DevsRunning running) {
        this.details.add(running);
    }

    public static class Help4DevsRunning {

        private String id;
        private String name;
        private String source;
        private String client;
        private String processes;
        private String uptime;
        private String init;
        private String runlevel;
        private String compilers;
        private String gcc;
        private String shell;
        private String version;
        private String inxi;
        private String description;
        private List<String> features;

        public Help4DevsRunning() {}

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getProcesses() {
            return processes;
        }

        public void setProcesses(String processes) {
            this.processes = processes;
        }

        public String getUptime() {
            return uptime;
        }

        public void setUptime(String uptime) {
            this.uptime = uptime;
        }

        public String getInit() {
            return init;
        }

        public void setInit(String init) {
            this.init = init;
        }

        public String getRunlevel() {
            return runlevel;
        }

        public void setRunlevel(String runlevel) {
            this.runlevel = runlevel;
        }

        public String getCompilers() {
            return compilers;
        }

        public void setCompilers(String compilers) {
            this.compilers = compilers;
        }

        public String getGcc() {
            return gcc;
        }

        public void setGcc(String gcc) {
            this.gcc = gcc;
        }

        public String getShell() {
            return shell;
        }

        public void setShell(String shell) {
            this.shell = shell;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getInxi() {
            return inxi;
        }

        public void setInxi(String inxi) {
            this.inxi = inxi;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<String> getFeatures() {
            return features;
        }

        public void setFeatures(List<String> features) {
            this.features = features;
        }

        public String toString() {
            return "Help4DevsRunning[" +
                    "id=" + id +
                    ", client=" + client +
                    ", name=" + name +
                    ", source=" + source +
                    ", processes=" + processes +
                    ", uptime=" + uptime +
                    ", init=" + init +
                    ", runlevel=" + runlevel +
                    ", compilers=" + compilers +
                    ", gcc=" + gcc +
                    ", shell=" + shell +
                    ", version=" + version +
                    ", inxi=" + inxi +
                    ", description=" + description +
                    ", features=" + features +
                    "]";
        }
    }
}
