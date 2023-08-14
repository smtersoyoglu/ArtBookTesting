package com.sametersoyoglu.artbooktesting.model

import com.google.gson.annotations.SerializedName

data class ImageResult(
    val id: Int,
    val pageURL : String,
    val type: String,
    val tags: String,
    val previewURL: String,
    val previewWidth:Int,
    val previewHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int,
    val webformatHeight: Int,
    val largeImageURL: String,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth : Int,
    @SerializedName("user_id")
    val userId : Int,
    val views : Int,
    val downloads: Int,
    val collections: Int,
    val likes: Int,
    val comments: Int,
    val user: String,
    val userImageURL: String
    )
