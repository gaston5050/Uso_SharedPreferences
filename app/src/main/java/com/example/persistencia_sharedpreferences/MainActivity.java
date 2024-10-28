package com.example.persistencia_sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView txtProductoAgregar, txtListadoProductos, txtsegundo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtProductoAgregar = findViewById(R.id.txtProducto);
        txtListadoProductos = findViewById(R.id.txtLista);
        txtsegundo = findViewById(R.id.txtSecundario);

    }

    public void agregar (View view){

        String productoNuevo = txtProductoAgregar.getText().toString();

        SharedPreferences prefs = getSharedPreferences("productos", MODE_PRIVATE);

        String productos = prefs.getString("productos", "");
        productos +=  productoNuevo + "\n";

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("productos", productos);
        editor.apply();
        listarProductos(view);
        vaciar();
    }
    public void listarProductos (View view){
        SharedPreferences prefs = getSharedPreferences("productos", MODE_PRIVATE);
        String producto = prefs.getString("productos", "");
        txtListadoProductos.setText(producto);
        vaciar();
    }

    public void borrar (View view){
        SharedPreferences prefs = getSharedPreferences("productos", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
        vaciar();
        listarProductos(view);
    }

    public void vaciar(){
       // txtListadoProductos.setText("");
        txtProductoAgregar.setText("");
    }
}