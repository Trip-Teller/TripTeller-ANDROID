package com.our.tripteller.data

data class ResponseUnsplashData(
    val total: Int,
    val total_pages: Int,
    val results: List<ImageInfoData>
)

data class ImageInfoData(
    val urls: SearchImagesData
)

data class SearchImagesData(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)