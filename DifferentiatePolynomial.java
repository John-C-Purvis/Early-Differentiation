import java.math.BigInteger;
import java.util.*;

public class Equation {
    
    public static BigInteger differentiate(String equation, long x) {
        if(equation == "") return new BigInteger("0");
        String[] terms = arrayTerms(equation);
        for(int i = 0; i < terms.length; i++) {
          terms[i] = parseTerm(terms[i]);
          terms[i] = resolveX(terms[i], x);
        }
        return sumArray(terms);
    }
  
    public static String[] arrayTerms(String polynomial) {
      ArrayList<String> termsArray = new ArrayList<String>();
      String workingTerm = "";
      for(int i = 0; i < polynomial.length(); i++) {
        if(i == 0 && polynomial.charAt(0) == '-') {
          workingTerm += "-";
          continue;
        }
        if(polynomial.charAt(i) != '-' && polynomial.charAt(i) != '+') {
          workingTerm += polynomial.charAt(i);
          continue;
        }
        termsArray.add(workingTerm);
        workingTerm = polynomial.charAt(i) == '-' ? "-" : "";
      }
      termsArray.add(workingTerm);
      return termsArray.toArray(new String[0]);
    }
  
    public static String parseTerm(String term) {
      if(!term.contains("x")) return "0";
      int xIndex = term.indexOf("x");
      int coefficient = 1;
      if(term.equals("-x")) coefficient = -1;
      else if(term.equals("x")) coefficient = 1;
      else if(term.substring(0, 2).equals("-x")) coefficient = -1;
      else if(xIndex != 0) coefficient = Integer.parseInt(term.substring(0, xIndex));
      if(!term.contains("^")) return String.valueOf(coefficient);
      int exponent = Integer.parseInt(term.substring(xIndex + 2));
      if(exponent < 3) return (coefficient * exponent) + "x";
      return (coefficient * exponent) + "x^" + (exponent - 1);
    }
  
    public static String resolveX(String term, long x) {
      BigInteger result = new BigInteger("0");
      if(!term.contains("x")) return term;
      int xIndex = term.indexOf("x");
      int coefficient = 1;
      if(term.equals("x")) coefficient = 1;
      else if(term.substring(0, 2).equals("-x")) coefficient = -1;
      else if(xIndex != 0) coefficient = Integer.parseInt(term.substring(0, xIndex));
      if(!term.contains("^")) return String.valueOf(coefficient * x);
      int exponent = 0;
      if(xIndex != term.length() - 1) exponent = Integer.parseInt(term.substring(xIndex + 2));
      BigInteger toMultiply = new BigInteger("0");
      BigInteger varX = new BigInteger(String.valueOf(x));
      toMultiply = BigInteger.valueOf(coefficient);
      toMultiply = toMultiply.multiply(varX.pow(exponent));
      return String.valueOf(toMultiply);
    }
  
    public static BigInteger sumArray(String[] terms) {
      BigInteger sum = new BigInteger("0");
      for(int i = 0; i < terms.length; i++) {
        BigInteger toAdd = new BigInteger(terms[i]);
        sum = sum.add(toAdd);
      }
      return sum;
    }
}
