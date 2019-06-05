package cn.nupt.wechat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test
 *
 * @AUTHOR PizAn
 * @CREAET 2019-06-02 20:04
 */

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "this is test";
    }



}
