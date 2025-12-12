package com.crawler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crawler.model.TableMeta;
import com.crawler.service.DatabaseCrawlerService;
import com.crawler.service.ModelGeneratorService;

@RestController
@RequestMapping("/api/crawler")
public class CrawlerController {

    @Autowired
    private DatabaseCrawlerService databaseCrawlerService;

    @Autowired
    private ModelGeneratorService modelGeneratorService;

    @GetMapping("/tables")
    public List<TableMeta> getTables() throws Exception {
        return databaseCrawlerService.getAllTables();
    }

    @GetMapping("/models")
    public List<String> getModels() throws Exception {
        var tables = databaseCrawlerService.getAllTables();
        return tables.stream()
                .map(modelGeneratorService::generateModelClass)
                .toList();
    }

    @GetMapping("/table/{name}")
    public TableMeta getTable(@PathVariable String name) throws Exception {
        var tables = databaseCrawlerService.getAllTables();
        return tables.stream()
                .filter(t -> t.getTableName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}

