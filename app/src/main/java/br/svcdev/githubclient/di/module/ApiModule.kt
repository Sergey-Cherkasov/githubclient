package br.svcdev.githubclient.di.module

import br.svcdev.githubclient.GithubClientApp
import br.svcdev.githubclient.common.AndroidNetworkStatus
import br.svcdev.githubclient.common.interfaces.INetworkStatus
import br.svcdev.githubclient.model.api.interfaces.IDataSource
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {
    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://api.github.com/"

    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): IDataSource = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IDataSource::class.java)


    @Singleton
    @Provides
    fun gson() = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()


    @Singleton
    @Provides
    fun networkStatus(app: GithubClientApp): INetworkStatus = AndroidNetworkStatus(app)
}
