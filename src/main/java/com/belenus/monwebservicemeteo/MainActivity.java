package com.belenus.monwebservicemeteo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.belenus.monwebservicemeteo.beans.ResultBean;
import com.belenus.monwebservicemeteo.beans.WeatherBean;
import com.belenus.monwebservicemeteo.utils.WSUtils;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {


    private TextView mTextViewSky;
    private TextView mTextViewTemp;
    private TextView mTextViewHumidity;
    private TextView mTextViewPressure;
    private TextView mTextViewWind;
    private ImageView mImageView2;
    private EditText mEditTextCity;


    private ResultBean resultat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTextViewSky = findViewById(R.id.textViewSky);
        mTextViewTemp = findViewById(R.id.textViewTemp);
        mTextViewHumidity = findViewById(R.id.textViewHumidity);
        mTextViewPressure = findViewById(R.id.textViewPressure);
        mTextViewWind = findViewById(R.id.textViewWind);
        mEditTextCity = findViewById(R.id.editTextCity);
        mImageView2 = findViewById(R.id.imageView2);


    }


    public void onBtPush(View view) {
        //Toast.makeText(this, "Boutton OK", Toast.LENGTH_SHORT).show();
        monAsyncTask monAsyncTask = new monAsyncTask();
        monAsyncTask.execute();
    }

    public class monAsyncTask extends AsyncTask {

        Exception exception;

        //Appelée sur un Thread Secondaire qui ne peut pas toucher au composant graphique
        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                //Faire la requete
                resultat = WSUtils.getCityName(mEditTextCity.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
                exception = e;
            }


            return resultat;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            //Quand doInBg est fini et c'est appelé sur le thread Graphique.
            if (exception != null) {
                Toast.makeText(MainActivity.this, "Une erreur est survenue : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                //Afficher le resultat
                ResultBean bean = resultat;
                mTextViewHumidity.setVisibility(View.VISIBLE);
                mImageView2.setVisibility(View.VISIBLE);
                mTextViewSky.setVisibility(View.VISIBLE);
                mTextViewWind.setVisibility(View.VISIBLE);
                mTextViewTemp.setVisibility(View.VISIBLE);
                mTextViewPressure.setVisibility(View.VISIBLE);

                String temp;

                temp = Math.round(bean.getMainResults().getTemp() - 273) + " °";
                mTextViewTemp.setText(temp);

                String ciel;
                String icon = null;

                for (WeatherBean weather : bean.getResults()) {
                    ciel = weather.getMain();
                    icon = weather.getIcon();
                    mTextViewSky.setText(ciel);


                    if (icon != null) {

                        Picasso.get().load("http://openweathermap.org/img/wn/" + icon + "@2x.png").resize(450, 450).into(mImageView2);

                    }

                }


                String humidity;
                humidity = bean.getMainResults().getHumidity() + " %";
                mTextViewHumidity.setText(humidity);

                String pressure;
                pressure = bean.getMainResults().getHumidity() + " hPa";
                mTextViewPressure.setText(pressure);

                String wind;

                wind = bean.getWindResults().getSpeed() + " Mph";
                mTextViewWind.setText(wind);



              /*  ciel += "Le pays rechercher est  :" + bean.getSysResults().getCountry() + '\n';

                ciel += "La temperature sera de  : " + bean.getMainResults().getTemp() + '\n';

                ciel += "Le vent aura une vitesse de   : " + bean.getWindResults().getSpeed() + '\n';


                mTextViewMeteo.setText(ciel);*/


            }
        }
    }
}
