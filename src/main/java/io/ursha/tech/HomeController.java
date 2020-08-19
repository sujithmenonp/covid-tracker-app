package io.ursha.tech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
class HomeController {

    @Autowired
    private CovidService covidService;

    @GetMapping("/")
    public String home(Model model){
        List<CovidDataModel> covidDataModels = covidService.getAll();
        //covidDataModels.sort(Comparator.comparingInt(CovidDataModel::getDiffFromPrevious).reversed());
        int newCount = covidDataModels.stream().mapToInt(a -> a.getDiffFromPrevious()).sum();
        int total = covidDataModels.stream().mapToInt(a -> a.getLatestCount()).sum();

        model.addAttribute("case_list", covidDataModels);
        model.addAttribute("total_cases", total);
        model.addAttribute("new_cases", newCount);
        return "home";
    }
}
