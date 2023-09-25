package com.example.u1p1_tarea11;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.u1p1_tarea11.databinding.ActivityMainBinding;  //Importamos el binding sacándolo de build.gradle de la app.

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; //Declaramos el Binding

    //Declaración de variables
    private int counterTop = 0; //Contador superior(1)
    private int counterUpper = 0; //Contador inmediatamente inferior al superior(2)
    private int counterLower = 0; //Contador inmediatamente inferior al upper(3)
    private int counterBottom = 0; //Contador inferior(4)

    // Constantes para las ID de los botones de reset, las pide haga lo que haga.
    private static final int RESET_TOP_BUTTON_ID = R.id.btnResetTop;
    private static final int RESET_UPPER_BUTTON_ID = R.id.btnResetUpper;
    private static final int RESET_LOWER_BUTTON_ID = R.id.btnResetLower;
    private static final int RESET_BOTTOM_BUTTON_ID = R.id.btnResetBottom;
    private static final int RESET_ALL_BUTTON_ID = R.id.btnResetAll;

    //Para contabilizar el total de forma correcta necesitamos un método, así podremos llamarlo con cada +1 para que se vaya actualizando.
    private void updateTotal() { //También nos servirá para los reset
        int totalCounter = counterTop + counterUpper + counterLower + counterBottom;
        binding.txtTotalCount.setText(getString(R.string.strTotalCounter, totalCounter));   //Seteamos el texto usando la variable totalCounter
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());  // Inicializamos los componentes del binding

        View view = binding.getRoot();   // Inicializamos la vista root (Vista principal)

        setContentView(view);  // Inicializamos la vista de contenido dentro de view

        TextView[] counters = {     // Array para mantener referencias de TextViews
                binding.txtTotalCount,
                binding.txtCounterTop,
                binding.txtCounterUpper,
                binding.txtCounterLower,
                binding.txtCounterBottom
        };
        Button[] resetButtons = {   //Array para mantener referencias de Buttons
                binding.btnResetTop,
                binding.btnResetUpper,
                binding.btnResetLower,
                binding.btnResetBottom,
                binding.btnResetAll
        };

        //Inicializamos los componentes básicos
        setSupportActionBar(binding.toolbar); //la toolbar

        //Setear el texto por defecto (0) en los contadores.
        for (TextView textView : counters) {
            textView.setText(getString(R.string.counterDefault));
        }

        //Empezamos con los listeners para los distintos botones. No he visto una forma más eficiente de hacerlo que con un copia-pega.
        // Boton +1 Top
        binding.btnPlusOneTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterTop++;   // Incrementamos el contador cada click.
                binding.txtCounterTop.setText(getString(R.string.strCounterTop, counterTop));   //Actualizamos el texto
                updateTotal();   //Actualizamos el total
            }
        });

        // Boton +1 Upper
        binding.btnPlusOneUpper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterUpper++;  // Incrementamos el contador cada click.
                binding.txtCounterUpper.setText(getString(R.string.strCounterUpper, counterUpper));   //Actualizamos el texto
                updateTotal();   //Actualizamos el total
            }
        });
        //Boton +1 Lower
        binding.btnPlusOneLower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterLower++;  // Incrementamos el contador cada click.
                binding.txtCounterLower.setText(getString(R.string.strCounterLower, counterLower));  //Actualizamos el texto
                updateTotal();   //Actualizamos el total
            }
        });
        //Boton +1 Bottom
        binding.btnPlusOneBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterBottom++;    // Incrementamos el contador cada click.
                binding.txtCounterBottom.setText(getString(R.string.strCounterBottom, counterBottom));     //Actualizamos el texto
                updateTotal();   //Actualizamos el total
            }
        });

        //Creamos un método reset para resetear cada uno de los botones.
        View.OnClickListener reset = new View.OnClickListener() {
            //Es necesaria una supresión de la necesidad de usar constantes, aunque se están usando igual.
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {  //El método utiliza unos if con else-if porque no hubo manera
                //de usar un switch. Ya me hubiera gustado.
                if (v.getId() == RESET_TOP_BUTTON_ID) {  //comparamos la ID, de ser igual que el botón que estamos pulsando
                    counterTop = 0; //La variable vuelve a cero y más abajo, el texto también vuelve a cero.
                    binding.txtCounterTop.setText(getString(R.string.strCounterTop, counterTop));
                }
                if (v.getId() == RESET_UPPER_BUTTON_ID) {
                    counterUpper = 0;
                    binding.txtCounterUpper.setText(getString(R.string.strCounterUpper, counterUpper));
                }
                if (v.getId() == RESET_LOWER_BUTTON_ID) {
                    counterLower = 0;
                    binding.txtCounterLower.setText(getString(R.string.strCounterLower, counterLower));
                }
                if (v.getId() == RESET_BOTTOM_BUTTON_ID) {
                    counterBottom = 0;
                    binding.txtCounterBottom.setText(getString(R.string.strCounterBottom, counterBottom));
                }
                if (v.getId() == RESET_ALL_BUTTON_ID) { //En el reset total tenemos que
                    //Resetear toads las variables
                    counterTop = 0;
                    counterUpper = 0;
                    counterLower = 0;
                    counterBottom = 0;
                    //Resetear todos los textos de los contadores usando nuestro Array.
                    for (TextView textView : counters) {
                        textView.setText(getString(R.string.counterDefault));
                    }
                }
                //También actualizamos el total llamando a nuestro método fuera de los ifs.
                updateTotal();
            }
        };
// Y ahora asignamos el reset a cada botón
        for (Button button : resetButtons) {
            button.setOnClickListener(reset);
        }
    }
}

/*NOTA AL PROFESOR:
*Intenté hacerlo con un switch, que me gustan bastante, pero me estaba pidiendo
*todo el rato que las id fueran "constantes" por mucho que las hice constantes,
* no quiso funcionar. Pego el código aquí, por si tiene solución:
*
* // Creamos un OnClickListener para todos los botones de reinicio
View.OnClickListener reset = new View.OnClickListener() {
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case RESET_TOP_BUTTON_ID:
                counterTop = 0;
                binding.txtCounterTop.setText(getString(R.string.strCounterTop, counterTop));
                break;
            case RESET_UPPER_BUTTON_ID:
                counterUpper = 0;
                binding.txtCounterUpper.setText(getString(R.string.strCounterUpper, counterUpper));
                break;
            case RESET_LOWER_BUTTON_ID:
                counterLower = 0;
                binding.txtCounterLower.setText(getString(R.string.strCounterLower, counterLower));
                break;
            case RESET_BOTTOM_BUTTON_ID:
                counterBottom = 0;
                binding.txtCounterBottom.setText(getString(R.string.strCounterBottom, counterBottom));
                break;
            case RESET_ALL_BUTTON_ID:
                counterTop = 0;
                counterUpper = 0;
                counterLower = 0;
                counterBottom = 0;
                for (TextView textView : counters) {
                    textView.setText(getString(R.string.counterDefault));
                }
                break;
        }
        updateTotal();
    }
};
*/
