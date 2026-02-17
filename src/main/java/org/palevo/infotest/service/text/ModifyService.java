package org.palevo.infotest.service.text;

import org.palevo.infotest.DAO.process.ProcessDAO;
import org.springframework.stereotype.Service;

@Service
public class ModifyService {
    public final String RU_VOWELS = "уеёыаоэяию";
    public final String EN_VOWELS = "eyuioa";
    public ProcessDAO processDAO;

    public String modify(String value) {
        if (value == null || value.equals("")) {
            return value;
        }
        String result = reverse(replaceRuVowels(replaceEnVowels(value)));

        return result;

    }

    private String reverse(String value) {
        String reverse = String.valueOf(new StringBuilder(value).reverse());
        return reverse;
    }

    private String replaceRuVowels(String value) {
        value = value.toLowerCase();
        String replaceVowels = "";
        for (char c : value.toCharArray()) {
            if (c == 255) {
                replaceVowels += (char) 192;
            }else if(c == 184){
                replaceVowels += (char) 230;
            } else if (RU_VOWELS.contains(String.valueOf(c))) {
                replaceVowels += (char) (1 + c);
            } else {
                replaceVowels += (char) c;
            }
        }
        return replaceVowels;
    }

    private String replaceEnVowels(String value) {
        value = value.toLowerCase();
        String replaceVowels = "";
        for (char c : value.toCharArray()) {
            if (c == 122) {
                replaceVowels += (char) 97;
            } else if (EN_VOWELS.contains(String.valueOf(c))) {
                replaceVowels += (char) (1 + c);
            } else {
                replaceVowels += (char) c;
            }
        }
        return replaceVowels;
    }
}
