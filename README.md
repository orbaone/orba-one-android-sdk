# orba-one-android-sdk

Official Android Implementation of the Orba One SDK.

## 1. Install as a Gradle Plugin

The SDK works on API 21+. To fetch with Gradle, make sure you add the Orba One bintray repository in your root project's build.gradle file:

```gradle
allprojects {
  repositories {
    ...
    maven {
      url  "https://orbaone.bintray.com/orba-one"
    }
  }
}
```

Then add the following to your app build.gradle file.
```gradle
dependencies {
    ...
    implementation 'com.orbaone:orba-one-sdk:+'
}
```

Now sync your build gradle to install the sdk.

## 2. Initializing the SDK

Orba One uses a **publishable api key** that you can obtain from your vendor dashboard. Your publishable api key will be needed in order to initialize the SDK in your mobile app. An sample implementation is provided below.

```java
import com.orbaone.orba_one_capture_sdk_core.OrbaOne;

OrbaOne oneSdk = new OrbaOne.Builder().setApiKey("publishable-api-key").setApplicantId("applicant-id").create();
oneSdk.startVerification(this);
```

## 3. Handling Verifications

Orba One exposes two callbacks in order to let you know if the user has completed or cancelled the verification flow. A third callback (onStartVerification) is also supplied to alert you if the user has successfully began the flow.

```java
oneSdk.onStartVerification(new OrbaOne.Response() {
  @Override
  public void onSuccess() {
    // Flow started
  }

  @Override
  public void onFailure(String message) {
    // Flow not started
  }
});

oneSdk.onCompleteVerification(new OrbaOne.Callback() {
  @Override
  public void execute(String key) {
    // Flow completed successfully. The applicant id is also returned as a parameter.
  }
});

oneSdk.onCancelVerification(new OrbaOne.Callback() {
  @Override
  public void execute() {
    // Flow cancelled by the user.
  }
});
```
## 4. Customizing the Flow

To customize the verification flow, you can simply make use of the sdk's builder function. All customization must be done before starting the flow.

```java
import com.orbaone.orba_one_capture_sdk_core.OrbaOne;
import com.orbaone.orba_one_capture_sdk_core.helpers.Step;

Step[] FlowStep = new Step[] {
  Step.INTRO, // Welcome step - gives your user a short overview of the flow. [Optional, Default].
  Step.ID, // Photo ID step - captures the user's identification document. [Default].
  Step.FACESCAN, // Selfie Video step - captures a video of the user for liveness detection. [Default].
  Step.COMPLETE // Final Step - alerts the user that all uploads are completed. [Optional].
  };

OrbaOne oneSdk = new OrbaOne.Builder().setApiKey("publishable-api-key").setApplicantId("applicant-id").setFlow(FlowStep).create();
oneSdk.startVerification(this);
```

## 5. Customizing the Theme

To ensure that Orba One fits in to your app's existing user experience, you can customize various colors by overiding the following in your ``colors.xml`` file.

```orbaColorPrimary```: Defines the background color of the Toolbar.\
```orbaColorPrimaryDark```: Defines the background color of the Statusbar.\
```orbaColorAccent```: Defines the outline of the play button as well as other details found in alert dialogs.\
```orbaColorTextPrimary```: Defines the text color of the Title in the Toolbar.\
```orbaColorTextSecondary```: Defines the text color of the Sub-title in the Toolbar.\
```orbaColorButtonPrimary```: Defines the background color of Primary Buttons and the text color of Secondary Buttons.\
```orbaColorButtonPrimaryText```: Defines the text color of Primary Buttons.\
```orbaColorButtonPrimaryPressed```: Defines the background of Primary Buttons when pressed.

## License

MIT
