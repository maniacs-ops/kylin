/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.apache.kylin.metadata.model;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 */

@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MeasureDesc {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("function")
    private FunctionDesc function;
    @JsonProperty("dependent_measure_ref")
    private String dependentMeasureRef;

    public List<TblColRef> getColumnsNeedDictionary() {
        // measure could store literal values using dictionary encoding to save space, like TopN
        if (function.isTopN()) {
            return Collections.singletonList(function.getTopNLiteralColumn());
        } else {
            return Collections.emptyList();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FunctionDesc getFunction() {
        return function;
    }

    public void setFunction(FunctionDesc function) {
        this.function = function;
    }

    public String getDependentMeasureRef() {
        return dependentMeasureRef;
    }

    public void setDependentMeasureRef(String dependentMeasureRef) {
        this.dependentMeasureRef = dependentMeasureRef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        MeasureDesc that = (MeasureDesc) o;

        if (!function.equals(that.getFunction()))
            return false;

        if (dependentMeasureRef != null && that.getDependentMeasureRef() == null || dependentMeasureRef == null && that.getDependentMeasureRef() != null)
            return false;

        if (dependentMeasureRef == null && that.getDependentMeasureRef() == null)
            return true;

        return dependentMeasureRef.equalsIgnoreCase(that.getDependentMeasureRef());
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "MeasureDesc [name=" + name + ", function=" + function + "]";
    }

}