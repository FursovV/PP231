package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.servise.CarServise;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarServise carServise;

    @Autowired
    public CarController(CarServise carServise) {
        this.carServise = carServise;
    }

    @GetMapping()
    public String cars(@RequestParam(required = false) Integer count, Model model) {
        model.addAttribute("cars", carServise.show(count));
        return "cars/car";
    }
}

