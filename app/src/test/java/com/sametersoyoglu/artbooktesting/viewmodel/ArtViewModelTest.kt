package com.sametersoyoglu.artbooktesting.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.sametersoyoglu.artbooktesting.MainCoroutineRule
import com.sametersoyoglu.artbooktesting.getOrAwaitValueTest
import com.sametersoyoglu.artbooktesting.repo.FakeArtRepository
import com.sametersoyoglu.artbooktesting.util.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtViewModelTest {
    // livedata, coroutine, threading gibi işlemleri test te istemiyoruz dümdüz bir şekilde bunları çalıştır demek
    // için altaki 2 kodu da testlerimizde kullanırız. threading felan dinlemeden hepsini main thread de çalıştırmamızı sağlar
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup() {
        // Test Doubles -- repository i test etmek için fake bir repository yaparız.

        viewModel = ArtViewModel(FakeArtRepository())
    }


    // Fonksiyon adlarını testlerde bu şekilde verebiliriz - art oluşturucaz ve yıl vermiceğiz ve hata döndürecek demek istedik. bunu test ediyoruz.
    // aynı şekilde name ve artistname girilmediğinde hata döndürmeyi test ediyoruz. programımızda yaptığımız kodların testi
    @Test
    fun `insert art without year returns error` () {
        viewModel.makeArt("Mona Lisa","Da Vinci","")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        // LiveData yı testlerde gördüğümüzde sıkıntı var demektir.
        // LiveData asekron çalışır bu yüzden herşeyin mainthread de çalışmasını istiyoruz bunun içinde LiveDatayı normal bir dataya çevirmemiz gerekiyor.
        // daha sonra burdaki sınıfa burda ne görürsen gör mainthread de çalıştırmak istiyoruz dememiz lazım.
        // bunları yapmak için de LiveDataUtil ile MainCoroutineRule yaparız. getOrAwaitValueTest() ile value artık Livedata olmaktan kurtuldu Resource<Art> şeklinde geldi.
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without name returns error` () {
        viewModel.makeArt("","Da Vinci","1800")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without artistName returns error` () {
        viewModel.makeArt("Mona Lisa","","1800")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
}