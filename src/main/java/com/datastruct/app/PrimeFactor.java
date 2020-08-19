/**
 * 
 */
package com.datastruct.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author prosannam
 *
 */
public class PrimeFactor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] n = { 70, 90 };

		List<Integer> l1 = calculatePrimeFactors(n[0]);
		List<Integer> l2 = calculatePrimeFactors(n[1]);

		System.out.println(l1 + " " + l2);

		calculateLCM(l1, l2);
		calculateHCF(70, 90);

	}

	/**
	 * Calculate HCF
	 * @param i
	 * @param j
	 */
	private static void calculateHCF(int i, int j) {
		List<Integer> l1 = calculatePrimeFactors(i);
		List<Integer> l2 = calculatePrimeFactors(j);
		
		
		Map<Object, Long> m1 = l1.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		Map<Object, Long> m2 = l2.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		
				
		System.out.println(m1+" "+m2);
		
		Map<Object, Long> finalMap = new HashMap<Object, Long>();

		for (Map.Entry<Object, Long> entry : m1.entrySet()) {
		
			for (Map.Entry<Object, Long> entry2 : m2.entrySet()) {
				if (entry.getKey()== entry2.getKey()) {
					if(entry.getValue()<=entry2.getValue()) {
						finalMap.put(entry.getKey(), entry.getValue());
					} else {
						finalMap.put(entry2.getKey(), entry2.getValue());
					}
				}
				
			}
			 

		}
		
		
		System.out.println(finalMap);
		
		long HCF = 1;
		for (Map.Entry<Object, Long> entry : finalMap.entrySet()) {

			System.out.println(entry.getKey() + " " + entry.getValue());
			for (int i1 = 0; i1 < entry.getValue(); i1++) {
				HCF *= Long.valueOf(entry.getKey().toString());
			}
		}

		System.out.println(HCF);
		
	}

	/**
	 * Calculate LCM
	 * 
	 * @param l1
	 * @param l2
	 */

	private static void calculateLCM(List<Integer> l1, List<Integer> l2) {

		List<bean> commonFactor = new ArrayList<bean>();

		Map<Object, Long> m1 = l1.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		Map<Object, Long> m2 = l2.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

		for (Map.Entry<Object, Long> entry : m1.entrySet()) {

			bean bean = new bean(entry.getKey(), entry.getValue());
			commonFactor.add(bean);
		}

		for (Map.Entry<Object, Long> entry : m2.entrySet()) {

			bean bean = new bean(entry.getKey(), entry.getValue());
			commonFactor.add(bean);
		}

		System.out.println(commonFactor);

		Map<Object, Long> finalMap = new HashMap<Object, Long>();

		for (bean bean : commonFactor) {

			if (finalMap.containsKey(bean.getKey())) {

				if (finalMap.get(bean.getKey()) < bean.getValue()) {
					finalMap.put(bean.getKey(), bean.getValue());
				}

			} else {
				finalMap.put(bean.getKey(), bean.getValue());
			}

		}
		long LCM = 1;
		for (Map.Entry<Object, Long> entry : finalMap.entrySet()) {

			System.out.println(entry.getKey() + " " + entry.getValue());
			for (int i = 0; i < entry.getValue(); i++) {
				LCM *= Long.valueOf(entry.getKey().toString());
			}
		}

		System.out.println(LCM);

	}

	/**
	 * Calculate Prime Factors
	 * 
	 * @param n
	 */
	private static List<Integer> calculatePrimeFactors(int n) {

		List<Integer> primeFactors = new ArrayList<>();

		while (n % 2 == 0) {
			primeFactors.add(2);
			n = n / 2;

		}

		
		for (int i = 3; i <= Math.sqrt(new Double(n)); i += 2) {
			while (n % i == 0) {
				primeFactors.add(i);
				n = n / i;
			}

		}
		if (n > 2) {
			primeFactors.add(n);
		}
	
		return primeFactors;

	}

}

/**
 * Bean to save the Prime Factors as key and it's occurrence as value
 *
 */
class bean {
	Object key;
	long value;

	public bean(Object key, long value) {
		this.key = key;
		this.value = value;
	}

	public Object getKey() {
		return key;
	}

	public long getValue() {
		return value;
	}
}
