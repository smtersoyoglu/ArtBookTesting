package com.sametersoyoglu.artbooktesting.repo

import androidx.lifecycle.LiveData
import com.sametersoyoglu.artbooktesting.model.ImageResponse
import com.sametersoyoglu.artbooktesting.roomdb.Art
import com.sametersoyoglu.artbooktesting.util.Resource


interface ArtRepositoryInterface {

    suspend fun insertArt( art: Art)

    suspend fun deleteArt( art: Art )

    fun getArt() : LiveData<List<Art>>

    suspend fun searchImage( imageString : String) : Resource<ImageResponse>
}