# Early-Differentiation

This Java class solves the differentiation of a polynomial given in string format.  It accounts for any number of terms in the given polynomial string and outputs the solution solved for given integer x.

The input format is String (polynomial) and integer (x).

The format for the polynomial is standardized as a string representation of the terms where each term is separated by an operator and an operator will precede the first term only if it is negative.  Coefficients and exponents are only included if their absolute value is greater than 1.  A sample follows:

6x^4-2x^3+5x-10

In this example it is assumed that there is no term for x^2 (coefficient is 0).  The cubed term and the offset are both negative.  The first term is positive as it does not begin with "-".
