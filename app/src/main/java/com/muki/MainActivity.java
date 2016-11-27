package com.muki;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.muki.core.MukiCupApi;
import com.muki.core.MukiCupCallback;
import com.muki.core.model.Action;
import com.muki.core.model.DeviceInfo;
import com.muki.core.model.ErrorCode;
import com.muki.core.model.ImageProperties;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText mSerialNumberEdit;
    private TextView mCupIdText;
    private TextView mDeviceInfoText;
    private ImageView mCupImage;
    private SeekBar mContrastSeekBar;
    private ProgressDialog mProgressDialog;
    private int selectMenu = 1;
    private Bitmap mImage;
    private int mContrast = ImageProperties.DEFAULT_CONTRACT;
    private List<Readiness> datosAnillo;
    private String mCupId;
    private MukiCupApi mMukiCupApi;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading. Please wait...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        mMukiCupApi = new MukiCupApi(getApplicationContext(), new MukiCupCallback() {
            @Override
            public void onCupConnected() {
                showToast("Cup connected");
            }

            @Override
            public void onCupDisconnected() {
                showToast("Cup disconnected");
            }

            @Override
            public void onDeviceInfo(DeviceInfo deviceInfo) {
                hideProgress();
                mDeviceInfoText.setText(deviceInfo.toString());
            }

            @Override
            public void onImageCleared() {
                showToast("Image cleared");
            }

            @Override
            public void onImageSent() {
                showToast("Image sent");
            }

            @Override
            public void onError(Action action, ErrorCode errorCode) {
                showToast("Error:" + errorCode + " on action:" + action);
            }
        });

        mSerialNumberEdit = (EditText) findViewById(R.id.serailNumberText);
        mCupIdText = (TextView) findViewById(R.id.cupIdText);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        //PARTE TONI

        ApiService api = RetroClient.getApiService();
        Call<HackerNews> call = api.getMyJSON();

        call.enqueue(new Callback<HackerNews>() {
            @Override
            public void onResponse(Call<HackerNews> call, Response<HackerNews> response) {
                if(response.isSuccessful()) {
                    datosAnillo = response.body().getReadiness(); // Resultados
                  //  textView.setText(response.body().getReadiness().get(1).getScore().toString());
                }

            }

            @Override
            public void onFailure(Call<HackerNews> call, Throwable t) {

            }
        });

    }
    public static Bitmap addBitmap(Bitmap bitmap1, Bitmap bitmap2) {
        int bitmap2Width = bitmap2.getWidth();
        int bitmap1Height = bitmap1.getHeight();
        int bitmap2Height = bitmap2.getHeight();

        Bitmap overlayBitmap = Bitmap.createBitmap(bitmap2Width, bitmap1Height+bitmap2Height, bitmap1.getConfig());
        Canvas canvas = new Canvas(overlayBitmap);
        canvas.drawBitmap(bitmap1, new Matrix(), null);
        canvas.drawBitmap(bitmap2, 0, bitmap1Height, null);
        return overlayBitmap;
    }

    public Bitmap createImage(int width, int height, int color, String name) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        canvas.drawRect(0F, 0F, (float) width, (float) height, paint2);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(42);
        paint.setTextScaleX(1);
        canvas.drawText(name, 75 - 25, 75 + 20, paint);
        return bitmap;
    }


    public void menuResumen(){
          int finalLista = datosAnillo.size() - 1;
          Readiness it = datosAnillo.get(finalLista);
          Bitmap textInfo0 = createImage(450, 100, Color.BLACK, "Summary");
          Bitmap textInfo1 = createImage(450, 100, Color.BLACK, "Score: "+it.getScore());
          Bitmap textInfo2 = createImage(450, 100, Color.BLACK, "Reco index: "+it.getScoreRecoveryIndex());
          Bitmap textInfo3 = createImage(450, 100, Color.BLACK, "Activ Balance: "+it.getScoreActivityBalance());
          Bitmap textInfo4 = createImage(450, 100, Color.BLACK, "Rest hours: "+it.getScoreRestingHr());

          textInfo3 = addBitmap(textInfo3, textInfo4);
          textInfo2 = addBitmap(textInfo2, textInfo3);
          textInfo1 = addBitmap(textInfo1, textInfo2);
          textInfo0 = addBitmap(textInfo0, textInfo1);
          mMukiCupApi.sendImage(textInfo0, new ImageProperties(50), mCupId);

    }
    public void menuPrincipal(View view) {
          showProgress();
          BarChart chart = (BarChart) findViewById(R.id.chart);
          //int[] dataObjects = {73, 61, 92, 80, 85};
          List<Entry> entries = new ArrayList<Entry>();
          ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
          int x = 0;
          for (Readiness data : datosAnillo) {

              // turn your data into Entry objects
              //X     Y
              yVals1.add(new BarEntry(x, data.getScoreSleepBalance()));
              ++x;
          }
          BarDataSet set1 = new BarDataSet(yVals1, "");
          set1.setColor(Color.BLACK);
          ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
          dataSets.add(set1);

          BarData data = new BarData(dataSets); // add entries to dataset
          data.setValueTextSize(14f);
          data.setBarWidth(0.9f);
          data.setValueTextColor(Color.BLACK);
          data.setValueFormatter(new IValueFormatter() {
              @Override
              public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                  return String.valueOf((int) value);
              }
          }); // styling, ...

          chart.getDescription().setEnabled(false);
          chart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
              @Override
              public String getFormattedValue(float value, AxisBase axis) {
                  return String.valueOf((int) value);
              }
          });
          chart.getLegend().setEnabled(false);
          chart.getAxisRight().setEnabled(false);
          chart.getXAxis().setEnabled(false);
          chart.getAxisLeft().setEnabled(false);
          chart.setData(data);

          chart.invalidate(); //refresh
          Bitmap image = chart.getChartBitmap();
          Date myDate = new Date();
          SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd/MM/yyyy");
          String fechaAct = timeStampFormat.format(myDate);
          Bitmap textInfo = createImage(image.getWidth(), 100, Color.BLACK, "Sleep Balance (%)");
          Bitmap textSup = createImage(image.getWidth(), 100, Color.BLACK, fechaAct);
          textSup = addBitmap(textInfo, textSup);
          image = addBitmap(textSup, image);
          mMukiCupApi.sendImage(image, new ImageProperties(50), mCupId);

    }

    public void request(final View view) {
        String serialNumber = mSerialNumberEdit.getText().toString();
        showProgress();
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    String serialNumber = strings[0];
                    return MukiCupApi.cupIdentifierFromSerialNumber(serialNumber);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                mCupId = s;
                mCupIdText.setText(mCupId);
                hideProgress();

                if(selectMenu == 1) menuPrincipal(view);
                else{menuResumen();}
            }
        }.execute(serialNumber);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.Readiness:
                if (checked)
                    selectMenu = 1;
                    break;
            case R.id.Summary:
                if (checked)
                    selectMenu = 2;
                    break;
        }
    }
    private void showToast(final String text) {
        hideProgress();
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    private void showProgress() {
        mProgressDialog.show();
    }

    private void hideProgress() {
        mProgressDialog.dismiss();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public com.google.android.gms.appindexing.Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new com.google.android.gms.appindexing.Action.Builder(com.google.android.gms.appindexing.Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(com.google.android.gms.appindexing.Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
