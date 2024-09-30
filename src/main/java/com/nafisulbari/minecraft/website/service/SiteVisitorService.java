package com.nafisulbari.minecraft.website.service;

import com.nafisulbari.minecraft.website.dto.SiteVisitorsDto;
import com.nafisulbari.minecraft.website.entity.SiteVisitor;
import com.nafisulbari.minecraft.website.repository.SiteVisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SiteVisitorService {

    @Autowired
    private SiteVisitorRepository siteVisitorRepository;


    public SiteVisitorsDto generateSiteVisitors(String ipAddress) {

        Optional<SiteVisitor> optionalSiteVisitor = siteVisitorRepository.getSiteVisitorByIpAddress(ipAddress);

        if (optionalSiteVisitor.isPresent()) {
            //might cause issues if unable to fetch ip
            Date today = new Date();
            siteVisitorRepository.updateVisitingCountAndLastVisited(ipAddress, today);
        } else {
            SiteVisitor siteVisitor = new SiteVisitor();
            siteVisitor.setIpAddress(ipAddress);
            siteVisitor.setFirstVisited(new Date());
            siteVisitor.setLastVisited(new Date());
            siteVisitor.setVisitingCount(1);

            siteVisitorRepository.save(siteVisitor);
        }

        long totalVisitors = siteVisitorRepository.count();

        SiteVisitorsDto siteVisitorsDto = new SiteVisitorsDto();
        siteVisitorsDto.setTotalVisitors(totalVisitors);

        return siteVisitorsDto;
    }
}
