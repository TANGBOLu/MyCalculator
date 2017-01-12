package com.example.zhuyuping.myjisuanqi;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Scanner;
import java.util.Stack;



public class Calculator {


    /**
     * 逆波兰式
     */
    public static Double toReversePolishNotation(String input)
            throws Exception {
        int singleFlag=0;

        int len = input.length();
        char c, tempChar;
        Stack<Character> s1 = new Stack<Character>();
        Stack<Double> s2 = new Stack<Double>();
        double number;
        int lastIndex = -1;
        for (int i = 0; i < len; ++i) {
            c = input.charAt(i);
            if ((Character.isDigit(c) || c == '.')) {
                lastIndex = readDouble(input, i);
                number = Double.parseDouble(input.substring(i, lastIndex));
                s2.push(number);
                i = lastIndex - 1;

            } else {
                if (isOperator(c)||isSingleOperator(c)) {
                    while (!s1.isEmpty()
                            && s1.peek() != '('
                            && priorityCompare(c, s1.peek()) <= 0) {

                        if(isSingleOperator(c)){
                            double num1=s2.pop();
                            s2.push(singlecalc(num1,s1.pop()));
                        }
                        else{
                            double num1 = s2.pop();
                            double num2 = s2.pop();
                            s2.push(calc(num2, num1, s1.pop()));
                        }
                    }
                    if(s2.isEmpty()&&isOperator(c)) {
                        s2.push(0.0);
                    }
                    s1.push(c);
                } else if (c == '(') {
                    s1.push(c);
                } else if (c == ')') {
                    while ((tempChar = s1.pop()) != '(') {
                        double num1 = s2.pop();
                        ////单运算符处理
                        if(isSingleOperator(tempChar)){
                            s2.push(singlecalc(num1,tempChar));
                        }
                        ////
                        double num2 = s2.pop();
                        s2.push(calc(num2, num1, tempChar));
                        if (s1.isEmpty()) {
                            throw new Exception(
                                    "表达式有误可能没有'('");
                        }
                    }
                }
            }
        }

            while (!s1.isEmpty()) {
                tempChar = s1.pop();
                double num1 = s2.pop();
                if(isSingleOperator(tempChar)){
                    s2.push(singlecalc(num1,tempChar));
                }else{
                    double num2=s2.pop();
                    s2.push(calc(num2, num1, tempChar));
                }
            }

            double result = s2.pop();

            if (!s2.isEmpty())
                throw new Exception("表达式格式不对");

            return result;
        }

    /**
     *单目运算
     */
    private static Double singlecalc(double num1, char op)
            throws Exception {
        switch (op) {
            case 't':
                return Math.tan(num1);
            case 's':
                return Math.sin(num1);
            case 'c':
                return Math.cos(num1);
            default:
                return 0.0; //错误
        }

    }


    /**
     * 普通四则运算
     */
    private static double calc(double num1, double num2, char op)
            throws Exception {

        BigDecimal b1=new BigDecimal(num1);

        BigDecimal b2=new BigDecimal(num2);

        switch (op) {
            case '+':
                return b1.add(b2).doubleValue();
            case '-':
                return b1.subtract(b2).doubleValue();
            case '*':
                return b1.multiply(b2).doubleValue();
            case '/':
                if (num2 == 0) throw new Exception("除数为0");
                return num1/num2;
            case '^':
                    return b1.pow(((int)num2)).doubleValue()*Math.pow(num1,num2-(int)num2);
            default:
                return 0; // will never catch up here
        }
    }

    /**
     * 优先级判定
     */
    private static int priorityCompare(char op1, char op2) {
        switch (op1) {
            case '+': case '-':
                return (op2 == '*' || op2 == '/'||op2=='^'||op2=='t'||op2=='s'||op2=='c' ? -1 : 0);
            case '*': case '/':case '^':
                return (op2 == '+' || op2 == '-' ? 1 : 0);
        }
        return 1;
    }

    /**
     * 读取表达式值
     */
    private static int readDouble(String input, int start)
            throws Exception {
        int len = input.length();
        int dotIndex = -1;
        char c;
        for (int i=start; i<len; ++i) {
            c = input.charAt(i);
            if (c == '.') {
                if (dotIndex != -1)
                    throw new Exception(
                            "表达式格式不对");
                else if (i == len - 1)
                    throw new Exception(
                            "表达式格式不对");
                else
                    dotIndex = i;
            } else if (!Character.isDigit(c)) {
                if (dotIndex == -1 || i - dotIndex > 1)
                    return i;
                else
                    throw new Exception(
                            "表达式格式不对");
            } else if (i == len - 1) {
                return len;
            }
        }

        throw new Exception("表达式格式不对");
    }

    /**
     * 判断是否是运算符
     */
    private static boolean isOperator(char c) {

        return (c=='+' || c=='-' || c=='*' || c=='/'||c=='&'||c=='^');
    }

    /**
     *判断是否是单目运算
     */
    private static boolean isSingleOperator(char c) {
        return (c=='t'||c=='s'||c=='c');
    }


}
