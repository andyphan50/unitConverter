package com.csu.cta7csc475

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.roundToInt

class UnitConverterViewModel(

): ViewModel() {

    private val _uiState = MutableStateFlow(UnitConverterUiState())
    val uiState: StateFlow<UnitConverterUiState> = _uiState

    fun updateInputValue(newValue: String) {
        _uiState.value = _uiState.value.copy(inputValue = newValue)
        recalc()
    }

    fun updateOutputValue(newValue: String) {
        _uiState.value = _uiState.value.copy(outputValue = newValue)
        recalc()
    }

    fun updateInputUnit(newValue: String, factor: Double) {
        _uiState.value = _uiState.value.copy(
            inputUnit = newValue,
            conversionFactor = factor)
        recalc()
    }

    fun updateOutputUnit(newValue: String, factor: Double) {
        _uiState.value = _uiState.value.copy(
            outputUnit = newValue,
            oConversionFactor = factor)
        recalc()
    }

    fun setInputExpanded(expanded: Boolean) {
        _uiState.value = _uiState.value.copy(iExpanded = expanded)
    }

    fun setOutputExpanded(expanded: Boolean) {
        _uiState.value = _uiState.value.copy(oExpanded = expanded)
    }

    private fun recalc() {
        val state = _uiState.value
        val input = state.inputValue.toDoubleOrNull() ?: 0.0

        val result = (input*state.conversionFactor*100.0/state.oConversionFactor)
            .roundToInt()/100.0

        _uiState.value = state.copy(outputValue = result.toString())
    }
}

data class UnitConverterUiState(
    val inputValue: String = "",
    val outputValue: String = "",
    val inputUnit: String= "Meters",
    val outputUnit: String = "Meters",
    val iExpanded: Boolean= false,
    val oExpanded: Boolean = false,
    val conversionFactor: Double = 1.00,
    val oConversionFactor: Double = 1.00,
)