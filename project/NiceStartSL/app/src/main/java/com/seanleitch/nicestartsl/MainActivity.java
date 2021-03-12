package com.seanleitch.nicestartsl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavView;
    Toolbar toolbar;
    Fragment selectedFragment = null;
    private DrawerLayout drawer;
    ConstraintLayout expandableView;
    Button arrowBtn;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navigation Drawr
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        bottomNavView = findViewById(R.id.bottomNavView);
        bottomNavView.setOnNavigationItemSelectedListener(listener);

        // AppBar/Toolbar
        toolbar = findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        // Expandable CardView
        expandableView = findViewById(R.id.expandableView);
        arrowBtn = findViewById(R.id.arrowBtn);
        cardView = findViewById(R.id.cardView1);

        arrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });

        // SwipeRefreshLayout
        final SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Snackbar
                Snackbar snackbar = Snackbar.make(drawer, "New forests are growing", Snackbar.LENGTH_LONG);
                snackbar.show();

            }
        });

        // Context Menu
        Button btnContext = (Button) findViewById(R.id.btnContext);
        registerForContextMenu(btnContext);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar_menu, menu);
        return true;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextual_menu, menu);
    }

    // Navigation Drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_mistrees:
                Toast.makeText(MainActivity.this, "Has pulsado en 'Mis Trees'", Toast.LENGTH_LONG).show();

                break;
            case R.id.nav_misforrests:
                Toast.makeText(MainActivity.this, "Has pulsado en 'Mis Forests'", Toast.LENGTH_LONG).show();

                break;
            case R.id.nav_aboutus:
                Toast.makeText(MainActivity.this, "Has pulsado en 'Sobre Nosotros'", Toast.LENGTH_LONG).show();

                break;
            case R.id.nav_logout:
                MaterialAlertDialogBuilder alertDialog = new MaterialAlertDialogBuilder(MainActivity.this);
                alertDialog.setMessage("¿Estás seguro que deseas salir?");
                alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.create().show();
                break;
        }

        return true;
    }

    // Bottom Navigation
    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navTree1:
                    Toast.makeText(MainActivity.this, "Has pulsado en 'Trees 1'", Toast.LENGTH_LONG).show();

                    break;
                case R.id.navTree2:
                    Toast.makeText(MainActivity.this, "Has pulsado en 'Trees 2'", Toast.LENGTH_LONG).show();

                    break;
                case R.id.navTree3:
                    Toast.makeText(MainActivity.this, "Has pulsado en 'Trees 3'", Toast.LENGTH_LONG).show();

                    break;
                case R.id.navTree4:
                    Toast.makeText(MainActivity.this, "Has pulsado en 'Trees 4'", Toast.LENGTH_LONG).show();

                    break;

                case R.id.navTree5:
                    Toast.makeText(MainActivity.this, "Has pulsado en 'Trees 5'", Toast.LENGTH_LONG).show();

                    break;
            }

            return true;
        }
    };

}