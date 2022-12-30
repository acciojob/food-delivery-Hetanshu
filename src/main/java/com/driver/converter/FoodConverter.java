package com.driver.converter;

import com.driver.io.entity.FoodEntity;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.shared.dto.FoodDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FoodConverter {

    public static FoodDetailsResponse dtoToResponse(FoodDto foodDto){
        return FoodDetailsResponse.builder().foodId(foodDto.getFoodId())
                .foodName(foodDto.getFoodName()).foodPrice(foodDto.getFoodPrice())
                .foodCategory(foodDto.getFoodCategory()).build();
    }

    public static FoodDto requestToDto(FoodDetailsRequestModel foodDetails){
        return FoodDto.builder().foodName(foodDetails.getFoodName())
                .foodPrice(foodDetails.getFoodPrice())
                .foodCategory(foodDetails.getFoodCategory()).build();
    }

    public static FoodDto entityToDto(FoodEntity foodEntity) {
        return FoodDto.builder().id(foodEntity.getId())
                .foodId(foodEntity.getFoodId())
                .foodName(foodEntity.getFoodName())
                .foodPrice(foodEntity.getFoodPrice())
                .foodCategory(foodEntity.getFoodCategory()).build();
    }

    public static FoodEntity dtoToEntity(FoodDto food) {
        return FoodEntity.builder().id(food.getId())
                .foodId(food.getFoodId())
                .foodName(food.getFoodName())
                .foodPrice(food.getFoodPrice())
                .foodCategory(food.getFoodCategory()).build();
    }
}
