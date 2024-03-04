package com.newsapp.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.newsapp.R
import com.newsapp.model.Category
import com.newsapp.model.Constants
import com.newsapp.model.NewsScreen
import com.newsapp.ui.theme.textColor

@Composable
fun CategoriesFragment(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Pick your category\n " +
                    "of interest",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        CategoriesList(navController)

    }

}

 @Preview(showSystemUi = true, showBackground = true)
@Composable
fun CategoriesFragmentPreview() {
    CategoriesFragment(rememberNavController())

}

@Composable
fun CategoriesList(navController: NavHostController) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(Constants.categories.size) {position->
            CategoryCard(category = Constants.categories.get(position),position,
                navController =navController )

        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoriesListPreview() {
    CategoriesList(rememberNavController())

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(
    category: Category
    ,index:Int
    ,navController:NavHostController
) {

    Card(
        modifier = Modifier
            .padding(24.dp)
            .height(150.dp),
        onClick = {
                  navController.navigate(route = "${NewsScreen().route}/${category.titleResId}/${category.apiID}")

        },

        shape = if (index%2== 0) RoundedCornerShape(topStart = 24.dp,
            topEnd = 24.dp,
            bottomStart = 24.dp

        )else RoundedCornerShape(topStart = 24.dp,
            topEnd = 24.dp,
            bottomEnd = 24.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = category.backgroundColor))
    ) {
        Image(
            painter = painterResource(id = category.drawableResId),
            contentDescription = "Category Image",
            modifier = Modifier
                .fillMaxHeight(.78f)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )

        Text(text = stringResource(id =category.titleResId),
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.CenterHorizontally))
    }


}

@Preview(showBackground = true)
@Composable
fun CategoryCardPreview() {
    CategoryCard(Constants.categories.get(0),0, rememberNavController())
}