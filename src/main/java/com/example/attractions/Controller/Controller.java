package com.example.attractions.Controller;

import com.example.attractions.Models.Attractions;
import com.example.attractions.Models.Reviews;
import com.example.attractions.Repo.AttractionsRepo;
import com.example.attractions.Repo.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {
    @Autowired
    private AttractionsRepo AttractionsRepo;
    @Autowired
    private ReviewRepo ReviewRepo;
    @PostMapping(value = "/", params ={ "city", "name","category","Xcoord","Ycoord","review","numberOfReviews"})
    public String addInfo(@RequestParam String city, @RequestParam String name,@RequestParam String category, @RequestParam int Xcoord,@RequestParam int Ycoord, @RequestParam int review, @RequestParam int numberOfReviews) {
        Attractions record = new Attractions(city, name, category, Xcoord, Ycoord, review, numberOfReviews);
        AttractionsRepo.save(record);
        return "Запись успешно добавлена";
    }
    @GetMapping(value = "/", params ={ "city","Xcoord","Ycoord","radius","category","mark"})
    public String[] getInfo(@RequestParam String city, @RequestParam int Xcoord, @RequestParam int Ycoord, @RequestParam double radius, @RequestParam String category, @RequestParam double mark) throws Exception{
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
            return new String[]{"Нет достопримечательностей, удовлетворяющих запросу"};
        else{
            String[] answer = new String[list.size()];
            list.toArray(answer);
            return answer;
        }
    }
    @PostMapping(value = "/", params ={ "name","text","mark"})
    public String addReview(@RequestParam String name,@RequestParam String text, @RequestParam int mark) {
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
        return "Запись успешно добавлена";
    }
    @GetMapping()
    public Map getReviews(@RequestParam String name) throws Exception{
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
        if (list.isEmpty())
            throw new Exception("Нет отзывов на эту достопримечательность");
        else{
            list.put(overallMark, name);
            return list;
        }
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Неверный формат запроса");
    }
}
