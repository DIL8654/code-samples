package io.dilankam.springsecurityjwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DilankaM
 * @created 31/10/2021 - 10:16
 */


@RestController
public class HelloController {

    @RequestMapping("/hello")
   public String hello()
   {
       return "hello friend!!";
   }
}
