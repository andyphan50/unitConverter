package com.csu.cta7csc475

import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {
    @Test
    fun `meters to centimeters conversion works`() {
        val vm = UnitConverterViewModel()

        vm.updateInputValue("2.0")
        vm.updateInputUnit("Meters", 1.0)
        vm.updateOutputUnit("Centimeters", 0.01)

        val state = vm.uiState.value
        assertEquals("200.0", state.outputValue)
    }

    @Test
    fun `feet to meters conversion works`() {
        val vm = UnitConverterViewModel()

        vm.updateInputValue("10")
        vm.updateInputUnit("Feet", 0.3048)
        vm.updateOutputUnit("Meters", 1.0)

        val state = vm.uiState.value
        assertEquals("3.05", state.outputValue)
    }

    @Test
    fun `invalid input defaults to zero`() {
        val vm = UnitConverterViewModel()

        vm.updateInputValue("abc")
        vm.updateInputUnit("Meters", 1.0)
        vm.updateOutputUnit("Meters", 1.0)

        val state = vm.uiState.value
        assertEquals("0.0", state.outputValue)
    }

}