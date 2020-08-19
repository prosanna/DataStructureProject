/**
 * The sieve of Eratosthenes
 */
package com.datastruct.app;

import java.util.Arrays;
import java.util.BitSet;

/**
 * Given a number n, print all primes smaller than or equal to n. It is also
 * given that n is a small number.
 * 
 * @author prosannam
 *
 */
public class PrimeNumberEratosthenes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 500;
		// SieveOfEratosthenes(n);
		System.out.println(SieveOfEratosthenes1(n));

	}

	/**
	 * <p>
	 * Create a list of consecutive integers from 2 to n: (2, 3, 4, �, n).
	 * </p>
	 * <p>
	 * Initially, let p equal 2, the first prime number.
	 * </p>
	 * <p>
	 * Starting from p2, count up in increments of p and mark each of these numbers
	 * greater than or equal to p2 itself in the list. These numbers will be p(p+1),
	 * p(p+2), p(p+3), etc..
	 * </p>
	 * <p>
	 * Find the first number greater than p in the list that is not marked. If there
	 * was no such number, stop. Otherwise, let p now equal this number (which is
	 * the next prime), and repeat from step 3.
	 * </p>
	 * 
	 * @param n
	 */
	private static void SieveOfEratosthenes(int n) {
		// Create a boolean array.
		Boolean[] primeArray = new Boolean[n + 1];
		// Fill the full array as true.
		Arrays.fill(primeArray, true);

		for (int p = 2; p * p <= n; p++) {

			if (primeArray[p] == true) {

				for (int i = p * p; i <= n; i += p) {
					primeArray[i] = false;
				}
			}

		}
		int count = 0;
		for (int i = 2; i <= n; i++) {
			if (primeArray[i]) {
				System.out.print(i + " ");
				count++;
			}
		}
		System.out.println();
		System.out.println("Total no. of primes: " + count);
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	private static BitSet SieveOfEratosthenes1(int value) {
		BitSet set = new BitSet();
		fillSet(set, value);

		int s = set.stream().min().getAsInt();
		while (s * s <= value) {
			int temp = s;
			int i = 0;
			int multipleTemp;
			while ((multipleTemp = s * (s + i)) <= value) {
				set.clear(multipleTemp);
				i++;
			}
			s = set.stream().filter(x -> x > temp).min().getAsInt();
		}

		return set;
	}

	private static void fillSet(BitSet set, int value) {
		for (int i = 2; i <= value; i++) {
			set.set(i);
		}
	}

}

