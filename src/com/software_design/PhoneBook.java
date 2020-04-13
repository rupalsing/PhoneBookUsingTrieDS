package com.software_design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PhoneBook {
    TrieNode root;
    HashMap<String,Long> entries;

    public PhoneBook() {
        root = new TrieNode();
        entries = new HashMap<String, Long>();
    }

    public long getNumber(String contact) {
    	 return entries.get(contact);
    }
    
    public void insert(String contact, Long number ) {
        TrieNode current = root;
        entries.put(contact, number);

        for (char ch : contact.toCharArray()) {
            current = current.child.computeIfAbsent(ch, c -> new TrieNode());
        }

        current.isLast = true;
    }

    public LinkedList<String> search(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            TrieNode node = current.child.get(ch);
            if (node == null)
                return new LinkedList<>();
            current = node;
        }
        return getAllPrefixes(current, prefix);
    }

    private LinkedList<String> getAllPrefixes(TrieNode current, String prefixToAdd) {

        LinkedList<String> result = new LinkedList<>();
        if (current.isLast)
            result.add("");

        for (Map.Entry<Character, TrieNode> item : current.child.entrySet()) {
            char key = item.getKey();
            for (String s : getAllPrefixes(item.getValue(), "")) {
                result.add(prefixToAdd + key + s);
            }
        }

        return result;
    }
}
