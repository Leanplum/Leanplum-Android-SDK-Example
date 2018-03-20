//  Copyright © 2017 Leanplum.
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//      http://www.apache.org/licenses/LICENSE-2.0
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//
package com.leanplum.android.example;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumPushService;
import com.leanplum.callbacks.StartCallback;

import java.lang.reflect.Field;

public class LeanplumApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();

    // Warning: Only used for testing purposes, do not use in production.
    Leanplum.setApiConnectionSettings(Configure.API_HOST_NAME, "api", Configure.API_SSL);

    Leanplum.setApplicationContext(this);
    if (BuildConfig.DEBUG) {
      Leanplum.setAppIdForDevelopmentMode(Configure.APP_ID, Configure.DEVELOPMENT_KEY);
    } else {
      Leanplum.setAppIdForProductionMode(Configure.APP_ID, Configure.PRODUCTION_KEY);
    }
    LeanplumActivityHelper.enableLifecycleCallbacks(this);

    // Read GCM Sender Id from BuildConfig, configurable in build.gradle.
    String gcmSenderId = (String) getBuildConfigValue(this, "GCM_SENDER_ID");
    if (gcmSenderId != null) {
      LeanplumPushService.setGcmSenderId(gcmSenderId);
    }
    Leanplum.start(this, new StartCallback() {
      @Override
      public void onResponse(boolean b) {
        Log.i("Leanplum Test", "Messages at start response: " + Leanplum.messageMetadata());
      }
    });
  }

  /**
   * Gets a field from the project's BuildConfig. This is useful when, for example, flavors
   * are used at the project level to set custom fields.
   *
   * @param context Used to find the correct file
   * @param fieldName The name of the field-to-access
   * @return The value of the field, or {@code null} if the field is not found.
   */
  public static Object getBuildConfigValue(Context context, String fieldName) {
    try {
      Class<?> clazz = Class.forName(context.getPackageName() + ".BuildConfig");
      Field field = clazz.getField(fieldName);
      return field.get(null);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }
}
