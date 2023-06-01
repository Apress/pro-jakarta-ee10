package book.jakartapro.textutils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class TextUtils {
  public static class Pair<S,T> {
    public S s;
    public T t;
    public Pair(S s, T t) {
      this.s = s;
      this.t = t;
    }
    public S first() { return s;}
    public T second() { return t; }
    @Override public String toString() { 
        return "(" + s + "," + t + ")"; };
  }
	
  public static List<Pair<String,Integer>> 
        wordFrequenciesSorted(String text) {
    Multiset<String> mus = Splitter.
       onPattern("[^\\p{L}\\p{Nd}]+").
       trimResults(CharMatcher.anyOf(
                   ".,;:-'\"!?=()[]{}*/")).
       omitEmptyStrings().
       splitToList(text).stream().collect(
           () -> { return HashMultiset.create(); }, 
	   (ms,str) -> { ms.add(str.toLowerCase()); }, 
           (ms1,ms2) -> { ms1.addAll(ms2); });
    return mus.entrySet().stream().
      sorted(Comparator.comparing((e) -> {
          return String.format("%09d-%s", 
                10000000-e.getCount(), 
                e.getElement()); 
      })).
      map((Multiset.Entry<String>e) -> {
        return new Pair<String,Integer>(e.getElement(),
                                    e.getCount()); }).
      collect(Collectors.toList());
  }
}
