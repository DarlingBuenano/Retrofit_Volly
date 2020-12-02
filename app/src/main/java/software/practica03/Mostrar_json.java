package software.practica03;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import software.practica03.Modelo.Usuario;
import software.practica03.Retrofit.JSONPlaceHolder;

public class Mostrar_json extends AppCompatActivity {
    TextView txtJSON, txtTitulo;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_json);

        txtJSON = (TextView)findViewById(R.id.txtJSONParseado);
        txtJSON.setMovementMethod(new ScrollingMovementMethod());

        txtTitulo = (TextView)findViewById(R.id.txtTitulo);

        Bundle parametros = this.getIntent().getExtras();
        String valor = parametros.getString("pakage");
        txtTitulo.setText(txtTitulo.getText().toString() + " " + valor);

        if(valor.equals("Retrofit")){
            ConsumirAPIRetrofit();
        }
    }

    private void ConsumirAPIRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JSONPlaceHolder servicio = retrofit.create(JSONPlaceHolder.class);
        Call<List<Usuario>> llamado = servicio.listaUsuarios();
        llamado.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(!response.isSuccessful()){
                    txtJSON.setText("Error: " + response.code());
                }
                List<Usuario> listaUsuarios = response.body();
                String cadena = "";
                for(Usuario user: listaUsuarios){
                    cadena = cadena + "postId: " + user.getPostId() + "\n";
                    cadena = cadena + "id: " + user.getId() + "\n";
                    cadena = cadena + "name: " + user.getName() + "\n";
                    cadena = cadena + "email: " + user.getEmail() + "\n";
                    cadena = cadena + "body: " + user.getBody() + "\n\n";
                }
                txtJSON.setText(cadena);
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                txtJSON.setText(t.getMessage());
                Toast.makeText( getApplicationContext(),"Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}