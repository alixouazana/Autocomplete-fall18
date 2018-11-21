
/*************************************************************************
 * @author Kevin Wayne
 *
 * Description: A term and its weight.
 * 
 *************************************************************************/

import java.util.Comparator;

public class Term implements Comparable<Term> {

	private final String myWord;
	private final double myWeight;

	/**
	 * The constructor for the Term class. Should set the values of word and
	 * weight to the inputs, and throw the exceptions listed below
	 * 
	 * @param word
	 *            The word this term consists of
	 * @param weight
	 *            The weight of this word in the Autocomplete algorithm
	 * @throws NullPointerException
	 *             if word is null
	 * @throws IllegalArgumentException
	 *             if weight is negative
	 */
	public Term(String word, double weight) {
		
		myWord = word;
		myWeight = weight;
		if (weight<0) {
			throw new IllegalArgumentException("negative weight "+ weight);
		}
		if (word == null) {
			throw new NullPointerException("null word "+ word);
		}
		
	}
	
	/**
	 * The default sorting of Terms is lexicographical ordering.
	 */
	public int compareTo(Term that) {
		return myWord.compareTo(that.myWord);
	}

	/**
	 * Getter methods, use these in other classes which use Term
	 */
	public String getWord() {
		return myWord;
	}

	public double getWeight() {
		return myWeight;
	}

	public String toString() {
		return String.format("(%2.1f,%s)", myWeight, myWord);
	}
	
	@Override
	public boolean equals(Object o) {
		Term other = (Term) o;
		return this.compareTo(other) == 0;
	}

	/**
	 * A Comparator for comparing Terms using a set number of the letters they
	 * start with. This Comparator may be useful in writing your implementations
	 * of Autocompletors.
	 *
	 */
	public static class PrefixOrder implements Comparator<Term> {
		private final int myPrefixSize;

		public PrefixOrder(int r) {
			this.myPrefixSize = r;
		}

		/**
		 * Compares v and w lexicographically using only their first r letters.
		 * If the first r letters are the same, then v and w should be
		 * considered equal. This method should take O(r) to run, and be
		 * independent of the length of v and w's length. You can access the
		 * Strings to compare using v.word and w.word.
		 * 
		 * @param v/w
		 *            - Two Terms whose words are being compared
		 */
		public int compare(Term v, Term w) {
			// TODO: Implement compare
			String vword = "";
			String wword = "";
			
			if(v.getWord().length()<myPrefixSize) {
				vword = v.getWord();
			}
			else vword = v.getWord().substring(0, myPrefixSize);
			 
			if(w.getWord().length()<myPrefixSize) {
				wword = w.getWord();
			}
			else wword = w.getWord().substring(0, myPrefixSize);
			
			
			for(int k=0; k<vword.length() && k<wword.length(); k++) {
				char vchar = vword.charAt(k);
				char  wchar = wword.charAt(k);
				if (vchar == wchar) continue;
				if(vchar<wchar) return vchar - wchar;
				if(wchar<vchar) return vchar - wchar;
			}
			if(vword.length()!=wword.length()) {
				return vword.length()-wword.length();
			}
			return 0;
		}
	
	}

	/**
	 * A Comparator for comparing Terms using only their weights, in descending
	 * order. This Comparator may be useful in writing your implementations of
	 * Autocompletor
	 *
	 */
	public static class ReverseWeightOrder implements Comparator<Term> {
		public int compare(Term v, Term w) {
			Double vweight = v.getWeight();
			Double wweight = w.getWeight();
			int reversecomp = wweight.compareTo(vweight);
			if(reversecomp!= 0) return reversecomp;
			return 0;
			
		}
	}

	/**
	 * A Comparator for comparing Terms using only their weights, in ascending
	 * order. This Comparator may be useful in writing your implementations of
	 * Autocompletor
	 *
	 */
	public static class WeightOrder implements Comparator<Term> {
		public int compare(Term v, Term w) {
			Double vweight = v.getWeight();
			Double wweight = w.getWeight();
			int normalcomp = vweight.compareTo(wweight);
			if(normalcomp!= 0) return normalcomp;
			return 0;
		}
	}
}
