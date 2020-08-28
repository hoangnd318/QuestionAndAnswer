/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.question.QuestionConnection;
import dao.tfidf_dictionary.TFIDFDictionaryConnection;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Question;
import model.Tag;

/**
 *
 * @author nguye
 */
public class TFIDF {

    //chuan hoa tu, xoa bo cac ky tu dac biet
    public String convertString(String doc) {
        Pattern pattern = Pattern.compile("[^a-z0-9A-Z_àáãạảăắằẳẵặâấầẩẫậèéẹẻẽêềếểễệđìíĩỉịòóõọỏôốồổỗộơớờởỡợùúũụủưứừửữựỳỵỷỹýÀÁÃẠẢĂẮẰẲẴẶÂẤẦẨẪẬÈÉẸẺẼÊỀẾỂỄỆĐÌÍĨỈỊÒÓÕỌỎÔỐỒỔỖỘƠỚỜỞỠỢÙÚŨỤỦƯỨỪỬỮỰỲỴỶỸÝ]");
        Matcher matcher = pattern.matcher(doc);
        String convertDoc = matcher.replaceAll("").toLowerCase();
        return convertDoc;
    }

    //khoi tao bo tu dien
    public void inItDictionary() {
        List<Question> questions = QuestionConnection.getQuestionConnection().findAllTFIDF();
        questions.forEach((q) -> {
            String[] docsTitle = q.getTitle().replaceAll("\n", " ").split("\\s");
            for (String doc : docsTitle) {
                String convertDoc = convertString(doc);
                if (!convertDoc.equalsIgnoreCase(" ") && !convertDoc.equalsIgnoreCase("")) {
                    if (!TFIDFDictionaryConnection.getTFIDFDictionaryConnection().checkExisDoc(convertDoc)) {
                        TFIDFDictionaryConnection.getTFIDFDictionaryConnection().addDocToDictionary(convertDoc);
                    }
                }
            }
        });
    }

    //cap nhat bo tu dien khi them moi cau hoi
    public void updateDictionary(Question q) {
        String[] docsTitle = q.getTitle().replaceAll("\n", " ").split("\\s");
        for (String doc : docsTitle) {
            String convertDoc = convertString(doc);
            if (!convertDoc.equalsIgnoreCase(" ") && !convertDoc.equalsIgnoreCase("")) {
                if (!TFIDFDictionaryConnection.getTFIDFDictionaryConnection().checkExisDoc(convertDoc)) {
                    TFIDFDictionaryConnection.getTFIDFDictionaryConnection().addDocToDictionary(convertDoc);
                }
            }
        }
    }

    //chuyen cac question sang list cac tu
    public List<List<String>> convertQuestionsToDocs() {
        List<Question> questions = QuestionConnection.getQuestionConnection().findAllTFIDF();
        List<List<String>> docs = new ArrayList<>();
        for (Question q : questions) {
            List<String> doc = new ArrayList<>();
            String[] docsTitle = q.getTitle().replaceAll("\n", " ").split("\\s");
            for (String dt : docsTitle) {
                String convertDoc = convertString(dt);
                if (!convertDoc.equalsIgnoreCase(" ") && !convertDoc.equalsIgnoreCase("")) {
                    doc.add(convertDoc);
                }
            }
            docs.add(doc);
        }
        return docs;
    }

    //kiem tra tu trung` lap cua question
    public boolean checkTuLapCuaCauHoi(List<String> docs, String doc) {
        for (String dt : docs) {
            if (dt.equalsIgnoreCase(doc)) {
                return true;
            }
        }
        return false;
    }

    //
    public List<String> convertQuestionToDocKhongLap(Question question) {
        List<String> doc = new ArrayList<>();
        String[] docsTitle = question.getTitle().replaceAll("\n", " ").split("\\s");
        for (String dt : docsTitle) {
            String convertDoc = convertString(dt);
            if (!convertDoc.equalsIgnoreCase(" ") && !convertDoc.equalsIgnoreCase("") && !checkTuLapCuaCauHoi(doc, convertDoc)) {
                doc.add(convertDoc);
            }
        }
        return doc;
    }

    public List<String> convertQuestionToDoc(Question question) {
        List<String> doc = new ArrayList<>();
        String[] docsTitle = question.getTitle().replaceAll("\n", " ").split("\\s");
        for (String dt : docsTitle) {
            String convertDoc = convertString(dt);
            if (!convertDoc.equalsIgnoreCase(" ") && !convertDoc.equalsIgnoreCase("")) {
                doc.add(convertDoc);
            }
        }
        return doc;
    }

    public boolean checkTrung(List<String> tuCauHoi, String tu) {
        for (String dt : tuCauHoi) {
            if (dt.equalsIgnoreCase(tu)) {
                return true;
            }
        }
        return false;
    }

    public List<Double> processQuestion(Question question) {
        List<List<String>> allDoc = convertQuestionsToDocs();
        List<String> tuCuaCauHoi = convertQuestionToDoc(question);
        List<String> tuCauHoiKLap = convertQuestionToDocKhongLap(question);
        List<String> tuDien = TFIDFDictionaryConnection.getTFIDFDictionaryConnection().getDictionary();
        List<Double> vector = new ArrayList<>();
        vector.add((double) question.getId());
        for (String doc : tuDien) {
            if (checkTrung(tuCauHoiKLap, doc)) {
                double tfidf = tfIdf(tuCuaCauHoi, allDoc, doc);
                vector.add(tfidf);
            } else {
                double flag = 0;
                vector.add(flag);
            }
        }
        return vector;
    }

    public double tf(List<String> doc, String term) {
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word)) {
                result++;
            }
        }
        //bo chia tong so tu
        return result / doc.size();
    }

    public double idf(List<List<String>> docs, String term) {
        double n = 0;
        for (List<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        //neu n=0 return idf = 0
        if (n == 0) {
            return 0;
        }

        return Math.log(docs.size() / n);
    }

    public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
        return tf(doc, term) * idf(docs, term);

    }
}
