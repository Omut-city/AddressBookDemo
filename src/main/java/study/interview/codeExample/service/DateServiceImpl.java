package study.interview.codeExample.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.interview.codeExample.api.DateService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class DateServiceImpl implements DateService {

    @Override
    public List<Integer> getYears() {
        ArrayList<Integer> years = new ArrayList<>();
        for (int i = 1970; i < LocalDate.now().getYear(); i++) {
            years.add(i);
        }
        return years;
    }

    @Override
    public List<String> getMonths() {
        return List.of("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    }

    @Override
    public List<Integer> getDays() {
        ArrayList<Integer> days = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            days.add(i);
        }
        return days;
    }
}
