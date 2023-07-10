package com.example.attractions.Controller;

import com.example.attractions.Models.Attractions;
import com.example.attractions.Models.Reviews;
import com.example.attractions.Repo.AttractionsRepo;
import com.example.attractions.Repo.ReviewRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ControllerTest {
    @Autowired
    private AttractionsRepo AttractionsRepo;
    @Autowired
    private ReviewRepo ReviewRepo;
    @Test
    void addInfo() {
        //city=Moscow&name=GorkiyPark&category=park&Xcoord=15&Ycoord=10&review=21&numberOfReviews=5
        String city = "Moscow";
        String name = "Kremlin";
        String category = "fortress";
        int Xcoord = 40;
        int Ycoord = 60;
        int review = 15;
        int numberOfReviews = 5;
        Attractions record = new Attractions(city, name, category, Xcoord, Ycoord, review, numberOfReviews);
        AttractionsRepo.save(record);
        city = "Moscow";
        name = "Bolshoi Theatre";
        category = "theatre";
        Xcoord = 20;
        Ycoord = 25;
        review = 10;
        numberOfReviews = 5;
        record = new Attractions(city, name, category, Xcoord, Ycoord, review, numberOfReviews);
        AttractionsRepo.save(record);
        city = "Moscow";
        name = "Cathedral Of Christ The Saviour";
        category = "cathedral";
        Xcoord = 22;
        Ycoord = 27;
        review = 25;
        numberOfReviews = 5;
        record = new Attractions(city, name, category, Xcoord, Ycoord, review, numberOfReviews);
        AttractionsRepo.save(record);
        city = "Moscow";
        name = "Saint Basil`s Cathedral";
        category = "cathedral";
        Xcoord = 20;
        Ycoord = 30;
        review = 20;
        numberOfReviews = 5;
        record = new Attractions(city, name, category, Xcoord, Ycoord, review, numberOfReviews);
        AttractionsRepo.save(record);
        city = "Moscow";
        name = "Gorky Park";
        category = "park";
        Xcoord = 15;
        Ycoord = 10;
        review = 5;
        numberOfReviews = 5;
        record = new Attractions(city, name, category, Xcoord, Ycoord, review, numberOfReviews);
        AttractionsRepo.save(record);
    }

    @Test
    void getInfo() {
        String city = "Moscow";
        String category = "0";
        int Xcoord = 40;
        int Ycoord = 60;
        int radius = 100;
        int mark = 3;
        ArrayList<String> list = new ArrayList<String>();
        boolean isCategory = false;
        boolean isMarked = false;
        if (category.equals("0"))
            isCategory = true;
        if (mark==0)
            isMarked = true;
        for (Attractions record:
                AttractionsRepo.findAll()) {
            double distance = Math.pow((Math.pow(Xcoord - record.getXcoord(), 2) + Math.pow(Ycoord - record.getYcoord(), 2)), 0.5);
            double overallMark = 0;
            if (!isMarked)
                overallMark = record.getReview() / record.getNumberOfReviews();
            if ((city.equals(record.getCity())) & (distance < radius) & (category.equals(record.getCategory()) || isCategory) & ((mark < overallMark) || isMarked))
                list.add(record.getName());
        }
        if (list.isEmpty())
           System.out.println("Нет достопримечательностей, удовлетворяющих запросу");
        else{
            String[] answer = new String[list.size()];
            list.toArray(answer);
            for (int i = 0; i<answer.length; i++)
                System.out.println(answer[i]);
        }
    }

    @Test
    void addReview() {
        String name = "Gorky Park";
        String text = "good";
        int mark = 5;
        Reviews record = new Reviews(name, text, mark);
        ReviewRepo.save(record);
        for (Attractions info:
                AttractionsRepo.findAll()) {
            if (name.equals(info.getName())){
                AttractionsRepo.delete(info);
                info.setReview(info.getReview()+mark);
                info.setNumberOfReviews(info.getNumberOfReviews()+1);
                AttractionsRepo.save(info);
            }
        }
        name = "Gorky Park";
        text = "nice";
        mark = 4;
        record = new Reviews(name, text, mark);
        ReviewRepo.save(record);
        for (Attractions info:
                AttractionsRepo.findAll()) {
            if (name.equals(info.getName())){
                AttractionsRepo.delete(info);
                info.setReview(info.getReview()+mark);
                info.setNumberOfReviews(info.getNumberOfReviews()+1);
                AttractionsRepo.save(info);
            }
        }
        name = "Gorky Park";
        text = "bad";
        mark = 3;
        record = new Reviews(name, text, mark);
        ReviewRepo.save(record);
        for (Attractions info:
                AttractionsRepo.findAll()) {
            if (name.equals(info.getName())){
                AttractionsRepo.delete(info);
                info.setReview(info.getReview()+mark);
                info.setNumberOfReviews(info.getNumberOfReviews()+1);
                AttractionsRepo.save(info);
            }
        }
    }

    @Test
    void getReviews() {
        String name = "Gorky Park";
        Map<Double, String> list = new HashMap<>();
        double overallMark = 0;
        for (Attractions record:
                AttractionsRepo.findAll()) {
            if (name.equals(record.getName())){
                overallMark = record.getReview() / record.getNumberOfReviews();
            }
        }
        for (Reviews info:
                ReviewRepo.findAll()) {
            if (name.equals(info.getName())){
                list.put((double)info.getMark(), info.getText());
            }
        }
        list.put(overallMark, name);
        if (list.isEmpty())
            System.out.println("Нет отзывов на эту достопримечательность");
        else{
            for (Map.Entry<Double, String> pair : list.entrySet())
            {
                double key = pair.getKey();
                String value = pair.getValue();
                System.out.println(key + ":" + value);
            }
        }
    }
}