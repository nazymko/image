package org.nazymko.imagegen.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Job {
    String title, company;
    List<String> skills,city = new ArrayList<>();
}
