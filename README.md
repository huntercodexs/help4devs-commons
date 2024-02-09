# HELP4DEVS - JAVA
A simple repository to serve various functions and methods made in Java

> This repository is a little project to help the Java developers on your daily needs. In this project, you can find 
> many resources that are commonly used in most of the situations during the specific needed or requirement to solve 
> any issue or problem from any case.

# Summary

The content that you will find out in this project are:

- <a href="#base">Base</a>
- <a href="#currency">Currency</a>
- <a href="#database">Database</a>
- <a href="#date">Date</a>
- <a href="#filehandler">FileHandler</a>
- <a href="#filereader">FileReader</a>
- <a href="#path">Path</a>
- <a href="#stringhandler">StringHandler</a>
- <a href="#tools">Tools</a>
- <a href="#validator">Validator</a>

# Base

[Help4DevsBaseService.java](src/main/java/com/huntercodexs/demojobs/services/Help4DevsBaseService.java)

# public static void params(JSONObject... jsonObjects)

This method offer the functionality to receive all arguments was passed as the parameters, for example:

<pre>
    public static void params(JSONObject... jsonObjects) {
        System.out.println(Arrays.toString(jsonObjects));
    }
</pre>

<pre>
    @Test
    public void paramsTests() {
        JSONObject j1 = new JSONObject();
        j1.put("name", "Test 1");

        JSONObject j2 = new JSONObject();
        j2.put("name", "Test 2");

        params(j1, j2);
    }
</pre>

In this case we have the following result

<pre>
[{"name":"Test 1"}, {"name":"Test 2"}]
</pre>

Look at the above result and see that there two objects of type JSON, and they were passed to the method in the separated 
way, however the method have a single expected argument (JSONObject... jsonObjects) that automatically convert these 
parameters to a list parameters with a type define in the signature.

# public static String numberFormatter(int input, String format)

The method numberFormatter is shortcut to format a number in the specific way. Maybe you need format one number from 
left to right filling using a specific number. Below is the common use to this functionality:

<pre>
    public static String numberFormatter(int input, String format) {
        return String.format(format, input);
    }
</pre>

<pre>
    @Test
    public void numberFormatterTest() {
        String result = numberFormatter(1, "%09d");
        System.out.println(result);
    }
</pre>

As a result we have the following output

<pre>
000000001
</pre>

# public static String stringFormatter(String input, String format)

The following method afford the function that format one string in a specific way. Below are the method mentioned in 
this topic, look at it how simple is the implementation

<pre>
    public static String stringFormatter(String input, String format) {
        return String.format(format, input);
    }
</pre>

<pre>
    @Test
    public void stringFormatterTest() {
        String result1 = stringFormatter("XXX", "%10s");
        System.out.println("["+result1+"]");

        String result2 = stringFormatter("XXX", "%-10s");
        System.out.println("["+result2+"]");
    }
</pre>

In this case, we are formatting one string XXX with 10 spaces from the left to right and also from the right to left. It 
can be seen when the second parameter is set using a character "-" in front of the number quantity formatter. The result 
for both cases above are showed below

Using %10s

<pre>
[       XXX]
</pre>

Using %-10s

<pre>
[XXX       ]
</pre>

# public static String fillerFormatter(String input, String filler, String align, int size)

This functionality offer a better way to format one string with a specific char, that can be a number or a string, and 
also can be made from left to right and right to left, below we have one simple example to explain in the better way 
what we're talking about, let's look at it. 

<pre>
    public static String fillerFormatter(String input, String fill, String align, int size) {

        if (!align.equals("left") && !align.equals("right")) {
            System.out.println("Error: use left or right to param [align]");
            return null;
        }

        if (size < 0) {
            System.out.println("Error: use size > 0");
            return null;
        }

        String formatted = input;

        int lenValue = input.length();
        int lenFill = size - lenValue;
        String repeat = fill.repeat(lenFill);

        if (align.equals("left")) {
            formatted = input + repeat;
        } else {
            formatted = repeat + input;
        }

        return formatted;
    }
</pre>

<pre>
    @Test
    public void fillerFormatterTest() {
        String result1 = fillerFormatter("XXX", "F", "left", 20);
        System.out.println("["+result1+"]");

        String result2 = fillerFormatter("XXX", "F", "right", 20);
        System.out.println("["+result2+"]");

        String result3 = fillerFormatter("ZZZ", "8", "left", 20);
        System.out.println("["+result3+"]");

        String result4 = fillerFormatter("ZZZ", "8", "right", 20);
        System.out.println("["+result4+"]");

        String result5 = fillerFormatter("YYY", "A", "left", -20);
        System.out.println("["+result5+"]");

        String result6 = fillerFormatter("YYY", "A", "right", -20);
        System.out.println("["+result6+"]");
    }
</pre>

The result will be something like below

<pre>
[XXXFFFFFFFFFFFFFFFFF]
[FFFFFFFFFFFFFFFFFXXX]
[ZZZ88888888888888888]
[88888888888888888ZZZ]
Error: use size > 0
[null]
Error: use size > 0
[null]
</pre>

# public static String rgFormatter(String value, String rgUf)

This method is only used in the Brazil, because it serves to specific situation in that country, where we need to manager 
or formatter the document number or document type from anyone in the system. So, maybe we need to add any information 
or remove the information from document (RG), in these case we can use this method in the way showed below

<pre>
    public static String rgFormatter(String value, String rgUf) {
        if (value == null || value.equals("")) return "";
        if (rgUf == null) rgUf = "";
        if (!rgUf.contains("SSP") && !rgUf.equals("")) return "";

        //SSP CP = SSPSC, SSP/SP = SSPSP
        rgUf = rgUf.replaceAll("[^A-Z]+", "");

        if (rgUf.equals("SSPSP") || rgUf.equals("SP")) {
            rgUf = "";
        } else {
            //SSPCRJ = RJ, SSPSC = SC
            rgUf = rgUf.replaceAll("SSP", "");
        }

        return "RG"+value.replaceAll("[^0-9]+", "")+rgUf;
    }
</pre>

<pre>
    @Test
    public void rgFormatterTest() {
        System.out.println(" > ["+ rgFormatter("9090909090", "CNH")+"]");
        System.out.println(" > ["+ rgFormatter("7878787878", "SSP SC")+"]");
        System.out.println(" > ["+ rgFormatter("6767676767", "SSPSP")+"]");
        System.out.println(" > ["+ rgFormatter("1212121212", "SSCMG")+"]");
        System.out.println(" > ["+ rgFormatter("2020202020", "SSP/RJ")+"]");
    }
</pre>

The result will be something like the below

<pre>
 > []
 > [RG7878787878SC]
 > [RG6767676767]
 > []
 > [RG2020202020RJ]
</pre>

Look that this method manager the information and makes a few validations to format the documentation identification RG 
in the form correct form for each place in the Brazil, and not accept empty rgUf. 

# Currency

[Help4DevsCurrencyService.java](src/main/java/com/huntercodexs/demojobs/services/Help4DevsCurrencyService.java)

- public static String brCurrency(float value)
- public static String brCurrency(double value)
- public static double currencySum(double current, double add)
- public static double currencySumFromString(String current, String add)

# Database

[Help4DevsDatabaseService.java](src/main/java/com/huntercodexs/demojobs/services/Help4DevsDatabaseService.java)

# Date

[Help4DevsDateService.java](src/main/java/com/huntercodexs/demojobs/services/Help4DevsDateService.java)

- public static String reverseDate(String inputDate, String separator)
- public static boolean expiredDate(String date, int time, String metricType)
- public static List<Long> quantifyDate(String initialDate, String finalDate)
- public static long quantifyMillisDate()
- public static long quantifyMillisParamsDate(String start, String end)
- public static String localDateFromGmtDate(String gmtDate, String operation, int time)

# FileHandler

[Help4DevsFileHandlerService.java](src/main/java/com/huntercodexs/demojobs/services/Help4DevsFileHandlerService.java)

- public static Properties loadProps(String classpath)
- public static InputStream bytesExtractorShipmentFile(String targetPath, String targetFile)
- public static InputStream fileToByteArray(String targetPath, String targetFile)
- public static ByteArrayDataSource fileToDataSource(String targetPath, String targetFile) throws IOException
- public static byte[] byteConvert(InputStream fileArray) throws IOException
- public static String fileToString(String targetPath, String targetFile)
- public static ArrayList<String> fileToArray(String targetPath, String targetFile)

# FileReader

[Help4DevsFileReaderService.java](src/main/java/com/huntercodexs/demojobs/services/Help4DevsFileReaderService.java)

- public static FileReader open(String filepath)
- public static BufferedReader buffer(FileReader activateFile)
- public static String reader(BufferedReader readActivateFile)
- public static void close(FileReader activateFile)
- public static String getFileContent(String filepath, String regex, int timeout)

# Path

[Help4DevsPathService.java](src/main/java/com/huntercodexs/demojobs/services/Help4DevsPathService.java)

- public static String sanitizePath(String path)
- public static String sanitizeAscii(String input)

# StringHandler

[Help4DevsStringHandlerService.java](src/main/java/com/huntercodexs/demojobs/services/Help4DevsStringHandlerService.java)

- public static String queryStringBuilder(Object input)
- public static String getDataFromQueryString(String queryString, String field)
- public static JSONObject queryStringToJson(String input)
- public static JSONObject stringToJson(String str)

# Tools

[Help4DevsToolsService.java](src/main/java/com/huntercodexs/demojobs/services/Help4DevsToolsService.java)

- public static String md5(String data)
- public static String guide(String tcn)

# Validator

[Help4DevsValidatorService.java](src/main/java/com/huntercodexs/demojobs/services/Help4DevsValidatorService.java)

- public static boolean cpfValidator(String cpf)
- public static boolean mailValidator(String email)
- public static boolean phoneValidator(String phoneNumber)
