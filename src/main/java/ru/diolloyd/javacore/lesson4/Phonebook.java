package ru.diolloyd.javacore.lesson4;

import java.util.HashMap;
import java.util.HashSet;

public class Phonebook {
    HashMap<String, HashSet<Long>> phonebook;

    public Phonebook() {
        this.phonebook = new HashMap<>();
    }

    public void add(String surname, long phoneNumber) {
        if (!phonebook.containsKey(surname)) {
            this.phonebook.put(surname, new HashSet<>());
        }
//        или вместо if
//        phonebook.putIfAbsent(surname, new HashSet<>());
        phonebook.get(surname).add(phoneNumber);
//        или вообще в одну строчку
//        phonebook.computeIfAbsent(surname, key -> new HashSet<>()).add(phoneNumber);
    }

    public HashSet<Long> get(String surname) {
        return this.phonebook.get(surname);
    }

}
