package com.cloud.front.web;


import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 搜索
 * @author xiaosa
 */
@RestController
@RequestMapping("elasticSearch")
public class ElasticSearchController {

    @Autowired
    private TransportClient client;

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @GetMapping("/get/people/man")
    @ResponseBody
    public ResponseEntity getPeople(@RequestParam(name = "id", defaultValue = "") String id){
        if (id.isEmpty()) {
            return new ResponseEntity( HttpStatus.NOT_FOUND);
        }
        GetResponse response = this.client.prepareGet("people", "man", id).get();
        if(response.isExists()){
            return new ResponseEntity(response.getSource(), HttpStatus.OK);
        }else {
            return new ResponseEntity(response.getSource(), HttpStatus.NOT_FOUND);
        }
    }


    /**
     * 添加
     * @param name
     * @param country
     * @param age
     * @param date
     * @return
     */
    @PostMapping("/add/people/man")
    @ResponseBody
    public ResponseEntity addPeople(@RequestParam(name = "name") String name,
                                    @RequestParam(name = "country") String country,
                                    @RequestParam(name = "age") int age,
                                    @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        try {
           XContentBuilder content =  XContentFactory.jsonBuilder()
                    .startObject()
                    .field("name", name)
                    .field("country", country)
                    .field("age", age)
                    .field("date", date.getTime())
                    .endObject();
            IndexResponse result = this.client.prepareIndex("people", "man").setSource(content).get();
            return new ResponseEntity(result.getId(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return  new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @PostMapping("/remove/people/man")
    @ResponseBody
    public ResponseEntity removePeople(@RequestParam(name = "id") String id){
        DeleteResponse result = this.client.prepareDelete("people", "man", id).get();
        return new ResponseEntity(result.toString(), HttpStatus.OK);
    }


    /**
     * 根据ID更新名字
     * @param id
     * @param name
     * @return
     */
    @PostMapping("/update/people/man")
    @ResponseBody
    public ResponseEntity updatePeople(@RequestParam(name = "id") String id,
                                       @RequestParam(name = "name") String name){
        UpdateRequest update = new UpdateRequest("people", "man", id);
        try {
            XContentBuilder build =  XContentFactory.jsonBuilder().startObject();
                if(name !=null){
                    build.field("name", name);
                }
            build.endObject();
            update.doc(build);
            UpdateResponse result = this.client.update(update).get();
            return new ResponseEntity(result.toString(), HttpStatus.OK);
         } catch (Exception e) {
            e.printStackTrace();
            return  new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 复合查询
     * @param name
     * @param country
     * @param gtDate
     * @param ltDate
     * @return
     */
    @PostMapping("/query/people/man")
    @ResponseBody
    public ResponseEntity queryPeople(@RequestParam(name = "name") String name,
                                      @RequestParam(name = "country") String country,
                                      @RequestParam(name = "gtDate") String gtDate,
                                      @RequestParam(name = "ltDate") String ltDate){
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if(name !=null){
            boolQuery.must(QueryBuilders.matchQuery("name", name));
        }
        if(country !=null){
            boolQuery.must(QueryBuilders.matchQuery("country", country));
        }
        //范围查询
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("date").from(gtDate);
        if(ltDate !=null ){
            rangeQuery.to(ltDate);
        }
        boolQuery.filter(rangeQuery);
        SearchRequestBuilder builder = this.client.prepareSearch("people")
                .setTypes("man")
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setQuery(boolQuery)
                .setFrom(0)
                .setSize(10);
        System.out.println(builder);
        SearchResponse response = builder.get();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (SearchHit hit: response.getHits()) {
            result.add(hit.getSource());
        }
        return  new ResponseEntity(result, HttpStatus.OK);
    }
}
