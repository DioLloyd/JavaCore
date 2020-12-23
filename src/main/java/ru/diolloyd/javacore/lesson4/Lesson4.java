package ru.diolloyd.javacore.lesson4;

import java.util.*;

public class Lesson4 {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>(Arrays.asList(
                "car", "home", "cat", "egg", "forest",
                "bike", "car", "dog", "cat", "road",
                "forest", "cat", "duck", "home", "cat"
        ));
        Collections.sort(words);
        System.out.println(words);
        HashSet<String> wordsUnique = new HashSet<>(words);
        System.out.println(wordsUnique);
        HashMap<String, Integer> wordQty = new HashMap<>();
        for (String word : words) {
            Integer value = wordQty.getOrDefault(word, 0);
            wordQty.put(word, ++value);
        }
        System.out.println(wordQty);


        Phonebook users = new Phonebook();
        users.add("Ivanov", 89991111111L);
        users.add("Mendeleev", 89992222222L);
        users.add("Pushkin", 89993333333L);
        users.add("Connor", 89994444444L);
        users.add("Ivanov", 89995555555L);
        System.out.println(users.get("Ivanov"));
        System.out.println(users.get("Mendeleev"));
    }
}
