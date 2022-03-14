package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result, result2;
    int num1, num2;
    String pi = String.valueOf(3.141592654);
    char c = '0';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.Output);
        result2 = (TextView) findViewById(R.id.Output2);
    }

    public void Del(View view) {
        String s = result.getText().toString();
        if (s.length() != 0) {
            s = s.substring(0, s.length() - 1);
            result.setText(s);
        }

    }

    public void AC(View view) {
        result.setText("");
        result2.setText("");
    }

    public void numzero(View view) {
        String s = result.getText().toString();
        if (s.length() != 0) {
            result.setText(s + "0");
        }
    }

    public void numdot(View view) {
        result.setText(result.getText() + ".");
    }

    public void numone(View view) {
        result.setText(result.getText() + "1");
    }

    public void numtwo(View view) {
        result.setText(result.getText() + "2");
    }

    public void numthree(View view) {
        result.setText(result.getText() + "3");
    }

    public void numfour(View view) {
        result.setText(result.getText() + "4");
    }

    public void numfive(View view) {
        result.setText(result.getText() + "5");
    }

    public void numsix(View view) {
        result.setText(result.getText() + "6");
    }

    public void numseven(View view) {
        result.setText(result.getText() + "7");
    }

    public void numeight(View view) {
        result.setText(result.getText() + "8");
    }

    public void numnine(View view) {
        result.setText(result.getText() + "9");
    }

    public void numplus(View view) {
        result.setText(result.getText() + "+");
    }

    public void numdiv(View view) {
        result.setText(result.getText() + "/");
    }

    public void numminus(View view) {
        result.setText(result.getText() + "-");
    }

    public void nummulti(View view) {
        result.setText(result.getText() + "*");
    }

    public void fb1(View view) {
        result.setText(result.getText() + "(");
    }

    public void fb2(View view) {
        result.setText(result.getText() + ")");
    }
    public void ncr1(View view){
        num1=Integer.parseInt(result.getText().toString());
        result2.setText(result.getText());
        result.setText("");
        c='1';

    }
    public void nprf(View view){
        num1=Integer.parseInt(result.getText().toString());
        result2.setText(result.getText());
        result.setText("");
        c='2';

    }

    public void pi1(View view) {
        result2.setText(result.getText());
        result.setText(result.getText() + pi);
    }

    public void squr(View view) {
        String s = result.getText().toString();
        Double val = Math.sqrt(Double.parseDouble(s));
        result.setText(String.valueOf(val));
    }

    public void sinf(View view) {
        result.setText(result.getText() + "sin(");
    }

    public void cosf(View view) {
        result.setText(result.getText() + "cos(");
    }

    public void tanf(View view) {
        result.setText(result.getText() + "tan(");
    }

    public void facto(View view) {
        int s = Integer.parseInt(result.getText().toString());
        int fact = factorial(s);
        result.setText(String.valueOf(fact));
        result2.setText(s + "!");
    }
    public void lnf(View view)
    {
        result.setText(result.getText()+"ln(");
    }
    public void logf(View view)
    {
        result.setText(result.getText()+"log(");
    }
    public void power(View view)
    {
        Double s = Double.parseDouble(result.getText().toString());
        Double Squares = (s*s);
        result.setText(String.valueOf(Squares));
        result2.setText(s + "²");
    }
    public void percent(View view)
    {
        Double s = Double.parseDouble(result.getText().toString());
        Double p = (s/100);
        result.setText(String.valueOf(p));
        result2.setText(s + "%");
    }
    int ncrs(int n, int r)
    {
        return factorial(n)/(factorial(r)*factorial(n-r));
    }
    int nprs(int n, int r)
    {
        return factorial(n)/(factorial(n-r));
    }
    int factorial(int n)
    {
        return (n==1||n==0)?1 : n*factorial(n-1);
    }
    public void expression(View view)
    {
        result.setText(result.getText() + "e");
    }
    public void numequal(View view)
    {
        if(c=='0') {
            String values = result.getText().toString();
            Double fresult = eval(values);
            result.setText(String.valueOf(fresult));
            result2.setText(values);
        }
        if(c=='1')
        {
            num2=Integer.parseInt(result.getText().toString());
            int fout=ncrs(num1,num2);
            result.setText(String.valueOf(fout));
            c='0';

        }
        if(c=='2')
        {
            num2=Integer.parseInt(result.getText().toString());
            int fout=nprs(num1,num2);
            result.setText(String.valueOf(fout));
            c='0';

        }

    }
    public static double eval(final String str)
    {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }



            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else if (func.equals("log")) x = Math.log10(x);
                    else if (func.equals("ln")) x = Math.log(x);
                    else if(func.equals("e")) x=Math.exp(x);
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());

                return x;
            }
        }.parse();
    }

}
