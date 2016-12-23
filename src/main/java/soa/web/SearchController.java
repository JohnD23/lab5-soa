package soa.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SearchController {

	@Autowired
	private ProducerTemplate producerTemplate;
	
	private Map<String, Object> map;

	@RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping(value="/search")
    @ResponseBody
    public Object search(@RequestParam("q") String q,
			  			@RequestParam(value = "max", required = false) String max) {

    	map = new HashMap<String, Object>();
    	if (max!=null & !max.equals("")) {
    		map.put("CamelTwitterCount", max);
		}
    	map.put("CamelTwitterKeywords", q);
        return producerTemplate.requestBodyAndHeaders("direct:search", "", map);
    }
}