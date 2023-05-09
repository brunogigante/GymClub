package localhost.cm.gymclub.shared

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefModule {
    @Provides
    fun provideAuthSharedPref(@ApplicationContext context: Context) = AuthSharedPref(context)
}