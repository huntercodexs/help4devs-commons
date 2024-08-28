# HARDSYS

## Overview on processing flow

![hardsys.png](/media/hardsys.png)

## Example on manager a resource

- Resource: Multimedia

#### STEP-1

- **class:** Help4DevsMultimediaDto
- **package:** com.huntercodexs.demo.system.hardsys.dto;
- **description:** Create a DTO clas file for a new resource according example below:

<code>

    package com.huntercodexs.demo.system.hardsys.dto;
    
    import java.util.List;
    
    public class Help4DevsMultimediaDto {
    
        private List<String> id;
        private List<String> name;
        private List<String> source;
        private List<String> description;
    
        public Help4DevsMultimediaDto() {}
    
        public List<String> getId() {
            return id;
        }
    
        public void setId(List<String> id) {
            this.id = id;
        }
    
        public List<String> getName() {
            return name;
        }
    
        public void setName(List<String> name) {
            this.name = name;
        }
    
        public List<String> getSource() {
            return source;
        }
    
        public void setSource(List<String> source) {
            this.source = source;
        }
    
        public List<String> getDescription() {
            return description;
        }
    
        public void setDescription(List<String> description) {
            this.description = description;
        }
    
        public String toString() {
            return "Help4DevsMultimediaDto[" +
                    "id=" + id +
                    ", name=" + name +
                    ", source=" + source +
                    ", description=" + description +
                    ']';
        }
    }

</code>

#### STEP-2.1

- **class:** Help4DevsHardSysResourcesDto
- **package:** com.huntercodexs.demo.system.hardsys.dto;
- **description:** Include the new resource in the main DTO class, see the example below:

<code>

    public class Help4DevsHardSysResourcesDto {
        ...
        private Help4DevsMultimediaDto multimedia;
        ...
    }

</code>

#### STEP-2.2

- **class:** Help4DevsHardSysResourcesDto
- **package:** com.huntercodexs.demo.system.hardsys.dto;
- **description:** Create de get method for the new resource, see the example below:

<code>

    public class Help4DevsHardSysResourcesDto {
        ...
        public Help4DevsMultimediaDto getMultimedia() {
            return multimedia;
        }
        ...
    }

</code>

#### STEP-2.3

- **class:** Help4DevsHardSysResourcesDto
- **package:** com.huntercodexs.demo.system.hardsys.dto;
- **description:** Also create the set method for the new resource, see the example below:

<code>

    public class Help4DevsHardSysResourcesDto {
        ...
        public void setMultimedia(Object multimedia) {
            this.multimedia = (Help4DevsMultimediaDto) multimedia;
        }
        ...
    }

</code>

#### STEP-2.4

- **class:** Help4DevsHardSysResourcesDto
- **package:** com.huntercodexs.demo.system.hardsys.dto;
- **description:** Finally add the new resource in the toString method, see the example below:

<code>

    public class Help4DevsHardSysResourcesDto {
        ...
        public String toString() {
            ...
            return "Help4DevsHardSysMetricsDto[" +
                ...
                ", multimedia=" + multimedia +
            "]";
        }
        ...
    }

</code>

#### STEP-3

- **class:** Help4DevsHardSysBuilder
- **package:** com.huntercodexs.demo.system.hardsys.core;
- **description:** Include the new resource in the builder class, see the example below:

<code>

    public class Help4DevsHardSysBuilder extends Help4DevsHardSysBase {
        ...
        public Help4DevsHardSysResourcesDto build() {
            Help4DevsHardSysResourcesDto dto = new Help4DevsHardSysResourcesDto();
            ...
            dto.setMultimedia(this.transport.get(hardsys("multimedia")));
            return dto;
        }
        ...
    }

</code>

#### STEP-4

- **class:** Help4DevsMultimediaDetails
- **package:** com.huntercodexs.demo.system.hardsys.processing;
- **description:** Create a new detail class for a new resource, see the example below:

<code>

    package com.huntercodexs.demo.system.hardsys.processing;
    
    import com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands;
    import com.huntercodexs.demo.system.hardsys.core.Help4DevsHardSysBase;
    
    import java.util.ArrayList;
    import java.util.List;
    
    import static com.huntercodexs.demo.services.parser.Help4DevsParserService.jsonCreatorRFC8259;
    
    public class Help4DevsMultimediaDetails extends Help4DevsHardSysBase {
    
        private final String resName = "multimedia";
        private final String resNameUpper = resName.toUpperCase();
        private final Help4DevsHardSysCommands command;
        private final List<String> multimediaDetails;
    
        public Help4DevsMultimediaDetails(List<String> devices, Help4DevsHardSysCommands command) {
            this.command = command;
            this.multimediaDetails = devices;
        }
    
        private List<String> detailsFromLinuxCommandInxi() {
            List<String> filter = new ArrayList<>();
            for (String details : this.multimediaDetails) {
                filter.add(details.replaceAll(resNameUpper+": ", resName+": "));
            }
            return filter;
        }
    
        private List<String> detailsFromLinuxCommandHwinfo() {
            List<String> filter = new ArrayList<>();
            for (String details : this.multimediaDetails) {
                if (details == null || details.isEmpty()) continue;
                filter.add(detailsFilter(details, "name"));
            }
            return filter;
        }
    
        private List<String> detailsFromLinuxCommandLshw() {
            List<String> filter = new ArrayList<>();
            for (String details : this.multimediaDetails) {
                filter.add(details.replaceAll(resNameUpper+": ", resName+": "));
            }
            return filter;
        }
    
        private List<String> detailsFromLinuxCommandLscpu() {
            List<String> filter = new ArrayList<>();
            for (String details : this.multimediaDetails) {
                filter.add(details.replaceAll(resNameUpper+": ", resName+": "));
            }
            return filter;
        }
    
        private List<String> detailsFromLinuxCommandLscpu2() {
            List<String> filter = new ArrayList<>();
            for (String details : this.multimediaDetails) {
                filter.add(details.replaceAll(resNameUpper+": ", resName+": "));
            }
            return filter;
        }
    
        private List<String> detailsFromLinuxCommandDmidecode() {
            List<String> filter = new ArrayList<>();
            for (String details : this.multimediaDetails) {
                filter.add(details.replaceAll(resNameUpper+": ", resName+": "));
            }
            return filter;
        }
    
        public String getDetails() {
            if (this.command.equals(Help4DevsHardSysCommands.INXI)) {
                return jsonCreatorRFC8259(detailsFromLinuxCommandInxi(), hardsys(resName));
            } else if (this.command.equals(Help4DevsHardSysCommands.HWINFO)) {
                return jsonCreatorRFC8259(detailsFromLinuxCommandHwinfo(), hardsys(resName));
            } else if (this.command.equals(Help4DevsHardSysCommands.LSHW)) {
                return jsonCreatorRFC8259(detailsFromLinuxCommandLshw(), hardsys(resName));
            } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU)) {
                return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu(), hardsys(resName));
            } else if (this.command.equals(Help4DevsHardSysCommands.LSCPU2)) {
                return jsonCreatorRFC8259(detailsFromLinuxCommandLscpu2(), hardsys(resName));
            } else if (this.command.equals(Help4DevsHardSysCommands.DMIDECODE)) {
                return jsonCreatorRFC8259(detailsFromLinuxCommandDmidecode(), hardsys(resName));
            }
            throw new RuntimeException("Invalid command for "+ hardsys(resName) +": " + this.command);
        }
    
    }

</code>

#### STEP-5

- **class:** Help4DevsHardSysInterface
- **package:** com.huntercodexs.demo.system.hardsys.core;
- **description:** Add the new resource in the interface class, see the example below:

<code>

    package com.huntercodexs.demo.system.hardsys.core;

    import com.huntercodexs.demo.system.hardsys.group.*;
    import com.huntercodexs.demo.system.hardsys.processing.*;
    
    public interface Help4DevsHardSysInterface {
        ...
        Help4DevsMultimediaDetails getMultimedia();
        ...
    }

</code>

#### STEP-6

- **class:** Help4DevsAllGroupDetails
- **package:** com.huntercodexs.demo.system.hardsys.group;
- **description:** Add the new resource to All Groups, see the example below:

<code>
    
    ...
    private List<String> detailsFromLinuxCommandHwinfo() {
        return Arrays.asList(
            ...
            this.allResources.getMultimedia().getDetails());
    }
    ...

</code>

#### STEP-7

- **class:** Help4DevsHardSysHwinfo
- **package:** com.huntercodexs.demo.system.hardsys.command;
- **description:** Add the new resource in the translator process, see the example below:

<code>

    ...
    private String fieldsTranslator(String input) {
        return input
            ...
            .replaceAll("multimedia:", hardsys("multimedia"))
            ...
    }
    ...

</code>

#### STEP-8

- **class:** Help4DevsHardSysHwinfoFactory
- **package:** com.huntercodexs.demo.system.hardsys.core.factory;
- **description:** Programming the Factory class, see the example below:

> In this class all resources that were previously captured are converted in a specific DTO format 

<code>

    ...
    public class Help4DevsHardSysHwinfoFactory extends Help4DevsHardSysBase {
        ...
        private void multimediaFactory(List<String> items) {
    
             //Testing...
             Help4DevsMultimediaDto multimedia = new Help4DevsMultimediaDto();
             multimedia.setId(Arrays.asList("1","2"));
             multimedia.setName(Arrays.asList("test","test"));
    
             this.transport.put(hardsys("multimedia"), multimedia);
    
        }
        ...
        public void factory() {
            ...
            multimediaFactory(this.resources.get(hardsys("multimedia")));
            ...
        }
    }

</code>

####  STEP-9

- **class:** Help4DevsHardSysLayout
- **package:** com.huntercodexs.demo.system.hardsys.core;
- **description:** Add the new resource in the correct layout details, see the example below:

<code>

    ...
    public abstract class Help4DevsHardSysLayout {
        ...
        //HWINFO Version 21.68 (Layout)
        protected static final String[] hwinfoLayout = new String[] {
                ...
                "multimedia:"
        };
        ...
    }

</code>

####  STEP-10

- **class:** Help4DevsHardSysResources
- **package:** com.huntercodexs.demo.system.hardsys.core;
- **description:** Add the new resource main and final HARDSYS resource class, see the example below:

<code>

    ...
    public class Help4DevsHardSysResources extends Help4DevsHardSysBase implements Help4DevsHardSysInterface {
        ...
        private Help4DevsMultimediaDetails multimediaDetails;
        ...
        private void jsonBuilder(HashMap<String, List<String>> resources, Help4DevsHardSysCommands command) {
            ...
            this.multimediaDetails = new Help4DevsMultimediaDetails(resources.get(hardsys("multimedia")), command);
            ...
        }
        ...
        public Help4DevsMultimediaDetails getMultimedia() {
            checkJsonState();
            return multimediaDetails;
        }
        ...
    
    }

    ...

</code>

