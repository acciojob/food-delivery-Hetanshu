package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.converter.FoodConverter;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	FoodServiceImpl foodService;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
		FoodDto foodDto=foodService.getFoodById(id);
		return FoodConverter.dtoToResponse(foodDto);
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		FoodDto foodDto=foodService.createFood(FoodConverter.requestToDto(foodDetails));
		return FoodConverter.dtoToResponse(foodDto);
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDto foodDto=FoodDto.builder().foodId(id).foodName(foodDetails.getFoodName()).foodPrice(foodDetails.getFoodPrice()).foodCategory(foodDetails.getFoodCategory()).build();
		foodDto=foodService.updateFoodDetails(id,foodDto);
		return FoodConverter.dtoToResponse(foodDto);
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
		OperationStatusModel obj=new OperationStatusModel();
		foodService.deleteFoodItem(id);
		return obj;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {
		List<FoodDto> foodDtoList=foodService.getFoods();
		List<FoodDetailsResponse> foodDetailsResponseList=new ArrayList<>();
		for(FoodDto foodDto:foodDtoList) foodDetailsResponseList.add(FoodConverter.dtoToResponse(foodDto));
		return foodDetailsResponseList;
	}
}
