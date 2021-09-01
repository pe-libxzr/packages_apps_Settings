/*
 * Copyright (C) 2021 LibXZR <i@xzr.moe>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package moe.xzr.controllers;

import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import com.android.settings.core.BasePreferenceController;

import moe.xzr.hardware.ChargeManager;

public class OptimizedChargePreferenceController extends BasePreferenceController
        implements Preference.OnPreferenceChangeListener {
    private static final String TAG = "OptimizedChargePreferenceController";
    private static final String KEY_CHARGE_OPTIMIZE = "optimized_charge";

    private ChargeManager mManager;
    private Context THIS;

    public OptimizedChargePreferenceController(Context context) {
        super(context, KEY_CHARGE_OPTIMIZE);
        THIS = context;
        mManager = ChargeManager.getInstance(context);
    }

    @Override
    public int getAvailabilityStatus() {
        return mManager.isSupported() ? AVAILABLE : UNSUPPORTED_ON_DEVICE;
    }

    @Override
    public void updateState(Preference preference) {
        super.updateState(preference);
        ((SwitchPreference) preference).setChecked(mManager.getOptimizedChargeEnabled());
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return !mManager.setOptimizedChargeEnabled((Boolean)newValue);
    }
}
