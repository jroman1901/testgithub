package com.example.democrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtcodigo, txtdescripcion, txtprecio, txtcantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtcodigo  = (EditText) findViewById(R.id.et1);
        txtdescripcion = (EditText) findViewById(R.id.et2);
        txtprecio = (EditText) findViewById(R.id.et3);
        txtcantidad = (EditText) findViewById(R.id.et4);

    }
    public void Guardar(View v)
    {
        try {
                AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this,"inventario",null,1);
                SQLiteDatabase db = admin.getWritableDatabase();
                String codigo = txtcodigo.getText().toString();
                String descripcion = txtdescripcion.getText().toString();
                Float precio = Float.parseFloat(txtprecio.getText().toString());
                Float cantidad = Float.parseFloat(txtcantidad.getText().toString());
                ContentValues contenedor = new ContentValues();
                contenedor.put("codigo",codigo);
                contenedor.put("descripcion",descripcion);
                contenedor.put("precio",precio);
                contenedor.put("cantidad",cantidad);
                db.insert("articulos",null,contenedor);
                Toast.makeText(this,"DATOS GUARDADOS CON EXITO!!!",Toast.LENGTH_SHORT).show();
                limpiar();
    }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void limpiar()
    {
     txtcodigo.setText("");
     txtdescripcion.setText("");
     txtprecio.setText("");
     txtcantidad.setText("");
    }

    public void Consultar(View v)
    {
        try {
            AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(this,"inventario",null,1);
            SQLiteDatabase db = admin.getWritableDatabase();
            String codigo = txtcodigo.getText().toString();
            Cursor cursor  = db.rawQuery("select codigo,descripcion,precio,cantidad from articulos where codigo ="+codigo+"",null);
            if(cursor.moveToFirst())
            {
                txtcodigo.setText(cursor.getString(0).toString());
                txtdescripcion.setText(cursor.getString(1).toString());
                txtprecio.setText(cursor.getString(2).toString());
                txtcantidad.setText(cursor.getString(3));
            }
            else
            {
                Toast.makeText(this,"No existen coincidencias en la base de datos",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception ex)
        {

        }
    }


}