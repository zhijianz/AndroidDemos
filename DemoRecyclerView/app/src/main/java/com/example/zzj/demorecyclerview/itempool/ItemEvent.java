/*
 * Copyright 2016 nekocode
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.zzj.demorecyclerview.itempool;

import android.support.v7.widget.RecyclerView;

/**
 * Created by nekocode on 16/8/17.
 */
public class ItemEvent {
    public static final int ITEM_CLICK = -1201;
    public static final int ITEM_LONGCLICK = -1202;

    public int action;
    public Object data;
    public RecyclerView.ViewHolder holder;

    public ItemEvent(int action, Object data, RecyclerView.ViewHolder holder) {
        this.action = action;
        this.data = data;
        this.holder = holder;
    }
}
