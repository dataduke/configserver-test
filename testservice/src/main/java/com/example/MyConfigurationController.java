package com.example;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyConfigurationController {

    @Value("${foo.bar}")
    private String bar;

    @Value("${foo.buz}")
    private String buz;

    @Value("${foo.joe}")
    private String joe;

    @Autowired
    private GroupConfigProperties groupConfigProperties;

    @RequestMapping(path = "/foo-config")
    public Map<String, String> getFooConfig() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("foo.bar", bar);
        configMap.put("foo.buz", buz);
        configMap.put("foo.joe", joe);
        return configMap;
    }

    @RequestMapping(path = "/group-config")
    public GroupConfigProperties getGroupConfig() {
        return groupConfigProperties;
    }
}
