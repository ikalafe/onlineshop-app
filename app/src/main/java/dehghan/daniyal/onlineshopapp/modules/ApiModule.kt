package dehghan.daniyal.onlineshopapp.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dehghan.daniyal.onlineshopapp.api.customer.UserApi
import dehghan.daniyal.onlineshopapp.api.invoices.InvoiceApi
import dehghan.daniyal.onlineshopapp.api.invoices.TransactionApi
import dehghan.daniyal.onlineshopapp.api.products.ColorApi
import dehghan.daniyal.onlineshopapp.api.products.ProductApi
import dehghan.daniyal.onlineshopapp.api.products.ProductCategoryApi
import dehghan.daniyal.onlineshopapp.api.site.BlogApi
import dehghan.daniyal.onlineshopapp.api.site.ContentApi
import dehghan.daniyal.onlineshopapp.api.site.SliderApi
import dehghan.daniyal.onlineshopapp.config.UnsafeSSLConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApi(): Retrofit {
        return Retrofit.Builder()
//            .baseUrl("https://21.100.43.52:9090/")
            .baseUrl("https://onlineshop.holosen.net:9090/")
            .client(UnsafeSSLConfig.unsafeOkHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApi(): UserApi {
        return provideApi().create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideInvoiceApi(): InvoiceApi {
        return provideApi().create(InvoiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTransactionApi(): TransactionApi {
        return provideApi().create(TransactionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideColorApi(): ColorApi {
        return provideApi().create(ColorApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBlogApi(): BlogApi {
        return provideApi().create(BlogApi::class.java)
    }

    @Provides
    @Singleton
    fun provideContentApi(): ContentApi {
        return provideApi().create(ContentApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSliderApi(): SliderApi {
        return provideApi().create(SliderApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductCategoryApi(): ProductCategoryApi {
        return provideApi().create(ProductCategoryApi::class.java)
    }
    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return provideApi().create(ProductApi::class.java)
    }
}