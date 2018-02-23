package jointsky.openTSDBClient;

import java.io.IOException;

import jointsky.openTSDBClient.builder.MetricBuilder;
import jointsky.openTSDBClient.request.QueryBuilder;
import jointsky.openTSDBClient.response.Response;
import jointsky.openTSDBClient.response.SimpleHttpResponse;

public interface HttpClient extends Client {

	public Response pushMetrics(MetricBuilder builder,
                                ExpectResponse exceptResponse) throws IOException;

	public SimpleHttpResponse pushQueries(QueryBuilder builder,
                                          ExpectResponse exceptResponse) throws IOException;
}