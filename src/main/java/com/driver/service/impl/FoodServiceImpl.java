package com.driver.service.impl;

import com.driver.converter.FoodConverter;
import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodRepository foodRepository;

    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity=FoodConverter.dtoToEntity(food);
        foodRepository.save(foodEntity);
        return food;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
//        try {
//            if(!foodRepository.existsByFoodId(foodId)) throw new Exception("FoodID is not present");
//        }catch (Exception e){
//            System.out.println(e);
//        }
        FoodEntity foodEntity=foodRepository.findByFoodId(foodId);
        return FoodConverter.entityToDto(foodEntity);
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
//        try {
//            if(!foodRepository.existsByFoodId(foodId)) throw new Exception("FoodID is not present");
//        }catch (Exception e){
//            System.out.println(e);
//        }
        FoodEntity foodEntity=foodRepository.findByFoodId(foodId);
        FoodEntity newEntity=FoodEntity.builder()
                .id(foodEntity.getId()).foodId(foodEntity.getFoodId()).foodName(foodDetails.getFoodName())
                .foodPrice(foodDetails.getFoodPrice())
                .foodCategory(foodDetails.getFoodCategory()).build();
        foodRepository.save(newEntity);
        return FoodConverter.entityToDto(newEntity);
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        Long Id=Long.parseLong(id);
//        try {
//            if(!foodRepository.existsById(Id)) throw new Exception("ID is not present");
//        }catch (Exception e){
//            System.out.println(e);
//        }
        foodRepository.deleteById(Id);
    }

    @Override
    public List<FoodDto> getFoods() {
        List<FoodEntity> foodEntityList= (List<FoodEntity>) foodRepository.findAll();
        List<FoodDto> foodDtoList=new ArrayList<>();
        for(FoodEntity foodEntity:foodEntityList){
            foodDtoList.add(FoodConverter.entityToDto(foodEntity));
        }
        return foodDtoList;
    }
}