package com.csu.cta7csc475

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csu.cta7csc475.ui.theme.CTA7CSC475Theme

class MainActivity : ComponentActivity() {
    private val viewModel: UnitConverterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CTA7CSC475Theme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter(viewModel)
                }
            }
        }
    }
}

@Composable
fun UnitConverter(
    viewModel: UnitConverterViewModel
) {
    val state by viewModel.uiState.collectAsState()



    val customTextStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 16.sp,
        color = Color.Red
    )


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.inputValue,
            onValueChange = {
                viewModel.updateInputValue(it)
            },
            label = { Text(text = "Enter Value") })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            // input box
            Box {
                // input button
                Button(
                    onClick = { viewModel.setInputExpanded(true) },
                    modifier = Modifier.testTag("input_unit_button")) {
                    Text(text = state.inputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(
                    expanded = state.iExpanded,
                    onDismissRequest = { viewModel.setInputExpanded(false) },
                    modifier = Modifier.testTag("input_dropdown")
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = { viewModel.updateInputUnit("Centimeters",0.01) },
                        modifier = Modifier.testTag("input_cm"))
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = { viewModel.updateInputUnit("Meters", 1.0) },
                        modifier = Modifier.testTag("input_m"))
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = { viewModel.updateInputUnit("Feet", 0.3048) },
                        modifier = Modifier.testTag("input_ft"))
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = { viewModel.updateInputUnit("Millimeters",0.001) },
                        modifier = Modifier.testTag("input_mm"))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            // output box
            Box {
                // output button
                Button(onClick = { viewModel.setOutputExpanded(true) }) {
                    Text(text = state.outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = state.oExpanded, onDismissRequest = { viewModel.setOutputExpanded(false) }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") },
                        onClick = {
                            viewModel.updateOutputUnit("Centimeters", 0.01)
                        })
                    DropdownMenuItem(text = { Text(text = "Meters") },
                        onClick = {
                            viewModel.updateOutputUnit("Meters", 1.0)

                        })
                    DropdownMenuItem(text = { Text(text = "Feet") },
                        onClick = {
                            viewModel.updateOutputUnit("Feet", 0.3048)

                        })
                    DropdownMenuItem(text = { Text(text = "Millimeters") },
                        onClick = {
                            viewModel.updateOutputUnit("Millimeters", 0.001)

                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        //Result Text
        Text(
            text = "Result: ${state.outputValue} ${state.outputUnit}",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter(viewModel = UnitConverterViewModel())
}