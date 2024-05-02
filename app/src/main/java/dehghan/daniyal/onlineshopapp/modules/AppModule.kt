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
import dehghan.daniyal.onlineshopapp.repositories.customer.UserRepository
import dehghan.daniyal.onlineshopapp.repositories.invoices.InvoiceRepository
import dehghan.daniyal.onlineshopapp.repositories.invoices.TransactionRepository
import dehghan.daniyal.onlineshopapp.repositories.products.ColorRepository
import dehghan.daniyal.onlineshopapp.repositories.products.ProductCategoryRepository
import dehghan.daniyal.onlineshopapp.repositories.products.ProductRepository
import dehghan.daniyal.onlineshopapp.repositories.site.BlogRepository
import dehghan.daniyal.onlineshopapp.repositories.site.ContentRepository
import dehghan.daniyal.onlineshopapp.repositories.site.SliderRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserRepository(api: UserApi) = UserRepository(api = api)

    @Provides
    @Singleton
    fun provideInvoiceRepository(api: InvoiceApi) = InvoiceRepository(api = api)

    @Provides
    @Singleton
    fun provideTransactionRepository(api: TransactionApi) = TransactionRepository(api = api)

    @Provides
    @Singleton
    fun provideColorRepository(api: ColorApi) = ColorRepository(api = api)

    @Provides
    @Singleton
    fun provideProductCategoryRepository(api: ProductCategoryApi) = ProductCategoryRepository(api = api)

    @Provides
    @Singleton
    fun provideProductRepository(api: ProductApi) = ProductRepository(api = api)

    @Provides
    @Singleton
    fun provideSliderRepository(api: SliderApi) = SliderRepository(api = api)

    @Provides
    @Singleton
    fun provideBlogRepository(api: BlogApi) = BlogRepository(api = api)

    @Provides
    @Singleton
    fun provideContentRepository(api: ContentApi) = ContentRepository(api = api)
}