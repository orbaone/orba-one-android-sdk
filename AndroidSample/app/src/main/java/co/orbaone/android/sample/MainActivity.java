package co.orbaone.android.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.orbaone.orba_one_capture_sdk_core.OrbaOne;
import com.orbaone.orba_one_capture_sdk_core.helpers.Step;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "SAMPLE APP";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void onButtonPress (View view) {
    try {
      Step[] FlowStep = new Step[] {Step.INTRO, Step.ID, Step.FACESCAN, Step.COMPLETE};
      OrbaOne oneSdk =
              new OrbaOne.Builder().setApiKey("PUBLISHABLE-KEY").setApplicantId("APPLICANT-ID").setFlow(FlowStep).create();
      oneSdk.startVerification(this);
      oneSdk.onStartVerification(new OrbaOne.Response() {
        @Override
        public void onSuccess() {
          // Flow started
          Log.i("Orba","Flow started");
        }

        @Override
        public void onFailure(String message) {
          // Flow not started
          Log.i("Orba","Flow not started");
        }
      });

      oneSdk.onCompleteVerification(new OrbaOne.Callback() {
        @Override
        public void execute(String key) {
          // Flow completed successfully. The applicant id is also returned as a parameter.
          Log.i("Orba","Flow completed successfully");
        }
      });

      oneSdk.onCancelVerification(new OrbaOne.Callback() {
        @Override
        public void execute() {
          // Flow cancelled by the user.
          Log.i("Orba","Flow cancelled by the user.");
        }
      });
    } catch (IllegalStateException error) {
      Log.e("SDK Error", error.getLocalizedMessage());
    }
  }
}