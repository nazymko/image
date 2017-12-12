package org.nazymko.imagegen.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Frontend {

    @RequestMapping(value = "draw", method = RequestMethod.GET)
    public String draw() {
        return null;
    }

}
