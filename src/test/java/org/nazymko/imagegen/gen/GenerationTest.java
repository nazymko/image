package org.nazymko.imagegen.gen;

import org.junit.Test;
import org.nazymko.imagegen.model.Job;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class GenerationTest {

    @Test
    public void draw() throws IOException, FontFormatException {
        Job job = new Job();
        job.setTitle("Java developer, 6 months, $2700-3800 net, Stockholm, Sweden");
        job.setCity(Arrays.asList("Київ", "Харків"));
        job.setCompany("Innova LTD");
        job.setSkills(Arrays.asList("PHP7", "Angular JS", "Full Stack Developer", "JavaScript", "Node.js", "NoSQL"));

        new Generation().draw(job);
    }
}