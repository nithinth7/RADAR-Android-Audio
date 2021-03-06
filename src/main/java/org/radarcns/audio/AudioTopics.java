/*
 * Copyright 2017 Universität Passau and The Hyve
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.radarcns.audio;

import org.radarcns.android.device.DeviceTopics;
import org.radarcns.key.MeasurementKey;
import org.radarcns.topic.AvroTopic;

/** Topic manager for topics concerning the Empatica E4. */
public class AudioTopics extends DeviceTopics {
    private final AvroTopic<MeasurementKey, OpenSmile2PhoneAudio> audioTopic;

    private static final Object syncObject = new Object();
    private static AudioTopics instance = null;

    public static AudioTopics getInstance() {
        synchronized (syncObject) {
            if (instance == null) {
                instance = new AudioTopics();
            }
            return instance;
        }
    }

    private AudioTopics() {
        audioTopic = createTopic("android_processed_audio",
                OpenSmile2PhoneAudio.getClassSchema(),
                OpenSmile2PhoneAudio.class);
    }

    public AvroTopic<MeasurementKey, OpenSmile2PhoneAudio> getAudioTopic() {
        return audioTopic;
    }

}
