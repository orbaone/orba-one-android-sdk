# orba-one-android-sdk

Official Android Implementation of the Orba One SDK.

## 1. Install as a Gradle Plugin

The SDK works on API 21+. To fetch with Gradle, make sure you add the Orba One maven repository in your root project's build.gradle file:

```gradle
repositories {
  ...
  mavenCentral()
}
```

Then add the following dependency to your app build.gradle file. You should replace the `+` with your desired version of the SDK.
```gradle
dependencies {
    ...
    implementation 'com.orbaone:orba-one-sdk:+'
}
```

Now sync your build gradle to install the sdk.

## 2. Initializing the SDK

The Orba One SDK uses a **publishable api key** and an **applicant id** that you can obtain from your vendor dashboard. Your Publishable Api key and Applicant Id will be needed in order to initialize the SDK in your mobile app. A sample implementation is shown below.

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

To customize the verification flow, you can simply make use of the sdk's builder class. All customization must be done before starting the flow.

```java
import com.orbaone.orba_one_capture_sdk_core.OrbaOne;
import com.orbaone.orba_one_capture_sdk_core.helpers.Step;

Step[] FlowStep = new Step[] {
  Step.INTRO, // Welcome step - gives your user a short overview of the flow. [Optional, Default].
  Step.ID, // Photo ID step - captures the user's identification document. [Default].
  Step.FACESCAN, // Selfie Video step - captures a video of the user for liveness detection. [Default].
  Step.COMPLETE // Final Step - informs the user that the verification process is completed. [Optional].
  };

OrbaOne oneSdk = new OrbaOne.Builder()
        .setApiKey("publishable-api-key")
        .setApplicantId("applicant-id")
        .setFlow(FlowStep)
        .create();
oneSdk.startVerification(this);
```

## 5. Customizing the Document Capture Step
To customize the document capture step, you can simply make use of the sdk's DocumentCaptureStep builder class. By using this builder class, you are able to exclude specified documents and countries from the capture flow. All customization must be done before starting the flow.

``` Java
import com.orbaone.orba_one_capture_sdk_core.OrbaOne;
import com.orbaone.orba_one_capture_sdk_core.documentCapture.CountryCode;
import com.orbaone.orba_one_capture_sdk_core.documentCapture.DocumentCaptureStep;
import com.orbaone.orba_one_capture_sdk_core.helpers.DocumentTypes;

DocumentCaptureStep captureConfig = new DocumentCaptureStep.Builder()
        .excludeDocument(new DocumentTypes[]{
                DocumentTypes.PASSPORT, // this will remove the Passport option
                DocumentTypes.DRIVERSLICENSE, // this will remove the Driver's License option
                DocumentTypes.NATIONALID // this will remove the National ID option
        })
        .excludeCountry(new CountryCode[] {
                CountryCode.JM, // this will remove Jamaica from the list of available countries
                CountryCode.US // this will remove the United States from the list of available countries
        })
        .create();
OrbaOne oneSdk = new OrbaOne.Builder()
        .setApiKey("publishable-api-key")
        .setApplicantId("applicant-id")
        .setDocumentCapture(captureConfig)
        .create();
oneSdk.startVerification(this);
```

## 6. Customizing the Theme

To ensure that Orba One fits in to your app's existing user experience, you can customize various colors by overiding the following in your ``colors.xml`` file.

```orbaColorPrimary```: Defines the background color of the Toolbar.\
```orbaColorPrimaryDark```: Defines the background color of the Statusbar.\
```orbaColorAccent```: Defines the outline of the play button as well as other details found in alert dialogs.\
```orbaColorTextPrimary```: Defines the text color of the Title in the Toolbar.\
```orbaColorTextSecondary```: Defines the text color of the Sub-title in the Toolbar.\
```orbaColorButtonPrimary```: Defines the background color of Primary Buttons and the text color of Secondary Buttons.\
```orbaColorButtonPrimaryText```: Defines the text color of Primary Buttons.\
```orbaColorButtonPrimaryPressed```: Defines the background color of Primary Buttons when pressed.

## Sample App
A sample app demonstrating the Orba One SDK's implementation has been included. See the [AndroidSample directory](https://github.com/orbaone/orba-one-android-sdk/tree/master/AndroidSample) for the Android - Java implementation.

## Support

Please post all issues through [Github](https://github.com/orbaone/orba-one-android-sdk/issues). If your query involves sensitive information, you may contact us at dev@orba.io with the subject `ANDROID ISSUE: `.

Copyright 2021 Orba One Technology Holdings Ltd. All rights reserved.
