package software.practica03.Retrofit;
import java.util.List;

import retrofit2.http.GET;
import software.practica03.Modelo.Usuario;
import retrofit2.Call;

public interface JSONPlaceHolder {
    @GET("comments")
    Call<List<Usuario>> listaUsuarios();
}
