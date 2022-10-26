package com.polysocial.rest.controller;

import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
        
        List<String> list = new ArrayList<String>();
        List<String> b = new ArrayList<String>();
        b.add("1");
        b.add("2");
        b.add("3");
        b.add("4");
        list.add("Java");
        list.add("C++");
        list.add("PHP");
        list.add("Python");
        List<String> all = new ArrayList<String>();
        all.addAll(b);
        all.addAll(list);
        System.out.println("-->"+all);
        System.out.println("/--->   "+all.get(5));
        
    }

}
