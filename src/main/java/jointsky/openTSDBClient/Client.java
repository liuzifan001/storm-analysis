package jointsky.openTSDBClient;

import java.io.IOException;

import jointsky.openTSDBClient.builder.MetricBuilder;
import jointsky.openTSDBClient.response.SimpleHttpResponse;
import jointsky.openTSDBClient.request.QueryBuilder;
import jointsky.openTSDBClient.response.Response;

public interface Client {

	String PUT_POST_API = "/api/put";

    String QUERY_POST_API = "/api/query";

	/**
	 * Sends metrics from the builder to the KairosDB server.
	 *
	 * @param builder
	 *            metrics builder
	 * @return response from the server
	 * @throws IOException
	 *             problem occurred sending to the server
	 */
	Response pushMetrics(MetricBuilder builder) throws IOException;

	SimpleHttpResponse pushQueries(QueryBuilder builder) throws IOException;
}