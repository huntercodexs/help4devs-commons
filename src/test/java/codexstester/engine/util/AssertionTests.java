package codexstester.engine.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static codexstester.engine.util.CodexsHelperTests.codexsHelperLogTerm;
import static codexstester.engine.util.CodexsHelperTests.codexsHelperLogTermTests;

public abstract class AssertionTests extends AdvancedTests {

    private static String methodName(StackTraceElement caller, StackTraceElement element) {
        String methodName = "! Unknown Method";
        if (caller != null && !caller.getMethodName().isEmpty()) {
            methodName = caller.getMethodName();
        }
        return methodName +" > "+element.getMethodName();
    }

    protected static void resulted(boolean flag, StackTraceElement caller) {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        codexsHelperLogTermTests("[RESULT]", "CODEXS TESTER", true);
        if (flag)  codexsHelperLogTermTests("[PASSED]", methodName(caller, element), false);
        if (!flag) codexsHelperLogTermTests("[FAILED]", methodName(caller, element), false);
    }

    protected void codexsTesterAssertExact(String ref, String text) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (text.equals(ref)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+ref, "<< RECEIVED: " + text, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertObject(Object obj1, Object obj2) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (obj1 == obj2) {
            Assert.assertEquals(obj1, obj2);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+obj1, "<< RECEIVED: " + obj2, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertText(String textPart, String text) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (text.contains(textPart)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+textPart, "<< RECEIVED: " + text, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertRegExp(String regExp, String text) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (text.matches(regExp)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+regExp, "<< RECEIVED: " + text, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertInt(int num1, int num2) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (num1 == num2) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+num1, "<< RECEIVED: " + num2, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertBool(boolean val, boolean expected) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (expected == val) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+expected, "<< RECEIVED: " + val, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertNotNull(Object obj) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        try {
            Assert.assertNotNull(obj);
            resulted(true, element);
        } catch (RuntimeException re) {
            codexsHelperLogTerm(">> EXPECTED: NotNull", "<< RECEIVED: Null", true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertNull(Object obj) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        try {
            Assert.assertNull(obj);
            resulted(true, element);
        } catch (RuntimeException re) {
            codexsHelperLogTerm(">> EXPECTED: Null", "<< RECEIVED: NotNull", true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertNumber(String number) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (StringUtils.isNumeric(number)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(">> EXPECTED: Numeric", "<< RECEIVED: " + number, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertCpf(String cpf) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];


        if (cpf.length() > 11) {
            resulted(false, element);
            Assert.fail();
        }

        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        try {
            Long.parseLong(cpf);
        } catch(NumberFormatException e) {
            resulted(false, element);
            Assert.fail();
        }

        int d1, d2;
        int digit1, digit2, rest;
        int cpfDigit;
        String nDigResult;

        d1 = d2 = 0;
        digit1 = digit2 = rest = 0;

        for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
            cpfDigit = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();
            d1 = d1 + (11 - nCount) * cpfDigit;
            d2 = d2 + (12 - nCount) * cpfDigit;
        };

        rest = (d1 % 11);

        if (rest < 2)
            digit1 = 0;
        else
            digit1 = 11 - rest;

        d2 += 2 * digit1;

        rest = (d2 % 11);

        if (rest < 2)
            digit2 = 0;
        else
            digit2 = 11 - rest;

        String digitVerify = cpf.substring(cpf.length() - 2, cpf.length());

        nDigResult = String.valueOf(digit1) + String.valueOf(digit2);

        resulted(true, element);
        Assert.assertEquals(digitVerify, nDigResult);
    }

    protected void codexsTesterAssertEmail(String email) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        boolean isValidMail = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isValidMail = true;
            }
        }
        resulted(isValidMail, element);
        Assert.assertTrue(isValidMail);
    }

    protected void codexsTesterAssertPhone(String phoneNumber) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        boolean isValidPhone = false;
        if (phoneNumber != null && phoneNumber.length() > 0) {
            String expression = "^[+]?[0-9]{13}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(phoneNumber);
            if (matcher.matches()) {
                isValidPhone = true;
            }
        }
        resulted(isValidPhone, element);
        Assert.assertTrue(isValidPhone);
    }

    protected void codexsTesterAssertSum(int a, int b, int c) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (a + b == c) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(">> EXPECTED: " + (a+b), "<< RECEIVED: " + c, true);
            resulted(false, element);
            Assert.fail();
        }
    }

}
