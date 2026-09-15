package di

import com.demo.MainActivity
import com.demo.core.data.client.di.ClientModuleImpl
import com.demo.feature.tvshow.data.api.di.FeatureTvShowDataModuleImpl
import dagger.Component

@Component(modules = [
    ClientModuleImpl::class,
    FeatureTvShowDataModuleImpl::class,
    FeatureTvShowDataModuleImpl::class
])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}