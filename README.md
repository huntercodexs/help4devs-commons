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

- public static void params(JSONObject... jsonObjects)

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

- public static String numberFormatter(int input, String format)
- public static String stringFormatter(String input, String format)
- public static String rgFormatter(String value, String rgUf)

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
