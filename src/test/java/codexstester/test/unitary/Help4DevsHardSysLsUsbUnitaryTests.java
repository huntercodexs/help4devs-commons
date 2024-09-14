package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.system.hardsys.Help4DevsHardSys;
import org.junit.Test;

import static com.huntercodexs.demo.services.system.hardsys.command.Help4DevsHardSysCommands.LSUSB;

public class Help4DevsHardSysLsUsbUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void generalSystemInfoByLsUsbCommandTest() {
        Help4DevsHardSys generalSystemInfo = new Help4DevsHardSys(LSUSB);
        generalSystemInfo.resources();
    }

}
