/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.question.QuestionConnection;
import dao.question_recommended.QuestionRecommendedConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Question;
import model.Tag;

/**
 *
 * @author nguye
 */
public class KNN {

    public Double getDistanceBetween(List<Double> dataElement1, List<Double> dataElement2) {
        double sum = 0;
        for (int i = 1; i < dataElement1.size(); i++) {
            sum += (dataElement2.get(i) - dataElement1.get(i)) * (dataElement2.get(i) - dataElement1.get(i));
        }
        double distance = Math.abs(Math.sqrt(sum));
        return distance;
    }

    public List<Integer> knn(List<Double> q_vector, List<List<Double>> data) {
        List<Integer> list_q_recommended = new ArrayList<>();
        List<Double> distance_arr = new ArrayList<>();
        List<Double> distance_arr_clone = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            double distance = getDistanceBetween(q_vector, data.get(i));
            distance_arr.add(distance);
            distance_arr_clone.add(distance);
        }
        Collections.sort(distance_arr, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o1.compareTo(o2);
            }
        });
        List<Double> shortestDistances = distance_arr;
        if(data.size() >= 5){
            shortestDistances = distance_arr.subList(0, 5);
        }
        for (int i = 0; i < shortestDistances.size(); i++) {
            Integer indexOnClone = distance_arr_clone.indexOf(shortestDistances.get(i));
            int q_id = data.get(indexOnClone).get(0).intValue();
            list_q_recommended.add(q_id);
        }
        return list_q_recommended;
    }

    public void xuLuCauHoi(Question question) {
        TFIDF tfidf = new TFIDF();
        List<Question> qs = new ArrayList<>();
        qs = QuestionConnection.getQuestionConnection().findByTagQuestionOther(question.getTags());
        List<List<Double>> q_v = new ArrayList<>();
        for (Question q : qs) {
            if(q.getId() != question.getId()){
                q_v.add(tfidf.processQuestion(q));
            }
        }
        List<Integer> list_q_recommended = knn(tfidf.processQuestion(question), q_v);
        for (int data : list_q_recommended) {
            Question tmp = QuestionConnection.getQuestionConnection().findById(data).get(0);
            System.out.println(tmp.getTitle());
//            QuestionRecommendedConnection
//                    .getQuestionRecommendedConnection()
//                    .updateRecommendedQuestion(question.getId(), data);
        }
    }

    public static void main(String[] args) {
        KNN knn = new KNN();
        List<Question> qs = new ArrayList<>();
        qs = QuestionConnection.getQuestionConnection().findAllTFIDF();
//        System.out.println(qs.get(0).getTitle());
        System.out.println(qs.get(1).getTitle());
//        System.out.println(" ");
        knn.xuLuCauHoi(qs.get(1));
//        for (Question q : qs) {
//            knn.xuLuCauHoi(q);
//        }
    }
}
