package com.example.calculator;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Result {
    static boolean divisionByZero = false;
    @SuppressLint("SuspiciousIndentation")
    public static double evaluateExpression(String expression)  {
        // الخطوة 1: تحليل السلسلة إلى أرقام ومشغلين باستخدام StringTokenizer
        StringTokenizer tokenizer = new StringTokenizer(expression, "+-×÷%", true);
        ArrayList<Double> numbers = new ArrayList<>();
        ArrayList<Character> operations = new ArrayList<>();

        // تجميع الأرقام والمشغلين في قوائم
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (isOperator(token)) {
                operations.add(token.charAt(0));
            } else {
                numbers.add(Double.parseDouble(token));
            }
        }
        // الخطوة 2: تنفيذ عمليات الضرب والقسمة أولاً
        for (int i = 0; i < operations.size(); i++) {
            char operation = operations.get(i);
            if (operation == '×' || operation == '÷' || operation == '%') {
                double left = numbers.get(i);
                double right = numbers.get(i + 1);
                double result = 0;

                if (operation == '%') {
                    if (right == 0) {
                        divisionByZero = true;
                        return Double.NaN; // أو يمكنك إرجاع قيمة خاصة أخرى
                    }
                    result = left % right;
                } else if (operation == '÷') {
                    if (right == 0) {
                        divisionByZero = true;
                        return Double.NaN; // أو يمكنك إرجاع قيمة خاصة أخرى
                    } else {
                        result = left / right;
                    }
                } else if (operation == '×') {
                    result = left * right;
                }

                // تحديث القيم في القائمة
                numbers.set(i, result);
                numbers.remove(i + 1);
                operations.remove(i);

                i--; // التراجع خطوة لتكرار التحقق من نفس الفهرس
            }
        }


        // الخطوة 3: تنفيذ عمليات الجمع والطرح
        double result = numbers.get(0);
        for (int i = 0; i < operations.size(); i++) {
            char operation = operations.get(i);
            double currentNumber = numbers.get(i + 1);
            if (operation == '+') {
                result += currentNumber;
            } else if (operation == '-') {
                result -= currentNumber;
            }
        }

        return result;
    }


    // دالة للتحقق إذا كان الرمز هو مشغل رياضي
    public static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("×") || token.equals("÷") || token.equals("%");
    }
    public static boolean isValidExpression(String expression) {
        return !expression.contains("%%") && !expression.contains("××") && !expression.contains("÷÷") && !expression.contains("++") && !expression.contains("--");
    }

}

