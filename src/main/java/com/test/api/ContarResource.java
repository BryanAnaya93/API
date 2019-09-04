/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Bryan
 */
@Path("contar")
@RequestScoped
public class ContarResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ContarResource
     */
    public ContarResource() {
    }

    /**
     * Retrieves representation of an instance of com.test.api.ContarResource
     * @param value
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@QueryParam("value") String value) {
        if(value != null && !value.isEmpty()){
            Map<String, Long> map = Arrays.asList(value.split("")).stream().collect(Collectors.groupingBy(c -> c.toLowerCase(), Collectors.counting()));
            List<String> fnl = map.entrySet().stream().filter(e -> e.getValue() > 1).map(e -> e.getKey()).collect(Collectors.toList());
            Map<String, Integer> res = new HashMap<>();
            res.put(value,fnl.size());

            JSONObject jo = new JSONObject(res);
            return Response.ok(jo.toString())
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                .build();
        }else{
            return Response.noContent()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                .build();
        }            
    }
}
