package com.tbadhit.submission_bajp_1.data

data class ItemEntity(
    var id: Int? = 0,
    var imagePath: String?,
    var backdropsImagePath: String?,
    var title: String?,
    var description: String?,
    var releaseYear: String?,
    var duration: String?,
    var genre: String?,
    var rate: Double?
)
