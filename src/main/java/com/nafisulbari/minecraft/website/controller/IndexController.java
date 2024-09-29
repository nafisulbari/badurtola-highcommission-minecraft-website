package com.nafisulbari.minecraft.website.controller;


import com.nafisulbari.minecraft.website.model.MenuItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {


    @GetMapping("/")
    public String index(Model model) {
        // Data to be passed to the template
        model.addAttribute("title", "My Website");
        model.addAttribute("header", "Welcome to My Website");

        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem("Home", "/home"),
                new MenuItem("About", "/about"),
                new MenuItem("Contact", "/contact")
        );

        model.addAttribute("menuItems", menuItems);
        model.addAttribute("year", 2024);

        return "index"; // Name of the template without the .ftl extension coz mentioned in config
    }
}
