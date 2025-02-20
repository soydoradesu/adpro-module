package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class HomepageController {
    @GetMapping("/")
    public String homepage() { // Renamed from Homepage() to homepage()
        return "Homepage";
    }
}
