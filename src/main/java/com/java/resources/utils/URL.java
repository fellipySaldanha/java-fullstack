package com.java.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static List<Integer> decodeIntList(String value) {
        return Arrays.asList(value.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }

    public static String decodeParameters(String parameter) {
        try {
            return URLDecoder.decode(parameter, "UTF-8");
        } catch (UnsupportedEncodingException e) {            
            return "";
        }
    }
    
}