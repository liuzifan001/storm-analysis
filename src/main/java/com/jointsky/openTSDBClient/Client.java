package com.jointsky.openTSDBClient;

import java.io.IOException;

import com.jointsky.openTSDBClient.builder.MetricBuilder;
import com.jointsky.openTSDBClient.response.Response;
import com.jointsky.openTSDBClient.response.SimpleHttpResponse;
import com.jointsky.openTSDBClient.request.QueryBuilder;

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