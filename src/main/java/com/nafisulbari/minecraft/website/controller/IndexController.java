package com.nafisulbari.minecraft.website.controller;


import com.nafisulbari.minecraft.website.dto.SiteVisitorsDto;
import com.nafisulbari.minecraft.website.service.SiteVisitorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class IndexController {

    @Autowired
    private SiteVisitorService siteVisitorService;


    @GetMapping("/")
    public String index(Model model, HttpServletRequest httpServletRequest) {

        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String currentYear = sdf.format(today);
        String year = "© ".concat(currentYear).concat(", Badurtola HighCommission");
        model.addAttribute("currentYear", currentYear);

        String ipAddress = "ip not found";
        if (httpServletRequest != null) {
            ipAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null || "".equals(ipAddress)) {
                ipAddress = httpServletRequest.getRemoteAddr();
            }
        }

        SiteVisitorsDto siteVisitorsDto = siteVisitorService.generateSiteVisitors(ipAddress);
        model.addAttribute("siteVisitorsDto", siteVisitorsDto);

        return "index"; // Name of the template without the .ftl extension coz mentioned in config
    }
}
