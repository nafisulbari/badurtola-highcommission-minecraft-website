package com.nafisulbari.minecraft.website.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class SiteVisitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ipAddress;

    private Date lastVisited;

    private Date firstVisited;

    private int visitingCount;
}
