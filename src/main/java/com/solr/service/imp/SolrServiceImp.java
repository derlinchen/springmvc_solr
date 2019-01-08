package com.solr.service.imp;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solr.service.SolrService;

@Service("solrService ")
public class SolrServiceImp implements SolrService {

	@Autowired
	private SolrClient client;

	@Override
	public void save() {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", "00001");
			map.put("name", "lijie");
			map.put("age", "30");
			map.put("info", "中国深圳市");

			SolrInputDocument doc = new SolrInputDocument();
			for (Entry<String, String> entry : map.entrySet()) {
				doc.addField(entry.getKey(), entry.getValue());
			}

			client.add("article", doc);
			client.commit("article");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete() {
		try {
			client.deleteById("article", "00001");
			client.commit("article");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", "00001");
			map.put("name", "lijie");
			map.put("age", "22");
			map.put("info", "中国深圳市");

			SolrInputDocument doc = new SolrInputDocument();
			for (Entry<String, String> entry : map.entrySet()) {
				doc.addField(entry.getKey(), entry.getValue());
			}

			client.add("article", doc);
			client.commit("article");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void query() {
		try {
			SolrQuery param = new SolrQuery();

			// q查询
			param.set("q", "info:中华");

			// filter查询
			param.addFilterQuery("id:[0 TO 00003]");

			// 排序
			param.setSort("id", SolrQuery.ORDER.asc);

			// 分页 从第0条开始取，取一条
			param.setStart(0);
			param.setRows(1);

			// 设置高亮
			param.setHighlight(true);

			// 设置高亮的字段
			param.addHighlightField("name");

			// 设置高亮的样式
			param.setHighlightSimplePre("<font color='red'>");
			param.setHighlightSimplePost("</font>");
			QueryResponse result = client.query("article", param);
			SolrDocumentList results = result.getResults();
			for (SolrDocument solrDocument : results) {
				System.out.println("id:" + solrDocument.get("id"));
				System.out.println("name:" + solrDocument.get("name"));
				System.out.println("age:" + solrDocument.get("age"));
				System.out.println("info:" + solrDocument.get("info"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
