package software.practica03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox rdRetrofit;
    CheckBox rdVolly;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rdRetrofit = (CheckBox)findViewById(R.id.rdRetrofit);
        rdVolly = (CheckBox)findViewById(R.id.rdVolley);
    }

    public void btnValidar(View view){
        if(rdRetrofit.isChecked())
            Toast.makeText(getApplicationContext(), "Retrofit", Toast.LENGTH_SHORT).show();
        else if (rdVolly.isChecked())
            Toast.makeText(getApplicationContext(), "Volley", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Parece que no reconoce los radiobutton", Toast.LENGTH_SHORT).show();
    }

    public void btnEnviar(View view){
        Bundle parametros = new Bundle();
        String valor = "";

        if(rdRetrofit.isChecked()){
            Toast.makeText(getApplicationContext(), "Retrofit", Toast.LENGTH_SHORT).show();
            valor = "Retrofit";
        }
        else if (rdVolly.isChecked()){
            Toast.makeText(getApplicationContext(), "Volly", Toast.LENGTH_SHORT).show();
            valor = "Volley";
        }
        parametros.putString("pakage", valor);

        Intent intent = new Intent(MainActivity.this, Mostrar_json.class);
        intent.putExtras(parametros);
        startActivity(intent);
    }
}