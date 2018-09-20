package mx.edu.hugo4295.itics.almacenaexterna;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import static android.os.Environment.getExternalStorageDirectory;

public class PrincipalActivity extends AppCompatActivity {

    boolean sdDisponible = false;
    boolean sdAccesoEscritura = false;

    TextView mensaje, ruta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        mensaje = (TextView) findViewById(R.id.lblMensaje);
        ruta = (TextView) findViewById(R.id.lblRuta);

        String estado = Environment.getExternalStorageState();

        if (estado.equals(Environment.MEDIA_MOUNTED)) {
            sdDisponible = true;
            sdAccesoEscritura = true;
            mensaje.setText("sd montada escritura lectura");
            Toast.makeText(this, "sd montada escritura lectura", Toast.LENGTH_SHORT).show();
            File nuevaCarpeta = new File(getExternalStorageDirectory(), "hugo4295");
            if (!nuevaCarpeta.exists()) {
                // nuevaCarpeta.isFile() si es archivo
                // nuevaCarpeta.isD¿irectory() si es un directorio
                Toast.makeText(this, "no existe la creamos", Toast.LENGTH_SHORT).show();
                nuevaCarpeta.mkdirs();
                ruta.setText(nuevaCarpeta.getPath().toString());
                Toast.makeText(this, nuevaCarpeta.getPath(), Toast.LENGTH_SHORT).show();

                try {
                    //se crea archivo
                    File f = new File(nuevaCarpeta.getAbsolutePath(), "hugo4295_sd.txt");
                    OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(f));

                    fout.write("Texto de prueba.");
                    fout.close();
                }catch (Exception ex)
                {
                    Toast.makeText(this, "Error al escribir fichero a tarjeta SD", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            sdDisponible = true;
            sdAccesoEscritura = false;
            Toast.makeText(this, "sd montada lectura", Toast.LENGTH_SHORT).show();
            mensaje.setText("sd montada lectura");
        } else {
            sdDisponible = false;
            sdAccesoEscritura = false;
            Toast.makeText(this, "no existe...", Toast.LENGTH_SHORT).show();
            mensaje.setText("no existe...");
        }
    }

    private void llamaleer(){
        Intent lee = new Intent(this, LeerActivity.class);
        startActivity(lee);
    }
}
