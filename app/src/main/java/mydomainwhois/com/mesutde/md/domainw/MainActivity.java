package mydomainwhois.com.mesutde.md.domainw;

import android.app.ProgressDialog;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class MainActivity extends AppCompatActivity {



    ProgressBar pb;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new BackgroundTask().execute((Void) null);
    }

    public class BackgroundTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Lütfen Bekleyin");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                String jsonStr = Jsoup.connect("http://api.bulkwhoisapi.com/whoisAPI.php?domain=ecivas.com&token=7d3f08b98ab9f69ae15060a5b58ef1ee")
                        .ignoreContentType(true)
                        .timeout(30000)
                        .userAgent("Mozilla")
                        .get()
                        .text();

                JSONArray ja = new JSONArray(jsonStr);
                // Log.e("OUT", "Kitap Sayısı : " + ja.length());

                // for (int i = 0; i < ja.length(); i++) {
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject kitap = ja.getJSONObject(i);

                    String name = kitap.getString("name");




                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            pd.dismiss();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Void result) {
            super.onCancelled(result);
        }


    }
}
