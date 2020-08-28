/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 *
 * @author nguye
 */
public class ConvertSEO {

    public String convertStringToSEO(String str) {
        try {
            String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp)
                    .replaceAll("")
                    .toLowerCase()
                    .replaceAll("đ", "d")
                    .replaceAll("\\_", "")
                    .replaceAll("[^\\w\\s]", "")
                    .replaceAll(" ", "-");
        } catch (Exception e) {
        }
        return "";
    }
}
