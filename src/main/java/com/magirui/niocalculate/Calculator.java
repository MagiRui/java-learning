package com.magirui.niocalculate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 〈计算器〉
 *
 * @author magirui
 * @create 2018-06-26 下午4:31
 */
public class Calculator {

    private static Stack<BigDecimal> valueStack = new Stack<BigDecimal>();

    private static String prevOp;

    public static BigDecimal exec(String str){

        if(!isValid(str)){

            throw new RuntimeException("expression error");
        }

        String[] numberArray = str.split("[+|\\-|*|/]");
        List<String> operList = new ArrayList<String>();
        Pattern  p = Pattern.compile("([+|\\-|*|/])");
        Matcher m = p.matcher(str);
        while(m.find()){

            operList.add(m.group());
        }

        String[] operArray = operList.toArray(new String[]{});
        for(int i = 0; i< numberArray.length - 1; i++ ){

            singleStep(numberArray[i], operArray[i], numberArray[i+1]);
        }

        BigDecimal b2 = valueStack.pop();
        BigDecimal b1 = valueStack.pop();
        BigDecimal result = calc(prevOp, b1, b2);
        return result;

    }

    private static boolean isValid(String str){

        return true;
    }

    private static void singleStep(String a, String op, String b){

        if(valueStack.isEmpty()){

            valueStack.push(new BigDecimal(a));
            valueStack.push(new BigDecimal(b));
        }

        if(null == prevOp){

            prevOp = op;
        }else{

            if(isHighPriority(op, prevOp)){

                BigDecimal tmpResult = calc(op, valueStack.pop(), new BigDecimal(b));
                valueStack.push(tmpResult);
            }else{

                BigDecimal b2 = valueStack.pop();
                BigDecimal b1 = valueStack.pop();
                BigDecimal tmpResult = calc(prevOp, b1, b2);
                valueStack.push(tmpResult);
                valueStack.push(new BigDecimal(b));
                prevOp = op;
            }

        }
    }

    private static boolean isHighPriority(String curr, String prev){

        if(("*".equalsIgnoreCase(curr) || "/".equalsIgnoreCase(curr)) &&
                ("+".equalsIgnoreCase(prev) || "-".equalsIgnoreCase(prev)) ){

            return true;
        }

        return false;
    }

    private static BigDecimal calc(String op, BigDecimal b1, BigDecimal b2){

        if("+".equalsIgnoreCase(op)){

            return b1.add(b2);
        }else if("-".equalsIgnoreCase(op)){

            return b1.subtract(b2);
        }else if("*".equalsIgnoreCase(op)){

            return b1.multiply(b2);
        }else if("/".equalsIgnoreCase(op)){

            if(b2.compareTo(BigDecimal.ZERO) == 0){

                return b1;
            }

            return b1.divide(b2, 2, 5);
        }else{

            return BigDecimal.ZERO;
        }

    }

    public static void main(String[] args){

        Calculator c1 = new Calculator();
        System.out.println(c1.exec("4+5*3"));
    }
}
