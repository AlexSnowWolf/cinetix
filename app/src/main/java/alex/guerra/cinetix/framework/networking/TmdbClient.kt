package alex.guerra.cinetix.framework.networking

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object TmdbClient {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "53c5ebef66e66e5f6bf80afd2fa803f9"

    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val request = original.url.newBuilder().addQueryParameter("api_key", API_KEY)
                .build()
            val finalRequest = original.newBuilder().url(request).build()
            return@Interceptor chain.proceed(finalRequest)
        })
        .addInterceptor(interceptor)
        .build()

    val tmdbService : TheMovieDbService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(TheMovieDbService::class.java)
    }




}