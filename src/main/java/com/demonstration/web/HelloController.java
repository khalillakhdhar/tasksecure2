package com.demonstration.web;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
@GetMapping

public String hello(@PathVariable long id)
{
return "hello security";	
}
}
