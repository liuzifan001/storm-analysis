package jointsky.esper;

import org.apache.storm.tuple.Fields;
import java.io.Serializable;

/**
 * Created by LiuZifan on 2018/2/2.
 * esper事件流类型的定义
 */
public class EventTypeDescriptor implements Serializable {
    private final String name;
    private final Fields fields;
    private final String streamId;

    EventTypeDescriptor(String name, String[] fields, String streamId)
    {
        this.name = name;
        this.fields = new Fields(fields);
        this.streamId = streamId;
    }

    public String getName()
    {
        return name;
    }

    public Fields getFields()
    {
        return fields;
    }

    public String getStreamId()
    {
        return streamId;
    }
}
