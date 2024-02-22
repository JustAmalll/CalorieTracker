package dev.amal.tracker_data.repository

import dev.amal.tracker_data.mapper.toTrackableFood
import dev.amal.tracker_data.mapper.toTrackedFood
import dev.amal.tracker_data.mapper.toTrackedFoodEntity
import dev.amal.tracker_data.source.cache.TrackerDao
import dev.amal.tracker_data.source.remote.OpenFoodApi
import dev.amal.tracker_domain.model.TrackableFood
import dev.amal.tracker_domain.model.TrackedFood
import dev.amal.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
) : TrackerRepository {

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> = runCatching {
        val result = api.searchFood(
            query = query,
            page = page,
            pageSize = pageSize
        ).products

        result.filter {
            val calculatedCalories = it.nutriments.carbohydrates100g * 4f +
                    it.nutriments.proteins100g * 4f +
                    it.nutriments.fat100g * 9f
            val lowerBound = calculatedCalories * 0.99f
            val upperBound = calculatedCalories * 1.01f

            it.nutriments.energyKcal100g in (lowerBound..upperBound)
        }.mapNotNull { it.toTrackableFood() }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(
        localDate: LocalDate
    ): Flow<List<TrackedFood>> = dao.getFoodsForDate(
        day = localDate.dayOfMonth,
        month = localDate.monthValue,
        year = localDate.year
    ).map { entities -> entities.map { it.toTrackedFood() } }
}