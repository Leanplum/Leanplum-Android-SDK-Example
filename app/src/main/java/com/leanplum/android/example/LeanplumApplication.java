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

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;

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
    Leanplum.start(this);
  }
}
