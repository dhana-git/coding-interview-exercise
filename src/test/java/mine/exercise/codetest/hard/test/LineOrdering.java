package mine.exercise.codetest.hard.test;

import java.util.*;
import java.util.stream.Collectors;

public class LineOrdering {

    private static Map<String, Pair> getLinkedPairs(Pair pairParam, List<Pair> pairs) {
        Map<String, Pair> linkedPairs = new LinkedHashMap<>();
        for (Pair pair : pairs) {
            if (!pair.equals(pairParam)) {
                if (pairParam.getFront().equals(pair.getFront()) || pairParam.getFront().equals(pair.getBack())) {
                    linkedPairs.put(pairParam.getFront(), pair);
                }
            }
        }

        for (Pair pair : pairs) {
            if (!pair.equals(pairParam)) {
                if (pairParam.getBack().equals(pair.getFront()) || pairParam.getBack().equals(pair.getBack())) {
                    linkedPairs.put(pairParam.getBack(), pair);
                }
            }
        }

        return linkedPairs;

    }

    private static String visit(Pair originPair, Pair visitingPair, List<Pair> visitedPairs,
                                Set<String> permutationChars) {

        if (originPair != null && (visitingPair == null || !originPair.equals(visitingPair))) {
            if (visitingPair == null) {
                visitingPair = originPair;
            }
            boolean matchFoundForFront = false;
            boolean matchFoundForBack = false;
            if (visitingPair.getLinkedPairs() == null || visitingPair.getLinkedPairs().size() == 0) {
                permutationChars.add(visitingPair.getFront());
                permutationChars.add(visitingPair.getBack());
            }
            for (String leafName : visitingPair.getLinkedPairs().keySet()) {
                Pair pair = visitingPair.getLinkedPairs().get(leafName);
                if (!visitingPair.equals(pair) && !visitedPairs.contains(pair)) {
                    if (visitingPair.getFront().equals(pair.getBack())
                            || visitingPair.getFront().equals(pair.getFront())) {
                        visitedPairs.add(visitingPair);
                        visit(visitingPair, pair, visitedPairs, permutationChars);
                        permutationChars.add(visitingPair.getFront());
                        matchFoundForFront = true;
                    } else {
                        permutationChars.add(visitingPair.getFront());
                    }
                    if (visitingPair.getBack().equals(pair.getBack())
                            || visitingPair.getBack().equals(pair.getFront())) {
                        visitedPairs.add(visitingPair);
                        visit(visitingPair, pair, visitedPairs, permutationChars);
                        permutationChars.add(visitingPair.getBack());
                        matchFoundForBack = true;
                    } else {
                        permutationChars.add(visitingPair.getBack());
                    }
                }
            }

            if (!matchFoundForFront) {
                permutationChars.add(visitingPair.getFront());
            }
            if (!matchFoundForBack) {
                permutationChars.add(visitingPair.getBack());
            }

        }

        return null;
    }

    public static int lineOrdering(String[] strArr) {
        List<Pair> pairs = Arrays.stream(strArr).map(item -> new Pair(item))
                .collect(Collectors.toCollection(LinkedList::new));
        Set<String> uniquePeople = new HashSet<>();
        pairs.stream().forEach(pair -> {
            uniquePeople.add(pair.getFront());
            uniquePeople.add(pair.getBack());
        });

        for (Pair pair : pairs) {
            pair.setLinkedPairs(getLinkedPairs(pair, pairs));

        }
        System.out.println(pairs);
        Set<String> combinations = new LinkedHashSet<>();
        for (Pair pair : pairs) {
            Set<String> permutationChars = new LinkedHashSet<>();
            visit(pair, null, new ArrayList<Pair>(), permutationChars);
            combinations.add(permutationChars.stream().reduce((prev, current) -> prev + current).get());
        }
        System.out.println(combinations);
        return combinations.size();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        // System.out.print(lineOrdering(s.nextLine()));
        // System.out.print(lineOrdering(new String[] { "J>B", "B<S", "D>J" }));
//		 System.out.print(lineOrdering(new String[] { "A>C"}));
//		 System.out.print(lineOrdering(new String[] { "A<B","B>A"}));
//		 System.out.print(lineOrdering(new String[] {"A>B","C>B","A<Q"}));
//		 System.out.print(lineOrdering(new String[]	 {"A>B","B>C","C>D","D>E","G>E"}));
        System.out.print(lineOrdering(new String[]{"A>B", "B>C", "C>D", "G>D"}));

    }

    public static class Pair {
        String front;
        String back;
        Map<String, Pair> linkedPairs;
        Pair(String peoplePair) {
            String[] peopleArray = peoplePair.split("<|>");
            int frontIndex = 0;
            int backIndex = 1;
            if (peoplePair.contains("<")) {
                frontIndex = 1;
                backIndex = 0;
            }
            this.front = peopleArray[frontIndex];
            this.back = peopleArray[backIndex];
        }

        public String getFront() {
            return front;
        }

        public String getBack() {
            return back;
        }

        public Map<String, Pair> getLinkedPairs() {
            return linkedPairs;
        }

        public void setLinkedPairs(Map<String, Pair> linkedPairs) {
            this.linkedPairs = linkedPairs;
        }

        public boolean hasLinkedPairs() {
            return linkedPairs != null && linkedPairs.size() > 0;
        }

        @Override
        public String toString() {
            return this.front + ">" + this.back;
        }

        @Override
        public boolean equals(Object obj) {
            return toString().equals(obj.toString());
        }
    }

}
