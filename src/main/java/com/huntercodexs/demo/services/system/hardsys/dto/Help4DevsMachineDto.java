package com.huntercodexs.demo.services.system.hardsys.dto;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsMachineDto {

    private String qty;
    private List<Help4DevsMachine> details;

    public Help4DevsMachineDto() {
        this.details = new ArrayList<>();
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public List<Help4DevsMachine> getDetails() {
        return details;
    }

    public void setDetails(List<Help4DevsMachine> details) {
        this.details = details;
    }

        public void addMachine(Help4DevsMachine machine) {
        this.details.add(machine);
    }

    public String toString() {
        return "Help4DevsMachineDto[" +
                "qty=" + qty +
                ", details=" + details +
                "]";
    }

    public static class Help4DevsMachine {

        private String uefi;
        private String name;
        private String type;
        private String date;
        private String mobo;
        private String code;
        private String model;
        private String serial;
        private String vendor;
        private String family;
        private String version;
        private String product;
        private String description;
        private String manufacturer;
        private List<String> features;

        public Help4DevsMachine() {}

        public String getUefi() {
            return uefi;
        }

        public void setUefi(String uefi) {
            this.uefi = uefi;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getMobo() {
            return mobo;
        }

        public void setMobo(String mobo) {
            this.mobo = mobo;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getSerial() {
            return serial;
        }

        public void setSerial(String serial) {
            this.serial = serial;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getFamily() {
            return family;
        }

        public void setFamily(String family) {
            this.family = family;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public List<String> getFeatures() {
            return features;
        }

        public void setFeatures(List<String> features) {
            this.features = features;
        }

        public String toString() {
            return "Help4DevsMachine[" +
                    "uefi=" + uefi +
                    ", name=" + name +
                    ", type=" + type +
                    ", date=" + date +
                    ", mobo=" + mobo +
                    ", code=" + code +
                    ", model=" + model +
                    ", serial=" + serial +
                    ", vendor=" + vendor +
                    ", family=" + family +
                    ", version=" + version +
                    ", product=" + product +
                    ", description=" + description +
                    ", manufacturer=" + manufacturer +
                    ", features=" + features +
                    "]";
        }
    }
}
