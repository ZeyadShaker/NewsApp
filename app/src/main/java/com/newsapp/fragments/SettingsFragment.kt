package com.newsapp.fragments

import Demo_ExposedDropdownMenuBox
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.newsapp.R
import com.newsapp.api.APIManager
import com.newsapp.model.Constants
import com.newsapp.model.api.ArticlesResponse
import com.newsapp.model.api.SourcesResponse
import com.newsapp.ui.theme.green
import org.intellij.lang.annotations.Language
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.file.WatchEvent.Modifier
import java.time.format.TextStyle

@Composable
fun SettingsFragment() {
    val context = LocalContext.current
    Demo_ExposedDropdownMenuBox { selectedLanguage ->
        val apiService = APIManager.getNewsServices()
        apiService.changeLanguage(Constants.API_KEY,selectedLanguage).enqueue(object :Callback<ArticlesResponse>{
            override fun onResponse(
                call: Call<ArticlesResponse>,
                response: Response<ArticlesResponse>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(context,"Successful".toString(),Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()

                }
            }
            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                Toast.makeText(context,t.message.toString(),Toast.LENGTH_SHORT).show()
            }
        })

    }}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SettingsFragmentPreview() {
    SettingsFragment()

}

@Composable
fun LanguageTextField() {
    Row() {
        Text(text = stringResource(id = R.string.english))
        Image(painter = painterResource(id = R.drawable.ic_settings),
            contentDescription ="icon" )

    }
    
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LanguageTextFieldPreview() {
    LanguageTextField()
}