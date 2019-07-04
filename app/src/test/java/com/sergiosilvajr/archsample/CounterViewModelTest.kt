package com.sergiosilvajr.archsample

import android.view.ViewGroup
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoRule

class CounterViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var viewModel: CounterViewModel

    @Mock
    lateinit var view: CounterView

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = CounterViewModel()
        viewModel.view = view
    }

    @Test
    fun testAddNumberToCounter() {
        viewModel.addOne()

        assertEquals(viewModel.counter.value, "1")
        verify(view, times(0)).showErrorPopup()
    }

    @Test
    fun testAddNumberToCounterMoreThan100() {
        viewModel.counter.value = "100"
        viewModel.addOne()

        assertEquals(viewModel.counter.value, "101")
        verify(view, times(0)).showErrorPopup()
        verify(view).warningMoreThan100()
    }

    @Test
    fun testSubtractNumberToCounter() {
        viewModel.removeOne()

        assertEquals(viewModel.counter.value, "-1")
        verify(view, times(0)).showErrorPopup()
    }

    @Test
    fun testAddNumberToCounterWithInvalidNumber() {
        viewModel.counter.value = "a"

        viewModel.addOne()

        assertNotEquals(viewModel.counter.value, "1")

        verify(view).showErrorPopup()
    }

    @Test
    fun testSubtractNumberToCounterWithInvalidNumber() {
        viewModel.view = view
        viewModel.counter.value = "a"

        viewModel.removeOne()

        assertNotEquals(viewModel.counter.value, "-1")
        verify(view).showErrorPopup()
    }
}
