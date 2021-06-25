package com.project.app.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.model.Building;
import com.project.app.model.Dropdown;
import com.project.app.service.BuildingService;

@RestController
@RequestMapping("/rest/v1")


public class AppRestController {
	@Autowired
	BuildingService buildingService;
	
	@GetMapping(value="/get/buildings")
	public List<Dropdown> getBuildingNames() throws InterruptedException, ExecutionException {
		
	 List<Building> builds =	buildingService.getBuildingNames();
	 
	 List<Dropdown> list = new ArrayList<>();
	 for(Building build:builds)
	 {
		 Dropdown d = new Dropdown();
		 d.setKey(build.getBuildingKey());
		 d.setValue(build.getBuildingName());
		 d.setRoomNos(buildingService.getRoomNumbers(build.getBuildingKey()));
		 list.add(d);
	 }
	
	 return list;
	}
	
	@GetMapping(value="/get/vcNo")
	@ResponseBody
	public VcNo getVcNo(@RequestParam String buildingKey,@RequestParam String roomNo) throws InterruptedException, ExecutionException {
		VcNo vc= new VcNo();
		vc.setVcNo(buildingService.getVcNo(buildingKey,roomNo));
		return vc;
	}
}
class VcNo
{
	private String vcNo;

	/**
	 * @return the vcNo
	 */
	public String getVcNo() {
		return vcNo;
	}

	/**
	 * @param vcNo the vcNo to set
	 */
	public void setVcNo(String vcNo) {
		this.vcNo = vcNo;
	}
	
}

