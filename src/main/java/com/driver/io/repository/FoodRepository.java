package com.driver.io.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.driver.io.entity.FoodEntity;

@Repository
public interface FoodRepository extends CrudRepository<FoodEntity, Long> {
	FoodEntity findByFoodId(String foodId);
//
//	boolean existsByFoodId(String foodId);

//	@Modifying
//	@Query("update FoodEntity s set s.foodId = :#{#food.foodId}, " +
//			"s.foodName = :#{#food.foodName}, " +
//			"s.foodPrice = :#{#food.foodPrice} ," +
//			"s.foodCategory = :#{#food.foodCategory} " +
//			"where s.id = :#{#food.id}")
//	int updateFoodDetails(FoodEntity food);
}
