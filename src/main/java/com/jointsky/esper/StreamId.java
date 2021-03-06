package com.jointsky.esper;

import java.io.Serializable;

/**
 * Created by LiuZifan on 2018/2/2.
 * 定义StreamId
 */
public class StreamId implements Serializable{
    private final String componentId;
    private final String streamId;

    StreamId(String componentId) {
        this(componentId, "default");
    }

    StreamId(String componentId, String streamId) {
        this.componentId = componentId;
        this.streamId = streamId;
    }

    public String getComponentId() {
        return componentId;
    }

    public String getStreamId() {
        return streamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StreamId streamId1 = (StreamId) o;

        if (componentId != null ? !componentId.equals(streamId1.componentId) : streamId1.componentId != null) {
            return false;
        }
        return streamId != null ? streamId.equals(streamId1.streamId) : streamId1.streamId == null;

    }

    public int hashCode() {
        int result = componentId != null ? componentId.hashCode() : 0;
        result = 31 * result + (streamId != null ? streamId.hashCode() : 0);
        return result;
    }
}
