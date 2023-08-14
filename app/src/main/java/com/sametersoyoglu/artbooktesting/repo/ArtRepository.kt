package com.sametersoyoglu.artbooktesting.repo

import androidx.lifecycle.LiveData
import com.bumptech.glide.load.engine.Resource
import com.sametersoyoglu.artbooktesting.api.RetrofitApi
import com.sametersoyoglu.artbooktesting.model.ImageResponse
import com.sametersoyoglu.artbooktesting.roomdb.Art
import com.sametersoyoglu.artbooktesting.roomdb.ArtDao
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artDao: ArtDao,
    private val retrofitApi: RetrofitApi) : ArtRepositoryInterface {
    override suspend fun insertArt(art: Art) {
        artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return artDao.observeArts()
    }

    override suspend fun searchImage(imageString: String): com.sametersoyoglu.artbooktesting.util.Resource<ImageResponse> {
        return try {
            val response = retrofitApi.imageSearch(imageString)

            if (response.isSuccessful) {
                response.body()?.let {
                    return@let com.sametersoyoglu.artbooktesting.util.Resource.success(it)
                } ?: com.sametersoyoglu.artbooktesting.util.Resource.error("Error",null)
            }else {
                com.sametersoyoglu.artbooktesting.util.Resource.error("Error",null)
            }
        }catch (e: Exception) {
            com.sametersoyoglu.artbooktesting.util.Resource.error("No data!",null)
        }
    }

}