package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.system.hardsys.Help4DevsHardSys;
import org.junit.Test;

import static com.huntercodexs.demo.system.hardsys.command.Help4DevsHardSysCommands.SYSTEMINFO;

public class Help4DevsHardSysSystemInfoUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void generalSystemInfoBySysteminfoWindowsCommandTest() {
        Help4DevsHardSys generalSystemInfo = new Help4DevsHardSys(SYSTEMINFO);
        generalSystemInfo.resources();
    }

}
