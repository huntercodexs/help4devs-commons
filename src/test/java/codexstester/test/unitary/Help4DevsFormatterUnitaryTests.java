package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import org.junit.Test;

import static com.huntercodexs.demo.services.Help4DevsDataRandomService.randomCnpj;
import static com.huntercodexs.demo.services.Help4DevsDataRandomService.randomCpf;
import static com.huntercodexs.demo.services.Help4DevsFormatterService.*;
import static com.huntercodexs.demo.services.Help4DevsToolsService.stdout;

public class Help4DevsFormatterUnitaryTests extends Help4DevsBridgeTests {

    @Test
    public void cpfFormatterTest() {
        stdout(cpfFormatter(randomCpf().replaceAll("[^0-9]", "")));
    }

    @Test
    public void cnpjFormatterTest() {
        stdout(cnpjFormatter(randomCnpj().replaceAll("[^0-9]", "")));
    }

    @Test
    public void moneyFormatterTest() {
        stdout(moneyFormatter("1000", "real"));
        stdout(moneyFormatter("1000", "dollar"));
        stdout(moneyFormatter("1000", "euro"));
    }

    @Test
    public void dateFormatter_UsingHyphen_Test() {
        dateFormatter("2020/12/01 10:00:00.003", "yyy-MM-dd HH:mm:ss.ms");
        dateFormatter("2021/09/10 10:00:00.007", "yyyy-MM-dd HH:mm:ss.ms");

        dateFormatter("2022/08/31 10:00:00.008", "dd-MM-yyy HH:mm:ss.ms");
        dateFormatter("2019/07/01 10:00:00.009", "dd-MM-yyyy HH:mm:ss.ms");

        dateFormatter("2015/04/01 10:00:00.010", "dd-MM-yy HH:mm:ss.ms");
        dateFormatter("1990/06/23 10:00:00.011", "yy-MM-dd HH:mm:ss.ms");

        dateFormatter("2020/12/01 10:00:00.003", "yyy-MM-dd HH:mm:ss");
        dateFormatter("2021/09/10 10:00:00.007", "yyyy-MM-dd HH:mm:ss");

        dateFormatter("2022/08/31 10:00:00.008", "dd-MM-yyy HH:mm:ss");
        dateFormatter("2019/07/01 10:00:00.009", "dd-MM-yyyy HH:mm:ss");

        dateFormatter("2015/04/01 10:00:00.010", "dd-MM-yy HH:mm:ss");
        dateFormatter("1990/06/23 10:00:00.011", "yy-MM-dd HH:mm:ss");

        dateFormatter("2020/12/01 10:00:00.003", "yyy-MM-dd HH:mm");
        dateFormatter("2021/09/10 10:00:00.007", "yyyy-MM-dd HH:mm");

        dateFormatter("2022/08/31 10:00:00.008", "dd-MM-yyy HH:mm");
        dateFormatter("2019/07/01 10:00:00.009", "dd-MM-yyyy HH:mm");

        dateFormatter("2015/04/01 10:00:00.010", "dd-MM-yy HH:mm");
        dateFormatter("1990/06/23 10:00:00.011", "yy-MM-dd HH:mm");

        dateFormatter("2020/12/01 10:00:00.003", "yyy-MM-dd HH");
        dateFormatter("2021/09/10 10:00:00.007", "yyyy-MM-dd HH");

        dateFormatter("2022/08/31 10:00:00.008", "dd-MM-yyy HH");
        dateFormatter("2019/07/01 10:00:00.009", "dd-MM-yyyy HH");

        dateFormatter("2015/04/01 10:00:00.010", "dd-MM-yy HH");
        dateFormatter("1990/06/23 10:00:00.011", "yy-MM-dd HH");

        dateFormatter("2020/12/01 10:00:00.003", "yyy-MM-dd");
        dateFormatter("2021/09/10 10:00:00.007", "yyyy-MM-dd");

        dateFormatter("2022/08/31 10:00:00.008", "dd-MM-yyy");
        dateFormatter("2019/07/01 10:00:00.009", "dd-MM-yyyy");

        dateFormatter("2015/04/01 10:00:00.010", "dd-MM-yy");
        dateFormatter("1990/06/23 10:00:00.011", "yy-MM-dd");

        dateFormatter("2020/12/01T10:00:00.003Z", "yyy-MM-dd HH:mm:ss.ms");
        dateFormatter("2021/09/10T10:00:00.007Z", "yyyy-MM-dd HH:mm:ss.ms");

        dateFormatter("2022/08/31T10:00:00.008Z", "dd-MM-yyy HH:mm:ss.ms");
        dateFormatter("2019/07/01T10:00:00.009Z", "dd-MM-yyyy HH:mm:ss.ms");

        dateFormatter("2022/08/31T10:00:00.008Z", "dd-MM-yyy HH:mm:ss.ms");
        dateFormatter("2019/07/01T10:00:00.009Z", "dd-MM-yyyy HH:mm:ss.ms");

        dateFormatter("2022/08/31T10:00:00.008Z", "dd-MM-yyy HH:mm:ss");
        dateFormatter("2019/07/01T10:00:00.009Z", "dd-MM-yyyy HH:mm:ss");

        dateFormatter("2015/04/01T10:00:00.010Z", "dd-MM-yy HH:mm:ss");
        dateFormatter("1990/06/23T10:00:00.011Z", "yy-MM-dd HH:mm:ss");
    }

    @Test
    public void dateFormatter_UsingBar_Test() {
        dateFormatter("2020/12/01 10:00:00.003", "yyy/MM/dd HH:mm:ss.ms");
        dateFormatter("2021/09/10 10:00:00.007", "yyyy/MM/dd HH:mm:ss.ms");

        dateFormatter("2022/08/31 10:00:00.008", "dd/MM/yyy HH:mm:ss.ms");
        dateFormatter("2019/07/01 10:00:00.009", "dd/MM/yyyy HH:mm:ss.ms");

        dateFormatter("2015/04/01 10:00:00.010", "dd/MM/yy HH:mm:ss.ms");
        dateFormatter("1990/06/23 10:00:00.011", "yy/MM/dd HH:mm:ss.ms");

        dateFormatter("2020/12/01 10:00:00.003", "yyy/MM/dd HH:mm:ss");
        dateFormatter("2021/09/10 10:00:00.007", "yyyy/MM/dd HH:mm:ss");

        dateFormatter("2022/08/31 10:00:00.008", "dd/MM/yyy HH:mm:ss");
        dateFormatter("2019/07/01 10:00:00.009", "dd/MM/yyyy HH:mm:ss");

        dateFormatter("2015/04/01 10:00:00.010", "dd/MM/yy HH:mm:ss");
        dateFormatter("1990/06/23 10:00:00.011", "yy/MM/dd HH:mm:ss");

        dateFormatter("2020/12/01 10:00:00.003", "yyy/MM/dd HH:mm");
        dateFormatter("2021/09/10 10:00:00.007", "yyyy/MM/dd HH:mm");

        dateFormatter("2022/08/31 10:00:00.008", "dd/MM/yyy HH:mm");
        dateFormatter("2019/07/01 10:00:00.009", "dd/MM/yyyy HH:mm");

        dateFormatter("2015/04/01 10:00:00.010", "dd/MM/yy HH:mm");
        dateFormatter("1990/06/23 10:00:00.011", "yy/MM/dd HH:mm");

        dateFormatter("2020/12/01 10:00:00.003", "yyy/MM/dd HH");
        dateFormatter("2021/09/10 10:00:00.007", "yyyy/MM/dd HH");

        dateFormatter("2022/08/31 10:00:00.008", "dd/MM/yyy HH");
        dateFormatter("2019/07/01 10:00:00.009", "dd/MM/yyyy HH");

        dateFormatter("2015/04/01 10:00:00.010", "dd/MM/yy HH");
        dateFormatter("1990/06/23 10:00:00.011", "yy/MM/dd HH");

        dateFormatter("2020/12/01 10:00:00.003", "yyy/MM/dd");
        dateFormatter("2021/09/10 10:00:00.007", "yyyy/MM/dd");

        dateFormatter("2022/08/31 10:00:00.008", "dd/MM/yyy");
        dateFormatter("2019/07/01 10:00:00.009", "dd/MM/yyyy");

        dateFormatter("2015/04/01 10:00:00.010", "dd/MM/yy");
        dateFormatter("1990/06/23 10:00:00.011", "yy/MM/dd");
    }

}
