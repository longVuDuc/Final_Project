package com.example.final_project.screen


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.final_project.R

@Composable
fun BottomAppBar(
    navigateToHome: () -> Unit,
    navigateToSearchTodo: () -> Unit,
    navigateToSetting: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically ) {
        Spacer(modifier = Modifier.width(60.dp))
        IconButton(onClick = { navigateToHome() }) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier.size(40.dp)
            )
        }
        Spacer(modifier = Modifier.width(40.dp))
        IconButton(onClick = { navigateToSearchTodo() }) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_todo_title),
                modifier = Modifier.size(40.dp)
            )
        }
        Spacer(modifier = Modifier.width(40.dp))
        IconButton(onClick = { navigateToSetting() }) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = stringResource(R.string.profile_title),
                modifier = Modifier.size(40.dp)
            )
        }
    }
}
