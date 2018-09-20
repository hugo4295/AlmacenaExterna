package mx.edu.hugo4295.itics.almacenaexterna;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.os.Environment.getExternalStorageDirectory;

public class LeerActivity extends AppCompatActivity {

    TextView contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer);

        contenido = (TextView) findViewById(R.id.lblcontenido);

        String estado = Environment.getExternalStorageState();

        if (estado.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "sd montada escritura lectura", Toast.LENGTH_SHORT).show();
            File nuevaCarpeta = new File(getExternalStorageDirectory(), "hugo4295");
            if (nuevaCarpeta.exists()) {
                // nuevaCarpeta.isFile() si es archivo
                // nuevaCarpeta.isD¿irectory() si es un directorio

                Toast.makeText(this, "leyendo contenido", Toast.LENGTH_SHORT).show();

                try {
                    //se crea archivo
                    File f = new File(nuevaCarpeta.getAbsolutePath(), "hugo4295_sd.txt");

                    BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

                    String texto = fin.readLine();
                    fin.close();
                    contenido.setText(texto);
                }catch (Exception ex)
                {
                    Toast.makeText(this, "Error al escribir fichero a tarjeta SD", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
