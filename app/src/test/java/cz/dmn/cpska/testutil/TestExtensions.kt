package cz.dmn.cpska.testutil

import org.hamcrest.CoreMatchers
import org.junit.Assert

infix fun Any?.shouldEqual(that: Any?) {
    that?.let {
        Assert.assertThat(this, CoreMatchers.equalTo(that))
    } ?: Assert.assertThat(this, CoreMatchers.nullValue())
}