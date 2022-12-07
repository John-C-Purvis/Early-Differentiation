import java.math.BigInteger;
import java.util.*;

public class Equation {
    
    // Input the source polynomial and the integer x for which to solve
    public static BigInteger differentiate(String equation, long x) {
        // Check for an empty set and return 0 if empty
        if(equation == "") return new BigInteger("0");
        // Convert the terms of the polynomial into an array of (String) terms
        String[] terms = arrayTerms(equation);
        // Iterate through each term in the array and parse each term then solve for x in each parsed term
        for(int i = 0; i < terms.length; i++) {
          terms[i] = parseTerm(terms[i]);
          terms[i] = resolveX(terms[i], x);
        }
        // Add the results of each term to solve the given equation for x
        return sumArray(terms);
    }
  
    // Convert a given polynomial String to separated terms in an ArrayList
    public static String[] arrayTerms(String polynomial) {
      // Establish a new ArrayList to hold the separated terms
      ArrayList<String> termsArray = new ArrayList<String>();
      // Instantiate a String by which each term will be assigned for parsing
      String workingTerm = "";
      // Iterate through the polynomial character-by-character to build each term in the array
      for(int i = 0; i < polynomial.length(); i++) {
        // If the term begins with a negative operator add this to the term build and move on to the next iteration
        if(i == 0 && polynomial.charAt(0) == '-') {
          workingTerm += "-";
          continue;
        }
        // If the character encountered is not an arithmetic operator add it to the term build and move on to the next iteration
        if(polynomial.charAt(i) != '-' && polynomial.charAt(i) != '+') {
          workingTerm += polynomial.charAt(i);
          continue;
        }
        // Add the term build to the array
        termsArray.add(workingTerm);
        // Set the term build to negative if the current character is "-" or empty if not
        workingTerm = polynomial.charAt(i) == '-' ? "-" : "";
      }
      // Catch an un-added term (final) from the loop and add it to the array
      termsArray.add(workingTerm);
      // Return the resulting array to the parent function
      return termsArray.toArray(new String[0]);
    }
  
    // Parse the provided term into a differentiated term
    public static String parseTerm(String term) {
      // Return 0 if no coefficient is detected
      if(!term.contains("x")) return "0";
      // Store the location of the term's variable
      int xIndex = term.indexOf("x");
      // Initialize the coefficient as 1 (equal to no coefficient)
      int coefficient = 1;
      // Check if the entire term is -x and set the coefficient as -1 (equal to subtracting the term with no coefficient)
      if(term.equals("-x")) coefficient = -1;
      // If the entire term is only the positive variable set the coefficient as 1 (equal to no coefficient)
      else if(term.equals("x")) coefficient = 1;
      // If the entire term is only the negative variable set the coefficient as -1 (equal to subtracting the term with no coefficient)
      else if(term.substring(0, 2).equals("-x")) coefficient = -1;
      // If the variable is not the first character of the term set the coefficient to the value of the characters preceding the variable
      else if(xIndex != 0) coefficient = Integer.parseInt(term.substring(0, xIndex));
      // If there is no exponent in the term return the value of the coefficient
      if(!term.contains("^")) return String.valueOf(coefficient);
      // Store the exponent for further parsing
      int exponent = Integer.parseInt(term.substring(xIndex + 2));
      // If the exponent is 2 return the differentiated result (excluding a new exponent value)
      if(exponent < 3) return (coefficient * exponent) + "x";
      // Return the differentiated result (and adjust the exponent value)
      return (coefficient * exponent) + "x^" + (exponent - 1);
    }
  
    // Solve for x in a given term
    public static String resolveX(String term, long x) {
      // Instantiate a variable that will contain the result and initialize the value as 0
      BigInteger result = new BigInteger("0");
      // If the term does not contain a variable return the term as-is
      if(!term.contains("x")) return term;
      // Store the location of the term's variable
      int xIndex = term.indexOf("x");
      // Initialize the coefficient as 1 (equal to no coefficient)
      int coefficient = 1;
      // If the entire term is only the variable set the coefficient as 1 (equal to no coefficient)
      if(term.equals("x")) coefficient = 1;
      // If the term is negatice with no coefficient set the coefficient to -1 (equal to subtracting the term with no coefficient)
      else if(term.substring(0, 2).equals("-x")) coefficient = -1;
      // If there is a (non-1) coefficient store that as the value of the coefficient store
      else if(xIndex != 0) coefficient = Integer.parseInt(term.substring(0, xIndex));
      // If there is no exponent return the value of the variable multiplied by the coefficient
      if(!term.contains("^")) return String.valueOf(coefficient * x);
      // Initialize a variable to store the value of the exponent
      int exponent = 0;
      // Store the value of the exponent
      if(xIndex != term.length() - 1) exponent = Integer.parseInt(term.substring(xIndex + 2));
      // Instantiate a variable to store the value to multiply the term by and initialize the value to 0
      BigInteger toMultiply = new BigInteger("0");
      // Convert the term's variable to a compatible type
      BigInteger varX = new BigInteger(String.valueOf(x));
      // Set the multiplicative value to the coefficient
      toMultiply = BigInteger.valueOf(coefficient);
      // Update the multiplicative value to the product of itself and the term's variable (raised to the exponent - maintaining order of operations)
      toMultiply = toMultiply.multiply(varX.pow(exponent));
      // Return the multiplicative value as a string
      return String.valueOf(toMultiply);
    }
  
    // Sum all of the terms in a given array
    public static BigInteger sumArray(String[] terms) {
      // Instantiate a variable that will contain the totals and initialize the value as 0
      BigInteger sum = new BigInteger("0");
      // Iterate through each of the given terms
      for(int i = 0; i < terms.length; i++) {
        // Convert the term to a compatible variable type
        BigInteger toAdd = new BigInteger(terms[i]);
        // Add the term to the running total
        sum = sum.add(toAdd);
      }
      // Return the total to the parent function
      return sum;
    }
}
