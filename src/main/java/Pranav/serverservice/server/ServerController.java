package Pranav.serverservice.server;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import Pranav.serverservice.models.Vendor;

@RestController
public class ServerController {
	
	@RequestMapping("/products/{id}")
	public HashMap<String,Double> getBid(@PathVariable int id){
		HashMap<String,Double> bids = new HashMap<>();
		RestTemplate restTemplate =new RestTemplate();
		Vendor[] forNow = restTemplate.getForObject("http://localhost:8083/vendors",Vendor[].class);
		List<Vendor> vendors = Arrays.asList(forNow);
		for(Vendor vendor: vendors) {
			if(vendor.getProducts().containsKey(id)) {
				bids.put("vid:"+vendor.getId()+", name:"+vendor.getName() , vendor.getProducts().get(id));
			}
		}
		return bids;
	}
}
