package cz.dmn.cpska

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class RxSchedulersOverrideRule(val scheduler: Scheduler = Schedulers.trampoline()) : TestRule {
    val instantSchedulerHandler = { _: Scheduler -> scheduler }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxAndroidPlugins.reset()
                RxAndroidPlugins.setMainThreadSchedulerHandler(instantSchedulerHandler)

                RxJavaPlugins.reset()
                RxJavaPlugins.setIoSchedulerHandler(instantSchedulerHandler)
                RxJavaPlugins.setNewThreadSchedulerHandler(instantSchedulerHandler)
                RxJavaPlugins.setComputationSchedulerHandler(instantSchedulerHandler)

                base.evaluate()

                RxAndroidPlugins.reset()
                RxJavaPlugins.reset()
            }
        }
    }
}