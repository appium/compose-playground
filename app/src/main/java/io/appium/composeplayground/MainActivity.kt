package io.appium.composeplayground

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.appium.composeplayground.compose.CarouselDemo
import io.appium.composeplayground.compose.ClickableDemo
import io.appium.composeplayground.compose.DisplayTextDemo
import io.appium.composeplayground.compose.TextInputDemo
import io.appium.composeplayground.navigation.Routes

class MainActivity : ComponentActivity() {

    private lateinit var menuScroll: NestedScrollView
    private lateinit var menuList: RecyclerView
    private lateinit var composeHost: ComposeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuScroll = findViewById(R.id.menu_scroll)
        menuList = findViewById(R.id.menu_list)
        composeHost = findViewById(R.id.compose_host)

        menuList.layoutManager = LinearLayoutManager(this)
        menuList.adapter = MenuAdapter(Routes.menuItems) { route -> showDemo(route) }

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(false) {
                override fun handleOnBackPressed() {
                    showMenu()
                }
            }.also { backCallback = it },
        )
    }

    private var backCallback: OnBackPressedCallback? = null

    private fun showDemo(route: String) {
        backCallback?.isEnabled = true
        menuScroll.visibility = View.GONE
        composeHost.visibility = View.VISIBLE
        composeHost.setContent {
            MaterialTheme {
                DemoScaffold(title = route, onBack = ::showMenu) {
                    when (route) {
                        Routes.CLICKABLE -> ClickableDemo()
                        Routes.CAROUSEL -> CarouselDemo()
                        Routes.DISPLAY_TEXT -> DisplayTextDemo()
                        Routes.TEXT_INPUT -> TextInputDemo()
                    }
                }
            }
        }
    }

    private fun showMenu() {
        backCallback?.isEnabled = false
        composeHost.visibility = View.GONE
        composeHost.setContent {}
        menuScroll.visibility = View.VISIBLE
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun DemoScaffold(
        title: String,
        onBack: () -> Unit,
        content: @Composable () -> Unit,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(title) },
                    navigationIcon = {
                        TextButton(onClick = onBack) {
                            Text(getString(R.string.back))
                        }
                    },
                )
            },
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                content()
            }
        }
    }

    private class MenuAdapter(
        private val items: List<String>,
        private val onItemClick: (String) -> Unit,
    ) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

        override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): MenuViewHolder {
            val view = android.view.LayoutInflater.from(parent.context)
                .inflate(R.layout.menu_item, parent, false)
            return MenuViewHolder(view as TextView)
        }

        override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
            val label = items[position]
            holder.textView.text = label
            holder.textView.setOnClickListener { onItemClick(label) }
        }

        override fun getItemCount(): Int = items.size

        class MenuViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
    }
}
