package dev.amal.core.data.preferences

//class DefaultPreferences() {
//
//    override suspend fun loadUserInfo(): UserInfo = withContext(Dispatchers.IO) {
//        val age = preferences.getInt(KEY_AGE, -1)
//        val height = preferences.getInt(KEY_HEIGHT, -1)
//        val weight = preferences.getFloat(KEY_WEIGHT, -1f)
//        val genderString = preferences.getString(KEY_GENDER, null)
//        val activityLevelString = preferences.getString(KEY_ACTIVITY_LEVEL, null)
//        val goalType = preferences.getString(KEY_GOAL_TYPE, null)
//        val carbRatio = preferences.getFloat(KEY_CARB_RATIO, -1f)
//        val proteinRatio = preferences.getFloat(KEY_PROTEIN_RATIO, -1f)
//        val fatRatio = preferences.getFloat(KEY_FAT_RATIO, -1f)
//
//        UserInfo(
//            gender = Gender.valueOf(genderString ?: Gender.MALE.name),
//            age = age,
//            weight = weight,
//            height = height,
//            activityLevel = ActivityLevel.valueOf(activityLevelString ?: MEDIUM.name),
//            goalType = GoalType.valueOf(goalType ?: GoalType.KEEP_WEIGHT.name),
//            carbRatio = carbRatio,
//            proteinRatio = proteinRatio,
//            fatRatio = fatRatio
//        )
//    }
//}