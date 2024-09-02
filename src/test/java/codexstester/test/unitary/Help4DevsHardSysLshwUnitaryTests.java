package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.system.hardsys.Help4DevsHardSys;
import org.junit.Test;

import static com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands.LSHW;

public class Help4DevsHardSysLshwUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void generalSystemInfoByLshwCommandTest() {
        Help4DevsHardSys generalSystemInfo = new Help4DevsHardSys(LSHW);
        generalSystemInfo.resources();
    }

}
