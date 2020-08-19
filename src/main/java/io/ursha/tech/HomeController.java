package io.ursha.tech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
class HomeController {

    @Autowired
    private CovidService covidService;

    @GetMapping("/")
    public String home(Model model){
        List<CovidDataModel> covidDataModels = covidService.getAllRegion("India");
        List<CovidDataModel> result = covidDataModels.stream().sorted((x,y)->y.getActiveCount()-x.getActiveCount()).collect(Collectors.toList());
        int sum = result.stream().mapToInt(a -> a.getActiveCount()).sum();
        model.addAttribute("case_list", result);
        model.addAttribute("total_cases", sum);

        return "home";
    }
}
