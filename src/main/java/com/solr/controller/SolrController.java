package com.solr.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solr.service.SolrService;

@RestController
public class SolrController {
	
	@Resource
	private SolrService solrService;
	
	@RequestMapping("/save")
	public void save(){
		solrService.save();
	}
	
	@RequestMapping("/delete")
	public void delete(){
		solrService.delete();
	}
	
	@RequestMapping("/update")
	public void update(){
		solrService.update();
	}
	
	@RequestMapping("/query")
	public void query(){
		solrService.query();
	}
	
}
